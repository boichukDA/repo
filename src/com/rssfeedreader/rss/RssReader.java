package com.rssfeedreader.rss;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class RssReader {

	private XmlPullParserFactory xmlPPFactory;

	public RssReader() {

	}

	public RssObjects readRssFromUrl(String urlStr) throws Exception {
		try {
			xmlPPFactory = XmlPullParserFactory.newInstance();

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();

			InputStream stream = conn.getInputStream();

			XmlPullParser myparser = xmlPPFactory.newPullParser();
			myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			myparser.setInput(stream, null);
			RssObjects list = parse(myparser);
			stream.close();
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ошибка парсинга", e);
		}
	}

	private RssObjects parse(XmlPullParser myparser) {
		List<RssObject> rssList = new ArrayList<RssObject>();
		int event;
		try {
			event = myparser.getEventType();
			RssObject object = null;
			Boolean isBegin = false;
			String text = null;

			while (event != XmlPullParser.END_DOCUMENT) {
				String name = myparser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					if (name.equals("item")) {
						if (!isBegin)
							isBegin = true;

						object = new RssObject();
					}
					if (name.equals("enclosure")) {
						String url = myparser.getAttributeValue(null, "url");
						object.setRssImage(url);
					}
					break;
				case XmlPullParser.TEXT:
					text = myparser.getText();
					break;
				case XmlPullParser.END_TAG:
					if (!isBegin)
						break;

					if (name.equals("title"))
						object.setRssTitle(text);

					if (name.equals("image"))
						object.setRssImage(text);

					if (name.equals("description"))
						object.setRssDescription(text);

					if (name.equals("item"))
						rssList.add(object);

					break;
				}
				event = myparser.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RssObjects(rssList);
	}
}

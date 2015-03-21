package com.rssfeedreader;

import android.os.AsyncTask;

import com.rssfeedreader.rss.RssObjects;
import com.rssfeedreader.rss.RssReader;

public class RssFeedWorker extends AsyncTask<MainActivity, Void, MainActivity> {

	@Override
	protected MainActivity doInBackground(MainActivity... params) {
		RssReader reader = new RssReader();
		RssObjects result = null;
		try {
			result = reader.readRssFromUrl(params[0].getFinalUrl());
			params[0].setObjects(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return params[0];
	}

	@Override
	protected void onPostExecute(MainActivity result) {
		result.updateListWithData();
	}
}

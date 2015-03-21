package com.rssfeedreader.rss;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<ImageView, Void, ImageView> {

	private RssObject rss;
	private Bitmap bitmap;

	public ImageLoader(RssObject rss) {
		this.rss = rss;
	}

	@Override
	protected ImageView doInBackground(ImageView... params) {
		try {
			URL iUrl = new URL(rss.getRssImage());
			bitmap = BitmapFactory.decodeStream(iUrl.openStream());
			rss.setBitmap(bitmap);
			bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return params[0];
	}

	@Override
	protected void onPostExecute(ImageView result) {
		result.setImageBitmap(bitmap);
	}
}

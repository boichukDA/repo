package com.rssfeedreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rssfeedreader.rss.RssObject;

public class DetailActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		Intent intent = getIntent();
		RssObject rssObject = (RssObject) intent
				.getParcelableExtra("rss_object");

		TextView titleView = (TextView) findViewById(R.id.detail_title);
		titleView.setText(rssObject.getRssTitle());

		ImageView imageView = (ImageView) findViewById(R.id.detail_image);
		if (rssObject.getBitmap() != null)
			imageView.setImageBitmap(rssObject.getBitmap());

		TextView descView = (TextView) findViewById(R.id.detail_description);
		descView.setText(rssObject.getRssDescription());

		final Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

}

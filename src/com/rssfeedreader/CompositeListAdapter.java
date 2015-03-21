package com.rssfeedreader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rssfeedreader.rss.ImageLoader;
import com.rssfeedreader.rss.RssObject;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class CompositeListAdapter extends ArrayAdapter<RssObject> {
	private Activity context;
	private RssObject[] rssObjects;

	public CompositeListAdapter(Activity context, RssObject[] objects) {
		super(context, R.layout.composite_list_view, objects);

		this.context = context;
		this.rssObjects = objects;
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View single_row = inflater.inflate(R.layout.composite_list_view, null,
				true);
		TextView textDescription = (TextView) single_row
				.findViewById(R.id.description);
		TextView textTitle = (TextView) single_row.findViewById(R.id.title);
		ImageView imageView = (ImageView) single_row
				.findViewById(R.id.imageView);
		textDescription.setText(rssObjects[position].getRssDescription());
		textTitle.setText(rssObjects[position].getRssTitle());
		imageView.setImageResource(R.drawable.ic_launcher);

		if (rssObjects[position].getRssImage() != null) {
			if (rssObjects[position].getBitmap() == null)
				new ImageLoader(rssObjects[position]).execute(imageView);
			else
				imageView.setImageBitmap(Bitmap.createScaledBitmap(
						rssObjects[position].getBitmap(), 50, 50, true));

		}
		return single_row;
	};
}

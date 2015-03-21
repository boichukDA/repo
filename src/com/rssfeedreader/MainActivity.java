package com.rssfeedreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.rssfeedreader.rss.RssObjects;

public class MainActivity extends Activity {

	private String finalUrl = "http://news.yandex.ru/hardware.rss";
	private RssObjects objects;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText editUrl = (EditText) findViewById(R.id.url);
		final EditText editFilter = (EditText) findViewById(R.id.filter);
		final MainActivity activity = this;
		editUrl.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					setFinalUrl(editUrl.getText().toString());
					editFilter.setText("");
					new RssFeedWorker().execute(activity);
					return true;
				}
				return false;
			}
		});

		editFilter.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					String filter = editFilter.getText().toString();
					if (objects != null)
						applyFilterToList(filter);
					return true;
				}
				return false;
			}
		});
		final ListView compositeView = (ListView) findViewById(R.id.listView);
		compositeView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getBaseContext(),
						DetailActivity.class);
				intent.putExtra("rss_object", objects.get(arg2));
				startActivityForResult(intent, 0);

			}
		});
	}

	private void applyFilterToList(String filter) {
		objects.applyFilter(filter);
		updateListWithData();
	}

	public void updateListWithData() {
		if (objects == null)
			return;
		CompositeListAdapter compositeListAdapter = new CompositeListAdapter(
				this, objects.filteredObjectsToArray());
		ListView lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(compositeListAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setObjects(RssObjects objects) {
		this.objects = objects;
	}

	public String getFinalUrl() {
		return finalUrl;
	}

	public void setFinalUrl(String finalUrl) {
		this.finalUrl = finalUrl;
	}

}

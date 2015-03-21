package com.rssfeedreader.rss;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class RssObject implements Parcelable {
	private String rssTitle;
	private String rssDescription;
	private String rssImage;
	private Bitmap bitmap;

	public RssObject(String rssTitle, String rssDescription, String rssImage) {
		this.setRssTitle(rssTitle);
		this.setRssDescription(rssDescription);
		this.setRssImage(rssImage);
	}

	public RssObject() {
	}

	public String getRssTitle() {
		return rssTitle;
	}

	public void setRssTitle(String rssTitle) {
		this.rssTitle = rssTitle;
	}

	public String getRssDescription() {
		return rssDescription;
	}

	public void setRssDescription(String rssDescription) {
		this.rssDescription = rssDescription;
	}

	public String getRssImage() {
		return rssImage;
	}

	public void setRssImage(String rssImage) {
		this.rssImage = rssImage;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public static final Parcelable.Creator<RssObject> CREATOR = new Parcelable.Creator<RssObject>() {

		public RssObject createFromParcel(Parcel in) {
			return new RssObject(in);
		}

		public RssObject[] newArray(int size) {
			return new RssObject[size];
		}
	};

	private RssObject(Parcel in) {
		rssTitle = in.readString();
		rssImage = in.readString();
		rssDescription = in.readString();
		bitmap = (Bitmap) in.readParcelable(getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(rssTitle);
		dest.writeString(rssImage);
		dest.writeString(rssDescription);
		dest.writeParcelable(bitmap, flags);

	}

}

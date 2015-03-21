package com.rssfeedreader.rss;

import java.util.ArrayList;
import java.util.List;

public class RssObjects {
	private List<RssObject> allObjects;
	private List<RssObject> filteredObjects;

	public RssObjects(List<RssObject> objects) {
		setAllObjects(objects);
		setFilteredObjects(objects);
	}

	public List<RssObject> applyFilter(String filter) {
		setFilteredObjects(new ArrayList<RssObject>());
		for (RssObject object : getAllObjects())
			if (object.getRssDescription().contains(filter)
					|| object.getRssTitle().contains(filter))
				getFilteredObjects().add(object);

		return getFilteredObjects();
	}

	public List<RssObject> getAllObjects() {
		return allObjects;
	}

	private void setAllObjects(List<RssObject> allObjects) {
		this.allObjects = allObjects;
	}

	public List<RssObject> getFilteredObjects() {
		return filteredObjects;
	}

	private void setFilteredObjects(List<RssObject> filteredObjects) {
		this.filteredObjects = filteredObjects;
	}

	public RssObject[] allObjectsToArray() {
		return allObjects.toArray(new RssObject[allObjects.size()]);
	}

	public RssObject[] filteredObjectsToArray() {
		return filteredObjects.toArray(new RssObject[filteredObjects.size()]);
	}

	public RssObject get(int position) {
		return filteredObjects.get(position);
	}
}

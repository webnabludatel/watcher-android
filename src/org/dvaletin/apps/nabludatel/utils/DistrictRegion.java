package org.dvaletin.apps.nabludatel.utils;

public class DistrictRegion {
	private final int id;
	private final String title;

	public DistrictRegion(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public static int indexOfRegionId(int regionId, DistrictRegion... regions) {
		for (int i = 0, regionsLength = regions.length; i < regionsLength; i++) {
			DistrictRegion region = regions[i];
			if (region.getId() == regionId) {
				return i;
			}
		}
		return -1;
	}
}

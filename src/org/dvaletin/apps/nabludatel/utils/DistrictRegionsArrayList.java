package org.dvaletin.apps.nabludatel.utils;

import java.util.ArrayList;

public final class DistrictRegionsArrayList {
	ArrayList<DistrictRegion> regions;
	public DistrictRegionsArrayList(){
		regions = new ArrayList<DistrictRegion>();
		regions.add(new DistrictRegion("Город Москва", "77"));
		regions.add(new DistrictRegion("Город Санкт-Петербург", "78"));
	}
	public ArrayList<DistrictRegion> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<DistrictRegion> regions) {
		this.regions = regions;
	}
	
}

package org.dvaletin.apps.nabludatel.utils;

public class DistrictRegion {
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	String value;
	public DistrictRegion(String t, String v){
		title = t;
		value = v;
	}
}

package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DistrictRegionAdapter extends BaseAdapter {
	private DistrictRegion[] regions;
	private LayoutInflater mInflater;
	
	public DistrictRegionAdapter(Context context, DistrictRegion... regions){
		this.regions = regions;
		this.mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return regions.length;
	}

	@Override
	public String getItem(int position) {
		return regions[position].getTitle();
	}

	@Override
	public long getItemId(int position) {
		return regions[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
}

package org.dvaletin.apps.nabludatel.utils;


import org.dvaletin.apps.nabludatel.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.district_view_item_layout,
					null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.list_view_item);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(regions[position].getTitle());


		return convertView;
	}
	
	public class ViewHolder {
		TextView txtTitle;
	}
}

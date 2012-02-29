package org.dvaletin.apps.nabludatel.utils;

import java.util.ArrayList;

import org.dvaletin.apps.nabludatel.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

public class DistrictRegionAdapter extends BaseAdapter {
	private ArrayList<DistrictRegion> regions;
	private LayoutInflater mInflater;
	
	public DistrictRegionAdapter(Context context, ArrayList<DistrictRegion> items){
		setRegions(items);
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return regions.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return regions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_view_item_layout,
					null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.list_view_item_title);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(regions.get(position).getTitle());


		return convertView;
	}
	public ArrayList<DistrictRegion> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<DistrictRegion> regions) {
		this.regions = regions;
	}
	public class ViewHolder {
		TextView txtTitle;
	}
}

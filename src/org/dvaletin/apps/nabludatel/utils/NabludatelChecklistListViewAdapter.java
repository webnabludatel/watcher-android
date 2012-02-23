package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.R;
import org.dvaletin.apps.nabludatel.NabludatelActivity.NabludatelCustomListViewAdapter.ViewHolder;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NabludatelChecklistListViewAdapter extends android.widget.BaseAdapter {
	SectionList items;
	private LayoutInflater mInflater;
	
	public NabludatelChecklistListViewAdapter(Context context, SectionList pItems){
		items = pItems;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.listItems.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.listItems[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_view_item_layout,
					null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView.findViewById(R.id.list_view_item_title);
			holder.txtDescription = (TextView) convertView
					.findViewById(R.id.list_view_item_description);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(items.listItems[position].getTitle());
		holder.txtDescription.setText(items.listItems[position].getViolationsDescription());
		
		return convertView;
	}

	public class ViewHolder {
		TextView txtTitle;
		TextView txtDescription;
	}
}

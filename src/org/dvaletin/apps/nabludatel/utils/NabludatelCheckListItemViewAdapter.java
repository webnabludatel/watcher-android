package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.dvaletin.apps.nabludatel.R;

public class NabludatelCheckListItemViewAdapter extends
		android.widget.BaseAdapter {
	SectionList items;
	private LayoutInflater mInflater;
	private int[] violations;

	public NabludatelCheckListItemViewAdapter(Context context,
			SectionList pItems) {
		items = pItems;
		mInflater = LayoutInflater.from(context);
		violations = new int[items.listItems.length];
		for (int i = 0; i < violations.length; i++) {
			violations[i] = 0;
		}
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
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.list_view_item_title);
			holder.txtDescription = (TextView) convertView
					.findViewById(R.id.list_view_item_description);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(items.listItems[position].getTitle());
		holder.txtDescription.setText(Consts
				.getViolationDescription(violations[position]));

		return convertView;
	}

	public void updateViolations(int position, int v) {
		violations[position] = v;
	}

	public class ViewHolder {
		TextView txtTitle;
		TextView txtDescription;
	}

	public int getViolationsCount(int i) {
		// TODO Auto-generated method stub
		return violations[i];
	}

	public int getTotalVioltionsCount() {
		int sum = 0;
		for (int i = 0; i < getCount(); i++) {
			sum += violations[i];
		}
		return sum;
	}

	public void updateViolationCount(ElectionsDBHelper mElectionsDB) {
		for (int i = 0; i < this.getCount(); i++) {
			this.updateViolations(
					i,
					mElectionsDB.getCheckListItemsCountByScreenId(items.listItems[i].LAYOUT)
			);
		}
	}
}

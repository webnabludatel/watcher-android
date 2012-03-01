package org.dvaletin.apps.nabludatel;

import java.util.ArrayList;

import org.dvaletin.apps.nabludatel.utils.*;
import org.dvaletin.apps.nabludatel.utils.NabludatelCheckListItemViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NabludatelActivity extends ABSNabludatelActivity {

	NabludatelCustomListViewAdapter mRootListViewAdapter;
	NabludatelCheckListItemViewAdapter mBeforeElectionsAdapter;
	NabludatelCheckListItemViewAdapter mDuringElectionsAdapter;
	NabludatelCheckListItemViewAdapter mFinalMeetingAdapter;
	NabludatelCheckListItemViewAdapter mCountingAdapter;
	private NabludatelCheckListItemViewAdapter mTikIkmoAdapter;
	private NabludatelCustomListViewAdapter mTikIkmoViewAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		activateRootMenu();
	}

	public void activateRootMenu() {
		setContentView(R.layout.main);
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.INVISIBLE);
		}
		Spinner district = (Spinner) findViewById(R.id.elections_district_spinner);
		String from[] = new String[] { ElectionsDBHelper.POLLINGPLACE_NAME_KEY };
		int[] to = new int[] { android.R.id.text1 };
		Cursor c = mElectionsDB.getPollingPlaceNames();
		if (c.getCount() > 0) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					android.R.layout.simple_spinner_item, c, from, to);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			district.setAdapter(adapter);
			
			getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
			mCurrentPollingPlaceId = prefs.getLong(
					Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);
			if (mCurrentPollingPlaceId > 0) {
				district.setSelection((int) mCurrentPollingPlaceId-1);
				mCurrentPollingPlaceType = mElectionsDB
						.getPollingPlaceType(mCurrentPollingPlaceId);
			}
			district.setOnLongClickListener(new View.OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					long id = prefs.getLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1L);
					if (id != -1L) {
						Intent intent = new Intent(NabludatelActivity.this, ElectionsDistrictActivity.class);
						intent.putExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID, id);
						startActivityForResult(intent, Consts.DISTRICT_ACTIVITY_REQUEST_CODE);
					}
					return true;
				}
			});

			district.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					long prev_item_id = prefs.getLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);
					if (id != prev_item_id) {

						prefs.edit()
								.putLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, id)
								.commit();
						NabludatelActivity.this.activateRootMenu();
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}

			});
		} else {
			// district.setVisibility(View.INVISIBLE);
		}
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mRootListViewAdapter == null && c.getCount() > 0) {
			ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();

			for (int i = 0; i < Consts.ROOT_MENU_ITEMS.length; i++) {
				mListViewItems.add(new NabludatelListViewItem(
						Consts.ROOT_MENU_ITEMS[i],
						Consts.ROOT_MENU_DESCRIPTIONS[i]));
			}
			mRootListViewAdapter = new NabludatelCustomListViewAdapter(this,
					mListViewItems);
		}

		if (mTikIkmoViewAdapter == null && c.getCount() > 0) {
			ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
			for (int i = 0; i < Consts.TIK_IKMO_MENU_ITEMS.length; i++) {
				mListViewItems.add(new NabludatelListViewItem(
						Consts.TIK_IKMO_MENU_ITEMS[i],
						Consts.TIK_IKMO_MENU_DESCRIPTIONS[i]));
			}
			mTikIkmoViewAdapter = new NabludatelCustomListViewAdapter(this,
					mListViewItems);
		}

		if (mCurrentPollingPlaceType == null) {
			
		} else if (mCurrentPollingPlaceType.equals(Consts.POLLING_PLACE_TYPE[0])) {
			mMainSelector.setAdapter(mRootListViewAdapter);
		} else {
			mMainSelector.setAdapter(mTikIkmoViewAdapter);
		}

		OnItemClickListener mUIKClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				switch (pItemPosition) {
				case 0: {
					NabludatelActivity.this.activateSectionBeforeElections();
					break;
				}
				case 1: {
					NabludatelActivity.this.activateSectionDuringElections();
					break;
				}
				case 2: {
					NabludatelActivity.this.activateSectionCounting();
					break;
				}
				case 3: {
					NabludatelActivity.this.activateSectionFinalMeeting();
					break;
				}
				case 4: {

					break;
				}
				case 5: {

					break;
				}
				default: {
					break;
				}
				}

			}

		};

		OnItemClickListener mTIKClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				switch (pItemPosition) {
				case 0: {
					NabludatelActivity.this.activateSectionTikIkmo();
					break;
				}
				default: {
					break;
				}
				}

			}

		};

		if(mCurrentPollingPlaceType == null){
		} else if (mCurrentPollingPlaceType.equals(Consts.POLLING_PLACE_TYPE[0])) {
			mMainSelector.setOnItemClickListener(mUIKClickListener);
		} else {
			mMainSelector.setOnItemClickListener(mTIKClickListener);
		}

	}

	protected void activateSectionBeforeElections() {
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.VISIBLE);
			back_button_old.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					NabludatelActivity.this.onBackPressed();

				}

			});
		}
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mBeforeElectionsAdapter == null) {

			mBeforeElectionsAdapter = new NabludatelCheckListItemViewAdapter(
					this, new SectionBeforeElections());
		}

		mMainSelector.setAdapter(mBeforeElectionsAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem) mBeforeElectionsAdapter
								.getItem(pItemPosition)).getTitle(),
						((ListViewActivityItem) mBeforeElectionsAdapter
								.getItem(pItemPosition)).getLayout(),
						((ListViewActivityItem) mBeforeElectionsAdapter
								.getItem(pItemPosition)).getActivity());
			}
		});
	}

	public void activateSectionDuringElections() {
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.VISIBLE);
			back_button_old.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					NabludatelActivity.this.onBackPressed();

				}

			});
		}
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mDuringElectionsAdapter == null) {

			mDuringElectionsAdapter = new NabludatelCheckListItemViewAdapter(
					this, new SectionDuringElections());
		}

		mMainSelector.setAdapter(mDuringElectionsAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem) mDuringElectionsAdapter
								.getItem(pItemPosition)).getTitle(),
						((ListViewActivityItem) mDuringElectionsAdapter
								.getItem(pItemPosition)).getLayout(),
						((ListViewActivityItem) mDuringElectionsAdapter
								.getItem(pItemPosition)).getActivity());
			}

		});

	}

	public void activateSectionCounting() {
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.VISIBLE);
			back_button_old.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					NabludatelActivity.this.onBackPressed();

				}

			});
		}
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mCountingAdapter == null) {
			mCountingAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionCounting());
		}

		mMainSelector.setAdapter(mCountingAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem) mCountingAdapter
								.getItem(pItemPosition)).getTitle(),
						((ListViewActivityItem) mCountingAdapter
								.getItem(pItemPosition)).getLayout(),
						((ListViewActivityItem) mCountingAdapter
								.getItem(pItemPosition)).getActivity());
			}

		});

	}

	public void activateSectionFinalMeeting() {
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.VISIBLE);
			back_button_old.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					NabludatelActivity.this.onBackPressed();

				}

			});
		}
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mFinalMeetingAdapter == null) {
			mFinalMeetingAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionFinalMeeting());
		}

		mMainSelector.setAdapter(mFinalMeetingAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem) mFinalMeetingAdapter
								.getItem(pItemPosition)).getTitle(),
						((ListViewActivityItem) mFinalMeetingAdapter
								.getItem(pItemPosition)).getLayout(),
						((ListViewActivityItem) mFinalMeetingAdapter
								.getItem(pItemPosition)).getActivity());
			}

		});

	}

	public void activateSectionTikIkmo() {
		Button back_button_old = (Button) findViewById(R.id.back_button_old);
		if (back_button_old != null) {
			back_button_old.setVisibility(View.VISIBLE);
			back_button_old.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					NabludatelActivity.this.onBackPressed();

				}

			});
		}
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mTikIkmoAdapter == null) {
			mTikIkmoAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionTikIkmo());
		}

		mMainSelector.setAdapter(mTikIkmoAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem) mTikIkmoAdapter
								.getItem(pItemPosition)).getTitle(),
						((ListViewActivityItem) mTikIkmoAdapter
								.getItem(pItemPosition)).getLayout(),
						((ListViewActivityItem) mTikIkmoAdapter
								.getItem(pItemPosition)).getActivity());
			}

		});

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
		int violations = -1;
		if (data != null)
			violations = data.getIntExtra(Consts.PREFS_VIOLATIONS, -1);
		if (violations >= 0 && mBeforeElectionsAdapter != null) {
			for (int i = 0; i < mBeforeElectionsAdapter.getCount(); i++) {
				if (((ListViewActivityItem) mBeforeElectionsAdapter.getItem(i))
						.getLayout() == requestCode) {
					mBeforeElectionsAdapter.updateViolations(i, violations);
					mMainSelector.setAdapter(mBeforeElectionsAdapter);
					break;
				}
			}
		}
		switch (requestCode) {
		case Consts.DISTRICT_ACTIVITY_REQUEST_CODE: {
			if (data != null) {
				long id = data.getLongExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);
				if (id != -1) {
					prefs.edit().putLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, id).commit();
					activateRootMenu();
				}
			}

			break;
		}
		}

	}

	@Override
	public void onBackPressed() {

		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mMainSelector.getAdapter() != mRootListViewAdapter) {
			activateRootMenu();
		} else {
			super.onBackPressed();
		}
	}

	public class NabludatelListViewItem {
		String mTitle;
		String mDescription;

		public NabludatelListViewItem(String pTitle, String pDescription) {
			mTitle = pTitle;
			mDescription = pDescription;
		}

		public String getTitle() {
			return mTitle;
		}

		public String getDescription() {
			return mDescription;
		}
	}

	public class NabludatelCustomListViewAdapter extends BaseAdapter {
		private ArrayList<NabludatelListViewItem> mItemsArrayList;

		private LayoutInflater mInflater;

		public NabludatelCustomListViewAdapter(Context context,
				ArrayList<NabludatelListViewItem> items) {
			mItemsArrayList = items;
			mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return mItemsArrayList.size();
		}

		public Object getItem(int position) {
			return mItemsArrayList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

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

			holder.txtTitle.setText(mItemsArrayList.get(position).getTitle());
			holder.txtDescription.setText(mItemsArrayList.get(position)
					.getDescription());

			return convertView;
		}

		public class ViewHolder {
			TextView txtTitle;
			TextView txtDescription;
		}
	}

	public void onReportButtonClick(View v) {
		Intent intent = new Intent(this, NabludatelReportActivity.class);
		startActivity(intent);
	}

	public void onProfileButtonClick(View v) {
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		startActivity(intent);
	}

	public void onPollingPlaceAddClick(View v) {
		Intent intent = new Intent(this, ElectionsDistrictActivity.class);
		startActivityForResult(intent, Consts.DISTRICT_ACTIVITY_REQUEST_CODE);
	}

	public void startNabludatelActivity(String title, int layoutId,
			Class<? extends ABSNabludatelActivity> c) {
		long mCurrentElectionsDistrict = prefs.getLong(
				Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);

		Intent intent = new Intent(this, c);
		if (mCurrentElectionsDistrict != -1) {
			intent.putExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID,
					mCurrentElectionsDistrict);
		}
		intent.putExtra(Consts.PREFS_TITLE, title);
		intent.putExtra(Consts.PREFS_LAYOUT_ID, layoutId);
		intent.putExtra(Consts.PREFS_LATITUDE, lat);
		intent.putExtra(Consts.PREFS_LONGITUDE, lng);
		this.startActivityForResult(intent, layoutId);

	}
}

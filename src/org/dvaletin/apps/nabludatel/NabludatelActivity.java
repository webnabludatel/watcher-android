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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NabludatelActivity extends ABSNabludatelActivity {

	NabludatelCustomListViewAdapter mUikListViewAdapter;
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

	public void onElectionDistrictsTitleInfoClick(View v) {
		showInfoDialog(R.string.elections_districts_hint);
	}

	public void onWatchingTitleInfoClick(View v) {
		showInfoDialog(R.string.watching_hint);
	}

	public void activateRootMenu() {
		setTitle("Наблюдение");
		setContentView(R.layout.main);
		Spinner district = (Spinner) findViewById(R.id.elections_district_spinner);
		String from[] = new String[] { ElectionsDBHelper.POLLINGPLACE_NAME_KEY };
		int[] to = new int[] { android.R.id.text1 };
		Cursor c = mElectionsDB.getPollingPlaceNames();
		try {
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

					findViewById(R.id.main_layout).setVisibility(View.VISIBLE);
					findViewById(R.id.watchingTitlePane).setVisibility(View.VISIBLE);
				} else {
					findViewById(R.id.main_layout).setVisibility(View.INVISIBLE);
					findViewById(R.id.watchingTitlePane).setVisibility(View.INVISIBLE);
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
					}

				});
				fillCheckListItems();
				ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
				if(mBeforeElectionsAdapter != null)
					mUikListViewAdapter.getListViewItem(0).setDescription(this.mBeforeElectionsAdapter.getTotalVioltionsCount());

				if(this.mCountingAdapter != null)
					mUikListViewAdapter.getListViewItem(2).setDescription(mCountingAdapter.getTotalVioltionsCount());

				if(this.mDuringElectionsAdapter != null)
					mUikListViewAdapter.getListViewItem(1).setDescription(mDuringElectionsAdapter.getTotalVioltionsCount());

				if(this.mFinalMeetingAdapter != null)
					mUikListViewAdapter.getListViewItem(3).setDescription(mFinalMeetingAdapter.getTotalVioltionsCount());

				if(this.mTikIkmoAdapter != null)
					this.mTikIkmoViewAdapter.getListViewItem(0).setDescription(mTikIkmoAdapter.getTotalVioltionsCount());

				mMainSelector.invalidateViews();

			} else {
				findViewById(R.id.main_layout).setVisibility(View.INVISIBLE);
				findViewById(R.id.watchingTitlePane).setVisibility(View.INVISIBLE);
			}
		} finally {
			c.close();
		}
	}

	private void fillCheckListItems() {
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mUikListViewAdapter == null) {
			ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();

			for (int i = 0; i < Consts.ROOT_MENU_ITEMS.length; i++) {
				mListViewItems.add(new NabludatelListViewItem(
						Consts.ROOT_MENU_ITEMS[i],
						Consts.ROOT_MENU_DESCRIPTIONS[i]));
			}
			mUikListViewAdapter = new NabludatelCustomListViewAdapter(this,
					mListViewItems);
			
		}

		if (mTikIkmoViewAdapter == null) {
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
			mMainSelector.setAdapter(mUikListViewAdapter);
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
		
		if (mBeforeElectionsAdapter == null) {

			mBeforeElectionsAdapter = new NabludatelCheckListItemViewAdapter(
					this, new SectionBeforeElections());
			mBeforeElectionsAdapter.updateViolationCount(mElectionsDB);
		}
		
		if (mDuringElectionsAdapter == null) {

			mDuringElectionsAdapter = new NabludatelCheckListItemViewAdapter(
					this, new SectionDuringElections());
			mDuringElectionsAdapter.updateViolationCount(mElectionsDB);
		}
		
		if (mCountingAdapter == null) {
			mCountingAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionCounting());
			mCountingAdapter.updateViolationCount(mElectionsDB);
		}
		
		if (mFinalMeetingAdapter == null) {
			mFinalMeetingAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionFinalMeeting());
			mFinalMeetingAdapter.updateViolationCount(mElectionsDB);
		}
		
		if (mTikIkmoAdapter == null) {
			mTikIkmoAdapter = new NabludatelCheckListItemViewAdapter(this,
					new SectionTikIkmo());
			mTikIkmoAdapter.updateViolationCount(mElectionsDB);
		}
	}

	protected void activateSectionBeforeElections() {
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		
		setTitle("Открытие участка");
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
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		
		setTitle("Голосование");
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
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		
		setTitle("Подсчет");
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
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		
		setTitle("Заседание и протокол");
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
		FrameLayout elections_district_select_frame = (FrameLayout) findViewById(R.id.elections_district_select_frame);
		elections_district_select_frame.setVisibility(View.GONE);
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		
		setTitle("ТИК/ИКМО");
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
		
		if(mMainSelector.getAdapter() instanceof NabludatelCheckListItemViewAdapter){
			((NabludatelCheckListItemViewAdapter)mMainSelector.getAdapter()).updateViolationCount(this.mElectionsDB);
			mMainSelector.invalidateViews();
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

		if (mMainSelector.getAdapter() != mUikListViewAdapter) {
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
		
		public void setDescription(String desc){
			mDescription = desc;
		}
		
		public void setDescription(int iDesc){
			setDescription(Consts.getViolationDescription(iDesc));
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
		
		public NabludatelListViewItem getListViewItem(int position){
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

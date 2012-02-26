package org.dvaletin.apps.nabludatel;


import java.util.ArrayList;

//<<<<<<< HEAD

import org.dvaletin.apps.nabludatel.utils.*;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.NabludatelChecklistListViewAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NabludatelActivity extends ABSNabludatelActivity {
	NabludatelCloud mNabludatelCloud;
	String deviceId;
	JSONObject mainJSON;
	protected static final String TAG_CAMERA = "Camera";
	
	NabludatelCustomListViewAdapter mRootListViewAdapter;
	NabludatelChecklistListViewAdapter mBeforeElectionsAdapter, mDuringElectionsAdapter;
	NabludatelChecklistListViewAdapter mAfterElectionsListViewAdapter;
	NabludatelChecklistListViewAdapter mFinalMeetingAdapter;
	NabludatelChecklistListViewAdapter mCountingAdapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
        deviceId = t.getDeviceId();
    	mNabludatelCloud = new NabludatelCloud(t.getDeviceId());
    	setContentView(R.layout.main);	
    	activateRootMenu();
    }
    
    public void activateRootMenu(){
    	Spinner district = (Spinner) findViewById(R.id.elections_district_spinner);
    	String from [] = new String[]{ElectionsDBHelper.POLLINGPLACE_NAME_KEY};
    	int[] to = new int[]{android.R.id.text1};
    	Cursor c = mElectionsDB.open().getPollingPlaceNumbers();
    	mElectionsDB.close();
    	if(c.getCount()>0){
	    	SimpleCursorAdapter adapter =
	    		  new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, 
	    				  from, to );
	    	adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
	    	district.setAdapter(adapter);
	    	district.setVisibility(View.VISIBLE);
	    	getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
	    	Long position_id = prefs.getLong(Consts.PREFS_ELECTIONS_DISRICT, -1);
	    	if(position_id != -1) 
	    		district.setSelection((int)(position_id-1));
	    	district.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					long prev_item_id = prefs.getLong(Consts.PREFS_ELECTIONS_DISRICT, -1);
					if(id != prev_item_id){
						
					
						prefs.edit().putLong(Consts.PREFS_ELECTIONS_DISRICT, id).commit();
						NabludatelActivity.this.activateRootMenu();
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
	    		
	    	});
    	}else{
//    		district.setVisibility(View.INVISIBLE);
    	}
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
    	
    	if(mRootListViewAdapter == null && c.getCount() > 0){
	    	ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
	    	
	    	for(int i=0; i<Consts.ROOT_MENU_ITEMS.length; i++){
	    		mListViewItems.add(new NabludatelListViewItem(Consts.ROOT_MENU_ITEMS[i], Consts.ROOT_MENU_DESCRIPTIONS[i]));
	    	}
	    	mRootListViewAdapter = new NabludatelCustomListViewAdapter(this, mListViewItems);
    	}
	    
    	mMainSelector.setAdapter(mRootListViewAdapter);
	    
    	mMainSelector.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				switch(pItemPosition){
					case 0:{
						NabludatelActivity.this.activateSectionBeforeElections();
						break;
					}
					case 1:{
						NabludatelActivity.this.activateSectionDuringElections();
						break;
					}
					case 2:{
						NabludatelActivity.this.activateSectionCounting();
						break;
					}
					case 3:{
						NabludatelActivity.this.activateSectionFinalMeeting();
						break;
					}
					case 4:{
						
						break;
					}
					case 5:{
						
						break;
					}
					default:{
						break;
					}
				}
				
			}
        	
        });
        
    }
    
    
    protected void activateSectionBeforeElections() {
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mBeforeElectionsAdapter == null){
	    	
	    	mBeforeElectionsAdapter = new NabludatelChecklistListViewAdapter(this, new SectionBeforeElections());
    	}          
    	
    	
        
        mMainSelector.setAdapter(mBeforeElectionsAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){
			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem)mBeforeElectionsAdapter.getItem(pItemPosition))
							.getTitle(),
						((ListViewActivityItem)mBeforeElectionsAdapter.getItem(pItemPosition))
							.getLayout(), 
						((ListViewActivityItem)mBeforeElectionsAdapter.getItem(pItemPosition))
							.getActivity());
			}
        });
	}

    public void activateSectionDuringElections(){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mDuringElectionsAdapter == null){
	    	
	    	mDuringElectionsAdapter = new NabludatelChecklistListViewAdapter(this, new SectionDuringElections());
    	}          
        
        mMainSelector.setAdapter(mDuringElectionsAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

        	@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem)mDuringElectionsAdapter.getItem(pItemPosition))
							.getTitle(),
						((ListViewActivityItem)mDuringElectionsAdapter.getItem(pItemPosition))
							.getLayout(), 
						((ListViewActivityItem)mDuringElectionsAdapter.getItem(pItemPosition))
							.getActivity());
			}
        	
        });
		
    }

    public void activateSectionCounting(){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mCountingAdapter == null){
    		mCountingAdapter = new NabludatelChecklistListViewAdapter(this, new SectionCounting());
    	}          
        
        mMainSelector.setAdapter(mCountingAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

        	@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem)mCountingAdapter.getItem(pItemPosition))
							.getTitle(),
						((ListViewActivityItem)mCountingAdapter.getItem(pItemPosition))
							.getLayout(), 
						((ListViewActivityItem)mCountingAdapter.getItem(pItemPosition))
							.getActivity());
			}
        	
        });
		
    }
    
	public void activateSectionFinalMeeting() {
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mFinalMeetingAdapter == null) {
			mFinalMeetingAdapter = new NabludatelChecklistListViewAdapter(
					this, new SectionFinalMeeting());
		}

		mMainSelector.setAdapter(mFinalMeetingAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				NabludatelActivity.this.startNabludatelActivity(
						((ListViewActivityItem)mFinalMeetingAdapter.getItem(pItemPosition))
							.getTitle(),
						((ListViewActivityItem)mFinalMeetingAdapter.getItem(pItemPosition))
							.getLayout(), 
						((ListViewActivityItem)mFinalMeetingAdapter.getItem(pItemPosition))
							.getActivity());
			}

		});

	}
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
    	int violations = -1;
    	if( data != null ) 
    		violations = data.getIntExtra(Consts.PREFS_VIOLATIONS, -1);
    	if(violations >= 0 && mBeforeElectionsAdapter!=null){
    		for(int i = 0; i < mBeforeElectionsAdapter.getCount(); i++){
    			if(((ListViewActivityItem)mBeforeElectionsAdapter.getItem(i)).getLayout() == requestCode){
    				mBeforeElectionsAdapter.updateViolations(i, violations);
    				mMainSelector.setAdapter(mBeforeElectionsAdapter);
    				break;
    			}
    		}
    	}
    	switch(requestCode){
    	case Consts.ACTIVITY_RESULT_NEW_ELECTIONS_DISTRICT:{
    		if(data != null){
    			long id = data.getLongExtra(Consts.PREFS_ELECTIONS_DISRICT, -1);
    			if(id != -1){
    				prefs.edit().putLong(Consts.PREFS_ELECTIONS_DISRICT, id).commit();
    				activateRootMenu();
    			}
    		}
    		
    		break;
    	}
    	case R.layout.elections_district_profile:{

			JSONObject json = null;
			try{
				json = new JSONObject(data.getStringExtra(Consts.ACTIVITY_JSON_DATA));
			} catch (JSONException e){
				e.printStackTrace();
			}

			try {
				json.put("URL", uploadPhotos( (JSONArray) json.get("URL")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			sendJSON(json);
			
			((NabludatelListViewItem) this.mRootListViewAdapter.getItem(0)).setDescription(Consts.getViolationDescription(json.length()));
		
			try {
				mainJSON.put("section_elections_district", json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	}
    	}
    	
    }
    
    
    public void sendJSON(JSONObject toSend){
    	//TODO 
    }

	public JSONArray uploadPhotos(JSONArray photos) {
		return null;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if(keyCode != KeyEvent.KEYCODE_BACK){
    		return false;
    	}
    	
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
    	
    	if(mMainSelector.getAdapter() != mRootListViewAdapter){
    		activateRootMenu();
    		return true;
    	}else{
    		this.finish();
    	}
    	return false;
    }
	
	
	public class NabludatelListViewItem {
		String mTitle;
		String mDescription;
		JSONObject json;
		
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
		
		public void setDescription(String pDescription){
			mDescription = pDescription;
		}
		
		public void setTite(String pTitle){
			mTitle = pTitle;
		}
		
		public void setJSON(JSONObject jsonToSet){
			json = jsonToSet;
		}
		
		public JSONObject getJSON(){
			return json;
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
				holder.txtTitle = (TextView) convertView.findViewById(R.id.list_view_item_title);
				holder.txtDescription = (TextView) convertView
						.findViewById(R.id.list_view_item_description);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txtTitle.setText(mItemsArrayList.get(position).getTitle());
			holder.txtDescription.setText(mItemsArrayList.get(position).getDescription());
			
			return convertView;
		}

		public class ViewHolder {
			TextView txtTitle;
			TextView txtDescription;
		}
	}
	
	public void onReportButtonClick(View v){
		Intent intent = new Intent(this, NabludatelReportActivity.class);
		startActivity(intent);
	}
	
	public void onProfileButtonClick(View v){
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		startActivity(intent);
	}
	
	
	
	public void onElectionsDictrictAddClick(View v)
	{
		Intent intent = new Intent(this, ElectionsDistrictActivity.class);
		startActivityForResult(intent, Consts.ACTIVITY_RESULT_NEW_ELECTIONS_DISTRICT);
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
//		activateRootMenu();
	}
	

    public void startNabludatelActivity(String title, int layoutId, Class<? extends ABSNabludatelActivity> c){
    	long mCurrentElectionsDistrict = prefs.getLong(Consts.PREFS_ELECTIONS_DISRICT, -1);
    	
    	Intent intent = new Intent(this, c);
    	if(mCurrentElectionsDistrict != -1){
    		intent.putExtra(Consts.PREFS_ELECTIONS_DISRICT, mCurrentElectionsDistrict);
    	}
		intent.putExtra(Consts.PREFS_TITLE, title);
		intent.putExtra(Consts.PREFS_LAYOUT_ID, layoutId);
		intent.putExtra(Consts.PREFS_LATITUDE, lat);
		intent.putExtra(Consts.PREFS_LONGITUDE, lng);
		this.startActivityForResult(intent, layoutId);
    	
	}
}

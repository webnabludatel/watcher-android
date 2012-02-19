package org.dvaletin.apps.nabludatel;


import java.io.File;
import java.util.ArrayList;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.GenericSQLHelper;
import org.dvaletin.apps.nabludatel.utils.S3Helper;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NabludatelActivity extends ABSNabludatelActivity {
	S3Helper mS3Helper;
	String deviceId;
	JSONObject mainJSON;
	protected static final String TAG_CAMERA = "Camera";
	
	NabludatelCustomListViewAdapter mRootListViewAdapter, mElectionsDistrictAdapter, mBeforeElectionsAdapter;
	NabludatelCustomListViewAdapter mDuringElectionsListViewAdapter, mAfterElectionsListViewAdapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
        deviceId = t.getDeviceId();
    	mS3Helper = new S3Helper(t.getDeviceId());
    	setContentView(R.layout.main);
    	try {
			mainJSON = new JSONObject(prefs.getString(Consts.ACTIVITY_JSON_DATA, ""));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mainJSON = new JSONObject();
		}	
    	activateRootMenu();
    }
    
    public void activateRootMenu(){
    	Spinner district = (Spinner) findViewById(R.id.elections_district_spinner);
    	String from [] = new String[]{GenericSQLHelper.POLLINGPLACE_NUMBER_KEY};
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
	    	prefs = this.getPreferences(MODE_PRIVATE);
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
    		district.setVisibility(View.INVISIBLE);
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
	    	ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
	    	
	    	for(int i=0; i<Consts.SECTION_BEFORE_ELECTIONS.length; i++){
	    		mListViewItems.add(new NabludatelListViewItem(Consts.SECTION_BEFORE_ELECTIONS[i], Consts.SECTION_BEFORE_ELECTIONS_DESCRIPTIONS[i]));
	    	}
	    	mBeforeElectionsAdapter = new NabludatelCustomListViewAdapter(this, mListViewItems);
    	}          
    	
    	
        
        mMainSelector.setAdapter(mBeforeElectionsAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				Intent mIntentToStart = null;
				int mActivityResult = 0;
				switch(pItemPosition){
				case 0:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_admitted_before_eight, SectionBeforeElectionsAdmittedBeforeEight.class);
					break;
				}
				case 1:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_bullot_box, SectionBeforeElectionsBullotBox.class);
					break;
				}
				case 2:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_koib, SectionBeforeElectionsKoib.class);
					break;
				}
				case 3:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_appearance, SectionBeforeElectionsAppearance.class);
					break;
				}
				case 4:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_voters,  SectionBeforeElectionsVoters.class);
					break;
				}
				case 5:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_before_elections_voters_count, SectionBeforeElectionsVotersCount.class);
					break;
				}
				default:{
					break;
				}
				}

			}
        	
        });
		
	}

    public void activateSectionDuringElections(){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mDuringElectionsListViewAdapter == null){
	    	ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
	    	
	    	for(int i=0; i<Consts.SECTION_DURING_ELECTIONS.length; i++){
	    		mListViewItems.add(new NabludatelListViewItem(Consts.SECTION_DURING_ELECTIONS[i], Consts.SECTION_DURING_ELECTIONS_DESCRIPTIONS[i]));
	    	}
	    	mDuringElectionsListViewAdapter = new NabludatelCustomListViewAdapter(this, mListViewItems);
    	}          
        
        mMainSelector.setAdapter(mDuringElectionsListViewAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				Intent mIntentToStart = null;
				int mActivityResult = 0;
				switch(pItemPosition){
				case 0:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_attendance, SectionDuringElectionsAttendance.class);
					break;
				}
				case 1:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_ballot, SectionDuringElectionsBullot.class);
					break;
				}
				case 2:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_ballot_process_pressure, SectionDuringElectionsBallotProcessPressure.class);
					break;
				}
				case 3:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_suspicious_voters, SectionDuringElectionsSuspiciousVouters.class);
					break;
				}
				case 4:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_bundle_of_ballots, SectionDuringElectionsBundleOfBallots.class);
					break;
				}
				case 5:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_during_elections_absentee_vote, SectionDuringElectionsAbsenteeVote.class);
					break;
				}
				default:{
					mIntentToStart = null;
					break;
				}
				}
			}
        	
        });
		
    }

    public void activateSectionCounting(){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mAfterElectionsListViewAdapter == null){
	    	ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
	    	
	    	for(int i=0; i<Consts.SECTION_COUNTING.length; i++){
	    		mListViewItems.add(new NabludatelListViewItem(Consts.SECTION_COUNTING[i], Consts.SECTION_COUNTING_DESCRIPTIONS[i]));
	    	}
	    	mAfterElectionsListViewAdapter = new NabludatelCustomListViewAdapter(this, mListViewItems);
    	}          
        
        mMainSelector.setAdapter(mAfterElectionsListViewAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				Intent mIntentToStart = null;
				int mActivityResult = 0;
				switch(pItemPosition){
				case 0:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_unused_ballots_counted_after_vote_finish, SectionCountingUnusedBallots.class);
					break;
				}
				case 1:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_unused_ballots_counted_after_vote_finish, SectionCountingConfirmedVoters.class);
					break;
				}
				case 2:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_ballot_box, SectionCountingBallotBox.class);
					break;
				}
				case 3:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_absentee_ballot, SectionCountingAbsenteeBallot.class);
					break;
				}
				case 4:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_counting_ballots, SectionCountingCountingBullots.class);
					break;
				}
				case 5:{
					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_control_calculations, SectionCountingControlCalculations.class);
					break;
				}
				default:{
					mIntentToStart = null;
					break;
				}
				}
			}
        	
        });
		
    }
    
	public void activateSectionFinalMeeting() {
		ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

		if (mAfterElectionsListViewAdapter == null) {
			ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();

			for (int i = 0; i < Consts.SECTION_FINAL_MEETING.length; i++) {
				mListViewItems.add(new NabludatelListViewItem(
						Consts.SECTION_FINAL_MEETING[i],
						Consts.SECTION_FINAL_MEETING_DESCRIPTIONS[i]));
			}
			mAfterElectionsListViewAdapter = new NabludatelCustomListViewAdapter(
					this, mListViewItems);
		}

		mMainSelector.setAdapter(mAfterElectionsListViewAdapter);
		mMainSelector.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pAdapterView,
					View argpView1, int pItemPosition, long pItemId) {
				Intent mIntentToStart = null;
				int mActivityResult = 0;
				switch (pItemPosition) {
				case 0: {
//					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_control_calculations, SectionCountingControlCalculations.class);
					break;
				}
				case 1: {
//					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_control_calculations, SectionCountingControlCalculations.class);
					break;
				}
				case 2: {
//					NabludatelActivity.this.startNabludatelActivity(R.layout.section_counting_control_calculations, SectionCountingControlCalculations.class);
					break;
				}
				default: {
					mIntentToStart = null;
					break;
				}
				}
			}

		});

	}
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
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
    	case R.layout.section_elections_district:{

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
			
			((NabludatelListViewItem) this.mRootListViewAdapter.getItem(0)).setDescription(Consts.getDescriptionFill(json.length()));
		
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
    
    public JSONArray uploadPhotos(JSONArray photos){
    	try{
			JSONArray photoURLs = new JSONArray();
			for(int i=0; i< photos.length(); i++){
				File photoFile = new File((String) photos.get(i));
				mS3Helper.uploadImageToS3(photoFile);
				photoURLs.put(Consts.getAmazonS3Url(deviceId, photoFile));
			}
			return photoURLs;
		} catch (JSONException e){
			e.printStackTrace();
		}
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
	
	public void onSpravochnikButtonClick(View v) {

		String url = "file:///android_asset/spravochnik/golos_index.html";
		Intent intent = new Intent(this, SpravochnikActivity.class);
		intent.putExtra(Consts.ACTIVITY_URL_DATA, url);
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
	

    public void startNabludatelActivity(int resultCode, Class<?> c){
    	long mCurrentElectionsDistrict = prefs.getLong(Consts.PREFS_ELECTIONS_DISRICT, -1);
    	if(mCurrentElectionsDistrict != -1){
    		Intent intent = new Intent(this, c);
    		intent.putExtra(Consts.PREFS_ELECTIONS_DISRICT, mCurrentElectionsDistrict);
    		this.startActivityForResult(intent, resultCode);
    	}
	}
}
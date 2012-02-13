package org.dvaletin.apps.nabludatel;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.TabAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NabludatelActivity extends ABSNabludatelActivity {
	S3Helper mS3Helper;
	String deviceId;
	
	protected static final String TAG_CAMERA = "Camera";
	
	NabludatelCustomListViewAdapter mRootListViewAdapter, mElectionsDistrictAdapter, mBeforeElectionsAdapter;
	NabludatelCustomListViewAdapter mDuringElectionsListViewAdapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
        deviceId = t.getDeviceId();
    	mS3Helper = new S3Helper(t.getDeviceId());
    	setContentView(R.layout.main);

    	activateRootMenu();
    	
    }
    
    public void activateRootMenu(){
    	
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
    	if(mRootListViewAdapter == null){
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
					NabludatelActivity.this.activateSectionElectionsDistrict();
					break;
				}
				case 1:{
					NabludatelActivity.this.activateSectionBeforeElections();
					break;
				}
				case 2:{
					NabludatelActivity.this.activateSectionDuringElections();
					break;
				}
				case 3:{
					
					break;
				}
				case 4:{
					
					break;
				}
				case 5:{
					
					break;
				}
				case 6:{
					
					break;
				}
				default:{
					break;
				}
				}
				
			}
        	
        });
        Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.INVISIBLE);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    
    protected void activateSectionElectionsDistrict() {
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);

    	if(mElectionsDistrictAdapter == null){
	    	ArrayList<NabludatelListViewItem> mListViewItems = new ArrayList<NabludatelListViewItem>();
	    	
	    	for(int i=0; i<Consts.SECTION_ELECTIONS_DISTRICT.length; i++){
	    		mListViewItems.add(new NabludatelListViewItem(Consts.SECTION_ELECTIONS_DISTRICT[i], Consts.SECTION_ELECTIONS_DISTRICT_DESCRIPTIONS[i]));
	    	}
	    	mElectionsDistrictAdapter = new NabludatelCustomListViewAdapter(this, mListViewItems);
    	}
	    
    	mMainSelector.setAdapter(mElectionsDistrictAdapter);
    	
    	ArrayAdapter<String> mMainSelectorAdapter = new ArrayAdapter<String>(
                this, R.layout.list_view_item_layout, R.id.list_view_item_title, Consts.SECTION_ELECTIONS_DISTRICT);
                
        
        mMainSelector.setAdapter(mMainSelectorAdapter);
        mMainSelector.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> pAdapterView, View argpView1, int pItemPosition,
					long pItemId) {
				Intent mIntentToStart = null;
				int mActivityResult = 0;
				switch(pItemPosition){
				case 0:{
					mIntentToStart = new Intent(NabludatelActivity.this, SectionElectionsDistrict.class);
					mActivityResult = R.layout.section_elections_district;
					break;
				}
				default:{
					mIntentToStart = null;
					break;
				}
				}
				if(mIntentToStart!=null){
					NabludatelActivity.this.startActivityForResult(mIntentToStart, mActivityResult);
				}
			}
        	
        });
		
        Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(R.string.app_name);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.activateRootMenu();		
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
					mIntentToStart = new Intent(NabludatelActivity.this, AdmittedBefore8AM.class);
					mActivityResult = R.layout.section_before_elections_admitted_before_eight;
					break;
				}
				case 1:{
					mIntentToStart = new Intent(NabludatelActivity.this, BullotBox.class);
					mActivityResult = R.layout.section_before_elections_bullot_box;
					break;
				}
				case 2:{
					mIntentToStart = new Intent(NabludatelActivity.this, KOIBActivity.class);
					mActivityResult = R.layout.section_before_elections_koib;
					break;
				}
				case 3:{
					mIntentToStart = new Intent(NabludatelActivity.this, OformllenijeUchastka.class);
					mActivityResult = R.layout.section_before_elections_appearance;
					break;
				}
				case 4:{
					mIntentToStart = new Intent(NabludatelActivity.this, VotersActivity.class);
					mActivityResult = R.layout.section_before_elections_voters;
					break;
				}
				case 5:{
					mIntentToStart = new Intent(NabludatelActivity.this, VotersCountActivity.class);
					mActivityResult = R.layout.section_before_elections_voters_count;
					break;
				}
				case 6:{
					
					break;
				}
				default:{
					mIntentToStart = null;
					break;
				}
				}
				if(mIntentToStart!=null){
					NabludatelActivity.this.startActivityForResult(mIntentToStart, mActivityResult);
				}
			}
        	
        });
		
        Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(R.string.app_name);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.activateRootMenu();		
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
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsAttendance.class);
					mActivityResult = R.layout.section_during_elections_attendance;
					break;
				}
				case 1:{
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsBullot.class);
					mActivityResult = R.layout.section_during_elections_ballot;
					break;
				}
				case 2:{
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsPressure.class);
					mActivityResult = R.layout.section_during_elections_ballot_process_pressure;
					break;
				}
				case 3:{
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsSuspiciousVouters.class);
					mActivityResult = R.layout.section_during_elections_suspicious_voters;
					break;
				}
				case 4:{
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsBundleBullots.class);
					mActivityResult = R.layout.section_during_elections_bundle_of_ballots;
					break;
				}
				case 5:{
					mIntentToStart = new Intent(NabludatelActivity.this, ElectionsAbsenteeVote.class);
					mActivityResult = R.layout.section_during_elections_absentee_vote;
					break;
				}
				default:{
					mIntentToStart = null;
					break;
				}
				}
				if(mIntentToStart!=null){
					NabludatelActivity.this.startActivityForResult(mIntentToStart, mActivityResult);
				}
			}
        	
        });
		
        Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(R.string.app_name);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.activateRootMenu();		
			}
		});
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	switch(requestCode){
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

    public void onBackButtonPress(View v){
    	ListView mMainSelector = (ListView) findViewById(R.id.main_selector);
    	if(mMainSelector.getAdapter() == mRootListViewAdapter){
    		this.finish();
    	}else{
    		activateRootMenu();
    	}
    	
    }

	public void onUIKClick(View v){
    	startActivity(
                new Intent(this,
                SectionElectionsDistrict.class
                ));
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
	
	public void onSpravochnikClick(View v) {

		String url = "file:///android_asset/spravochnik/golos_index.html";
		Intent intent = new Intent(this, SpravochnikActivity.class);
		intent.putExtra(Consts.ACTIVITY_URL_DATA, url);
		startActivity(intent);
	}
}
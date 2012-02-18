package org.dvaletin.apps.nabludatel;

import java.util.Arrays;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.PollingPlaceSQLHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class ElectionsDistrictActivity extends ABSNabludatelActivity {
	private static final int DIALOG_FIO_ERROR = 1;
	private static final int DIALOG_NUMBER_ERROR = 2;
	PollingPlaceSQLHelper mPollingPlaceSQLHelper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPollingPlaceSQLHelper = new PollingPlaceSQLHelper(this);
		setContentView(R.layout.elections_district_profile);
		EditText uik_district_number = (EditText)findViewById(R.id.uik_district_number);
//		uik_district_number.setOnFocusChangeListener(new View.OnFocusChangeListener(){
//
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if(!hasFocus && v instanceof EditText){
//					try{
//						int i = Integer.valueOf(((EditText) v).getText().toString());
//						if(i <= 0){
//							showDialog(DIALOG_NUMBER_ERROR);
//							v.requestFocus();
//						}
//					}catch (Exception e){
//						e.printStackTrace();
//						showDialog(DIALOG_NUMBER_ERROR);
//						v.requestFocus();
//					}
//				}
//				
//			}
//			
//		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if(keyCode != KeyEvent.KEYCODE_BACK){
    		return false;
    	}
    	if(savePlace()){
    		finish();
    		return true;
    	}
    	return false;
	}
	
	
	public boolean savePlace(){
		
		String uik_district_number = ((EditText)findViewById(R.id.uik_district_number)).getText().toString();
		String uik_district_watchers_count = ((EditText)findViewById(R.id.uik_district_watchers_count)).getText().toString();
		int uik_watchers = -1;
		int uik_district = -1;
		boolean to_return = true;
		try{
			uik_district = Integer.valueOf(uik_district_number);
			uik_watchers = Integer.valueOf(uik_district_watchers_count);
			if(uik_district <= 0 || uik_watchers <= 0){
				showDialog(DIALOG_NUMBER_ERROR);
				to_return = false;
			}
		}catch(Exception e){
			e.printStackTrace();
			showDialog(DIALOG_NUMBER_ERROR);
			to_return = false;
		}
		String uik_district_chairman = ((EditText)findViewById(R.id.uik_district_chairman)).getText().toString();
		String uik_district_secretary = ((EditText)findViewById(R.id.uik_district_secretary)).getText().toString();
		if(uik_district_chairman.equals("") || uik_district_secretary.equals("")){
			showDialog(DIALOG_FIO_ERROR);
			to_return = false;
		}
		int position = ((Spinner)findViewById(R.id.district_type)).getSelectedItemPosition();
		String district_type = Consts.DISTRICT_TYPE[position];
		long time = System.currentTimeMillis();
		if(to_return){
			
			mPollingPlaceSQLHelper.open().addPollingPlace(uik_district_chairman, 0f, 0f, "", uik_district, uik_district_secretary, time, uik_watchers, district_type);
			mPollingPlaceSQLHelper.close();
		}
		
		return to_return;
	}
	
	@Override
	protected Dialog onCreateDialog(final int pId) {
		switch(pId) {
			case DIALOG_FIO_ERROR:
				return new AlertDialog.Builder(this)
					.setTitle(R.string.elections_district_fio_invalid)
					.setMessage(R.string.elections_district_fio_invalid)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton(android.R.string.ok, null)
					.create();
			case DIALOG_NUMBER_ERROR:
				return new AlertDialog.Builder(this)
					.setTitle(R.string.elections_district_number_invalid)
					.setMessage(R.string.elections_district_number_invalid)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton(android.R.string.ok, null)
					.create();
			default:
				return super.onCreateDialog(pId);
		}
	}
}

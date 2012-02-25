package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Spinner;

public class ElectionsDistrictActivity extends ABSNabludatelActivity {
	private static final int DIALOG_FIO_ERROR = 1;
	private static final int DIALOG_NUMBER_ERROR = 2;

	long district_id = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elections_district_profile);
		EditText uik_district_number = (EditText) findViewById(R.id.uik_district_number);
		Spinner district_type  = (Spinner) findViewById(R.id.district_type);
		EditText uik_district_chairman = (EditText) findViewById(R.id.uik_district_chairman);
		EditText uik_district_secretary = (EditText) findViewById(R.id.uik_district_secretary);
		EditText uik_district_watchers_count = (EditText) findViewById(R.id.uik_district_watchers_count);
		Intent intent = this.getIntent();
		district_id = intent.getLongExtra(Consts.PREFS_ELECTIONS_DISRICT, -1);
		if(district_id != -1){
			Cursor c = mElectionsDB.getPollingPlaceByNumber(district_id);
			if(c.getCount() > 0){
				uik_district_number.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_NUMBER_COLUMN));
				uik_district_chairman.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_CHAIRMAN_COLUMN));
				uik_district_secretary.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_SECRETARY_COLUMN));
				uik_district_watchers_count.setText(String.valueOf(c.getInt(ElectionsDBHelper.POLLINGPLACE_TOTALOBSERVERS_COLUMN)));
				String district_type_string = c.getString(ElectionsDBHelper.POLLINGPLACE_TYPE_COLUMN);
				for(int i=0; i < Consts.DISTRICT_TYPE.length; i++){
					if(Consts.DISTRICT_TYPE[i].equals(district_type_string))
						district_type.setSelection(i);
				}
				
			}
		}
	}

	@Override
	public void onBackPressed() {
		savePlace();
		
		super.onBackPressed();

	}

	public void savePlace() {

		String uik_district_number = ((EditText) findViewById(R.id.uik_district_number))
				.getText().toString();
		if(uik_district_number.equals(""))
			return;
		
		String uik_district_watchers_count = ((EditText) findViewById(R.id.uik_district_watchers_count))
				.getText().toString();
		int uik_watchers = 0;

		try {
			uik_watchers = Integer.valueOf(uik_district_watchers_count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String uik_district_chairman = ((EditText) findViewById(R.id.uik_district_chairman))
				.getText().toString();
		String uik_district_secretary = ((EditText) findViewById(R.id.uik_district_secretary))
				.getText().toString();
		if (uik_district_chairman.equals("")
				|| uik_district_secretary.equals("")) {
		}
		int position = ((Spinner) findViewById(R.id.district_type))
				.getSelectedItemPosition();
		String district_type = Consts.DISTRICT_TYPE[position];
		long time = System.currentTimeMillis();
		long id = mElectionsDB.addPollingPlace(uik_district_chairman, lat, lng,
				"", uik_district_number, uik_district_secretary, time,
				uik_watchers, district_type);
		
		getReturnIntent().putExtra(Consts.PREFS_ELECTIONS_DISRICT, id);
	}

}

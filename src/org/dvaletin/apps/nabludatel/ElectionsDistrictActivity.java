package org.dvaletin.apps.nabludatel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.DistrictRegion;
import org.dvaletin.apps.nabludatel.utils.DistrictRegionAdapter;

import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;

public class ElectionsDistrictActivity extends ABSNabludatelActivity {
	private static final int DIALOG_FIO_ERROR = 1;
	private static final int DIALOG_NUMBER_ERROR = 2;
	protected static final String T = ElectionsDistrictActivity.class.getSimpleName();

	long district_id = 0;
	int district_type_selection_id = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elections_district_profile);
		EditText uik_district_number = (EditText) findViewById(R.id.uik_district_number);
		Spinner district_type  = (Spinner) findViewById(R.id.district_type);
		EditText uik_district_chairman = (EditText) findViewById(R.id.uik_district_chairman);
		EditText uik_district_secretary = (EditText) findViewById(R.id.uik_district_secretary);
		EditText fake_district_region = (EditText) findViewById(R.id.fake_district_region);
		Intent intent = this.getIntent();
		district_id = intent.getLongExtra(Consts.PREFS_ELECTIONS_DISRICT, 0);
		durtyResumeHack = false;
		
		if(district_id != 0){
			Cursor c = mElectionsDB.getPollingPlaceByNumber(district_id);
			if(c.getCount() > 0){
				uik_district_number.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_NAME_COLUMN));
				uik_district_chairman.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_CHAIRMAN_COLUMN));
				uik_district_secretary.setText(c.getString(ElectionsDBHelper.POLLINGPLACE_SECRETARY_COLUMN));
				district_type_selection_id = c.getInt(ElectionsDBHelper.POLLINGPLACE_SELECTION_COLUMN);
				String district_type_string = c.getString(ElectionsDBHelper.POLLINGPLACE_TYPE_COLUMN);
				for(int i=0; i < Consts.DISTRICT_TYPE.length; i++){
					if(Consts.DISTRICT_TYPE[i].equals(district_type_string))
						district_type.setSelection(i);
				}
				
			}
		}
		Spinner district_region = (Spinner) findViewById(R.id.district_region);
		
		DistrictRegionAdapter adapter = new DistrictRegionAdapter(this, DistrictRegion.RU_REGIONS);
		
		district_region.setAdapter(adapter);
		try{
			district_region.setSelection(district_type_selection_id);
		} catch (Exception e){
			e.printStackTrace();
		}
		district_region.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(district_id > 0)
					mElectionsDB.updatePollingPlaceSelection(district_id, position);
				EditText fake_district_region = (EditText) findViewById(R.id.fake_district_region);
				fake_district_region.setText(String.valueOf(id));		
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}} );
		
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
		if(district_id > 0){
			mElectionsDB.updatePollingPlace(district_id, uik_district_chairman, lat, lng,
					uik_district_number, -1, uik_district_secretary, time,
					district_type_selection_id, district_type);
		}else{
			long id = mElectionsDB.addPollingPlace(uik_district_chairman, lat, lng,
				uik_district_number, -1, uik_district_secretary, time,
				district_type_selection_id, district_type);
		
			getReturnIntent().putExtra(Consts.PREFS_ELECTIONS_DISRICT, id);
		}
	}

}

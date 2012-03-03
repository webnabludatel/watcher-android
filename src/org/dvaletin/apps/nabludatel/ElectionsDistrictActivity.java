package org.dvaletin.apps.nabludatel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.dvaletin.apps.nabludatel.utils.*;

import java.util.Arrays;

public class ElectionsDistrictActivity extends ABSNabludatelActivity {
	long mSelectedRegionId=-1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elections_district_profile);

		final Spinner regionS = (Spinner) findViewById(R.id.district_region);
		regionS.setAdapter(new DistrictRegionAdapter(this, Consts.REGIONS));
		regionS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if( id > 0){
					updateCheckListItem(parent.getTag().toString(), String.valueOf(id), "");
					mSelectedRegionId = id;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		final Spinner typeS  = (Spinner) findViewById(R.id.district_type);
		typeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				updateCheckListItem(parent.getTag().toString(),
						position >= 0 && position < Consts.POLLING_PLACE_TYPE.length ?
								Consts.POLLING_PLACE_TYPE[position] : "", "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});

		if (mCurrentPollingPlaceId != -1){
			Cursor c = mElectionsDB.getPollingPlaceByNumber(mCurrentPollingPlaceId);
			try {
				if(c.getCount() > 0){
					int regionId = c.getInt(ElectionsDBHelper.POLLINGPLACE_REGION_ID_COLUMN);
					regionS.setSelection(DistrictRegion.indexOfRegionId(regionId, Consts.REGIONS));

					String type = c.getString(ElectionsDBHelper.POLLINGPLACE_TYPE_COLUMN);
					typeS.setSelection(Arrays.asList(Consts.POLLING_PLACE_TYPE).indexOf(type));
				}
			} finally {
				c.close();
			}
		}
	}

	@Override
	protected void restoreCheckListItemsFromDb(long pollingPlaceId) {
		if (pollingPlaceId != -1) {
			super.restoreCheckListItemsFromDb(pollingPlaceId);
		}
	}

	@Override
	protected void restoreView(View view, CheckListItem data) {
		super.restoreView(view, data);
		if (view instanceof Spinner) {
			restoreSpinner(data, (Spinner) view);
		}
	}

	private void restoreSpinner(CheckListItem data, Spinner spinner) {
		try {
			Spinner regionS = (Spinner) findViewById(R.id.district_region);

			if (spinner == regionS) {
				int regionId = Integer.valueOf(data.getValue());
				regionS.setSelection(DistrictRegion.indexOfRegionId(regionId, Consts.REGIONS));
				return;
			}

			Spinner typeS  = (Spinner) findViewById(R.id.district_type);
			if (spinner == typeS) {
				typeS.setSelection(Arrays.asList(Consts.POLLING_PLACE_TYPE).indexOf(data.getValue()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		if(savePlace())
			super.onBackPressed();
	}

	public boolean savePlace() {
		// TODO добавить проверки!!!
		String name = ((EditText) findViewById(R.id.district_number)).getText().toString();
		if (name.equals("") || this.mSelectedRegionId == -1) {
			this.showDialog(0);
			return false;
		}

		String chairman = ((EditText) findViewById(R.id.district_chairman)).getText().toString();
		String secretary = ((EditText) findViewById(R.id.district_secretary)).getText().toString();

		int regionPosition = ((Spinner) findViewById(R.id.district_region)).getSelectedItemPosition();
		int regionId = regionPosition >= 0 && regionPosition < Consts.REGIONS.length ?
				Consts.REGIONS[regionPosition].getId() : Consts.REGIONS[0].getId();

		int typePosition = ((Spinner) findViewById(R.id.district_type)).getSelectedItemPosition();
		String districtType = typePosition >= 0 && typePosition < Consts.POLLING_PLACE_TYPE.length ?
				Consts.POLLING_PLACE_TYPE[typePosition] : Consts.POLLING_PLACE_TYPE[0];

		long time = System.currentTimeMillis();
		if(mCurrentPollingPlaceId > 0){
			mElectionsDB.updatePollingPlace(mCurrentPollingPlaceId, lat, lng, name, time, chairman,
					secretary, regionId, districtType, -1
			);
		}else{
			mCurrentPollingPlaceId = mElectionsDB.addPollingPlace(lat, lng, name, time, chairman,
					secretary, regionId, districtType, -1);
		}
		getReturnIntent().putExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID, mCurrentPollingPlaceId);
		
		return true;
	}
	
	@Override
	protected
	Dialog onCreateDialog(int id){
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Ошибка").setMessage("Не заполнены обязательные поля (*)").setPositiveButton("Изменить", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			}}).setNegativeButton("Выйти", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					ElectionsDistrictActivity.this.finish();
					
				}});
		return b.create();
		
	}
	

}

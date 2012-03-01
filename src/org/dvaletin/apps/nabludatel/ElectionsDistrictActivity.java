package org.dvaletin.apps.nabludatel;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.dvaletin.apps.nabludatel.utils.*;

import java.util.Arrays;

public class ElectionsDistrictActivity extends ABSNabludatelActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elections_district_profile);

		final Spinner regionS = (Spinner) findViewById(R.id.district_region);
		regionS.setAdapter(new DistrictRegionAdapter(this, Consts.REGIONS));
		regionS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				updateCheckListItem(adapterView.getTag().toString(), String.valueOf(id), "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
				updateCheckListItem(adapterView.getTag().toString(), "", "");
			}
		});

		final Spinner typeS  = (Spinner) findViewById(R.id.district_type);
		typeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				updateCheckListItem(adapterView.getTag().toString(),
						position >= 0 && position < Consts.POLLING_PLACE_TYPE.length ?
								Consts.POLLING_PLACE_TYPE[position] : "", "");
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
				updateCheckListItem(adapterView.getTag().toString(), "", "");
			}
		});

		if (mCurrentPollingPlaceId != -1){
			Cursor c = mElectionsDB.getPollingPlaceByNumber(mCurrentPollingPlaceId);
			if(c.getCount() > 0){
				int regionId = c.getInt(ElectionsDBHelper.POLLINGPLACE_REGION_ID_COLUMN);
				regionS.setSelection(DistrictRegion.indexOfRegionId(regionId, Consts.REGIONS));

				String type = c.getString(ElectionsDBHelper.POLLINGPLACE_TYPE_COLUMN);
				typeS.setSelection(Arrays.asList(Consts.POLLING_PLACE_TYPE).indexOf(type));
			}
		}
	}

	@Override
	protected void restoreView(View view, CheckListItem data) {
		super.restoreView(view, data);
		if (view instanceof Spinner) {
			resporeSpinner(data, (Spinner) view);
		}
	}

	private void resporeSpinner(CheckListItem data, Spinner spinner) {
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
	public void onBackPressed() {
		savePlace();
		
		super.onBackPressed();
	}

	public void savePlace() {

		String name = ((EditText) findViewById(R.id.district_number)).getText().toString();
		if (name.equals("")) return;

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
	}

}

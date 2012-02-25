package org.dvaletin.apps.nabludatel;


import org.dvaletin.apps.nabludatel.utils.Consts;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainWindow extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int buttonResource = getResources().getIdentifier("drawable/button", null, getPackageName());
	    Drawable button = getResources().getDrawable(buttonResource);
		setContentView(R.layout.main_tabs);
		TabHost host=getTabHost();
		host.addTab(host.newTabSpec("one")
				.setIndicator("Я", button)
				.setContent(new Intent(this, NabludatelSettingsActivity.class)));
		host.addTab(host.newTabSpec("two")
				.setIndicator("Наблюдение")
				.setContent(new Intent(this, NabludatelActivity.class)));
				
		host.addTab(host.newTabSpec("three")
				.setIndicator("Отчет")
				.setContent(new Intent(this, ReportActivity.class)));
		


		String url = "file:///android_asset/spravochnik/golos_index.html";
		Intent intent = new Intent(this, SpravochnikActivity.class);
		intent.putExtra(Consts.ACTIVITY_URL_DATA, url);

		host.addTab(host.newTabSpec("four")
				.setIndicator("Справочник")
				.setContent(intent));
		
		host.addTab(host.newTabSpec("five")
				.setIndicator("S.O.S")
				.setContent(new Intent(this, SosActivity.class)));
		
		setupUI();
	}
	
	private void setupUI() {
		RadioButton rbFirst = (RadioButton) findViewById(R.id.first);
		RadioButton rbSecond = (RadioButton) findViewById(R.id.second);
		RadioButton rbThird = (RadioButton) findViewById(R.id.third);
		RadioButton rbFourth = (RadioButton) findViewById(R.id.fourth);
		RadioButton rbFifth = (RadioButton) findViewById(R.id.fifth);
		
		rbFirst.setButtonDrawable(R.drawable.button_radio);
//		rbFirst.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_1_30, 0, 0);
//		rbFirst.setText("Я");
//		rbFirst.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8.0f);
//		rbFirst.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
		
		rbSecond.setButtonDrawable(R.drawable.button_radio);
//		rbSecond.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbSecond.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_2_30, 0, 0);
//		rbSecond.setText("Наблюдение");
//		rbSecond.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8.0f);
		
		rbThird.setButtonDrawable(R.drawable.button_radio);
//		rbThird.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbThird.setText("Справочник");
//		rbThird.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_3_30, 0, 0);
//		rbThird.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8f);
		
		rbFourth.setButtonDrawable(R.drawable.button_radio);
//		rbFourth.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbFourth.setText("S.O.S");
//		rbFourth.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_4_30, 0, 0);
//		rbFourth.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8f);
		
		rbFifth.setButtonDrawable(R.drawable.button_radio);
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.states);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, final int checkedId) {
				switch (checkedId) {
				case R.id.first:
					getTabHost().setCurrentTab(0);
					break;
				case R.id.second:
					getTabHost().setCurrentTab(1);
					break;
				case R.id.third:
					getTabHost().setCurrentTab(2);
					break;
				case R.id.fourth:
					getTabHost().setCurrentTab(3);
					break;
				}
			}
		});
	}
}

package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.ReportImageView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ReportActivity extends ABSNabludatelActivity {
	
	FrameLayout report_image_frame;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nabludatel_report);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		long pollingPlaceId = prefs.getLong(Consts.PREFS_ELECTIONS_DISRICT, -1);
		String pollingPlaceName = mElectionsDB.getPollingPlaceNameByNumber(pollingPlaceId);
		this.setTitle("Отчет по участку " + pollingPlaceName);
		String violations_title = getString(R.string.report_violations_title);
		TextView report_violation_list_title = (TextView) findViewById(R.id.report_violation_list_title);
		report_violation_list_title.setText(violations_title + " " + pollingPlaceName);
		LinearLayout report_frame = (LinearLayout)findViewById(R.id.report_frame);
		report_frame.removeAllViews();
		Cursor badCursor = mElectionsDB.getViolationsByPollingPlaceId(pollingPlaceId);
		Cursor goodCursor = mElectionsDB.getNoneViolationsByPollingPlaceId(pollingPlaceId);
		int bad = badCursor.getCount();
		int good = goodCursor.getCount();
		badCursor.moveToFirst();
		ImageView list_divider = null;
		if(badCursor.getCount() <= 0){
			TextView report_item = new TextView(this);
			report_item.setTextAppearance(this, R.style.TextStyle);
			report_item.setText("Нарушений не зафиксировано");
			report_frame.addView(report_item);
		}
		for(int i = 0; i < badCursor.getCount(); i++){
			TextView report_item = new TextView(this);
			report_item.setTextAppearance(this, R.style.TextStyle);
			report_item.setText(badCursor.getString(badCursor.getColumnIndex(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_KEY)));
			report_frame.addView(report_item);
			list_divider = new ImageView(this, null, R.style.list_divider_style);
			list_divider.setImageResource(R.drawable.list_devider);
			list_divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			report_frame.addView(list_divider);
			badCursor.moveToNext();
		}
		if(list_divider != null){
			report_frame.removeView(list_divider);
		}
		ReportImageView report_image = (ReportImageView) findViewById(R.id.report_image);
		report_image.setNewValues(good, bad);
//		report_image_frame.removeAllViews();
//		report_image_frame.addView(new ReportImageView(this, 0, 0));
	}
	
	public void onReportOnsiteButtonClick(View v){
		
	}

}

package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;

public class SosActivity extends ABSNabludatelActivity {
	
	private static final int DIALOG_SEND_SOS_BEGIN = 1001;
	private static final int DIALOG_SEND_SOS_ERROR = 1002;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.s_o_s);
	}

	@Override
	public void restore() {
		// Do nothing to restore
	}

	@Override
	public void onResume(){
		super.onResume();
		long pollingPlace = prefs.getLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1L);
		if(pollingPlace == -1L){
			((EditText)findViewById(R.id.sos_report_text)).setEnabled(false);
			((EditText)findViewById(R.id.sos_report_text)).setFocusable(false);
			((EditText)findViewById(R.id.sos_report_text)).setHint("Не выбран участок наблюдения");
		}else{
			((EditText)findViewById(R.id.sos_report_text)).setEnabled(true);
			((EditText)findViewById(R.id.sos_report_text)).setFocusable(true);
			((EditText)findViewById(R.id.sos_report_text)).setHint(R.string.sos_report_hint);
		}
	}
	
	public void onTellUsClick(View v){
//		super.onBackPressed();
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		final NabludatelCloud cloud = new NabludatelCloud(tm.getDeviceId());
		if(cloud.tryAuthenticate()){
			String sos_report_text = ((EditText)findViewById(R.id.sos_report_text)).getText().toString();
			String sos_report_key = findViewById(R.id.sos_report_text).getTag().toString();
			long timestamp = System.currentTimeMillis();
			long pollingPlace = prefs.getLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1L);
			long serverResponce;
			if(!sos_report_text.equals("") && pollingPlace != -1L ){
				showDialog(DIALOG_SEND_SOS_BEGIN);
				serverResponce = cloud.postNewMessage(sos_report_key, sos_report_text, lat, lng, timestamp, -1 /* rowId */ , pollingPlace);
				if(serverResponce != -1){
					((EditText)findViewById(R.id.sos_report_text)).setText("");
					dismissDialog(DIALOG_SEND_SOS_BEGIN);
				}else{
					showDialog(DIALOG_SEND_SOS_ERROR);
				}
				savePhotos();
				saveVideos();
				
			}
		}
	}
	protected Dialog onCreateDialog(int id) {
	    switch(id) {
	    case DIALOG_SEND_SOS_BEGIN:{
			return ProgressDialog.show(this, "",
					"Отправляю...", true);
	    }
	    case DIALOG_SEND_SOS_ERROR:{
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Ошибка отправки SOS, попробуйте позднее...")
	    	       .setCancelable(false)
	    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	           }
	    	       });
			return builder.create();
	    }
	    	
		default:
	        return null;
	    }
	}
	
	public void onInformationClick(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Инструкции");
		builder.setMessage(getString(R.string.sos_hint)).setCancelable(false)
		.setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}

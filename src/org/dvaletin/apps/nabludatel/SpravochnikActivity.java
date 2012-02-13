package org.dvaletin.apps.nabludatel;

import java.lang.reflect.Method;
import java.net.URI;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SpravochnikActivity extends ABSNabludatelActivity {
	
	WebView spravichnik_vew;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spravochnik_layout);
		spravichnik_vew = (WebView) findViewById(R.id.spravichnik_vew);
		try {
			Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
			m.invoke(spravichnik_vew, true);
		} catch (Exception ignored) {
		}
		Intent mIntent = this.getIntent();
		String url = mIntent.getStringExtra(Consts.ACTIVITY_URL_DATA);
		spravichnik_vew.loadUrl(url);
		Button searchButton = (Button) findViewById(R.id.spravochnik_search_button);
		EditText search = (EditText) findViewById(R.id.spravochnik_search_text);
		search.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if(event.getAction() == KeyEvent.KEYCODE_ENTER){
					spravichnik_vew.findNext(true);
					return true;
				}else if(!v.getText().toString().equals("")){
					spravichnik_vew.findAll(v.getText().toString());
					return true;
				}
				
				return false;
			}
			
		});
	}
	
	public void onSpravochnikSearchClick(View v){
//		EditText search = (EditText) findViewById(R.id.spravochnik_search_text);
		spravichnik_vew.findNext(true);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && spravichnik_vew.canGoBack()) {
	    	spravichnik_vew.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}

package org.dvaletin.apps.nabludatel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import org.dvaletin.apps.nabludatel.utils.Consts;

import java.lang.reflect.Method;

public class SpravochnikActivity extends ABSNabludatelActivity {

	private WebView webView;
	private String searchQuery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spravochnik_layout);
		webView = (WebView) findViewById(R.id.spravichnik_vew);

		Intent mIntent = this.getIntent();
		String url = mIntent.getStringExtra(Consts.ACTIVITY_URL_DATA);
		webView.loadUrl(url);
	}

	private boolean search() {
		EditText search = (EditText) findViewById(R.id.spravochnik_search_text);
		String s = search.getText().toString();
		if ("".equals(s.trim())) {
			webView.clearMatches();
			return false;
		} else {
			if (s.equals(searchQuery)) {
				webView.findNext(true);
			} else {
				searchQuery = s;
				webView.findAll(s);
				setFindIsUp(webView);
			}
			hideKeyboard();
			return true;
		}
	}

	public void hideKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(findViewById(R.id.spravochnik_search_text).getWindowToken(), 0);
	}

	private void setFindIsUp(WebView view) {
		try {
			Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
			m.invoke(view, true);
		} catch (Exception ignored) {
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		setTitle("Справочник");
	}

	public void onSpravochnikSearchClick(View v) {
		search();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		if ((keyCode == KeyEvent.KEYCODE_SEARCH)) {
			if (search()) {
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}

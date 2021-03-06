package org.dvaletin.apps.nabludatel;

import impl.android.com.twitterapime.xauth.ui.WebViewOAuthDialogWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.dvaletin.apps.nabludatel.utils.Consts;

import com.twitterapime.xauth.Token;
import com.twitterapime.xauth.ui.OAuthDialogListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import org.dvaletin.apps.nabludatel.utils.LocalProperties;

public class SimpleTwitterActivity extends Activity implements OAuthDialogListener{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterAuth tauth = auth();
		WebView webView = new WebView(SimpleTwitterActivity.this);
		SimpleTwitterActivity.this.setContentView(webView);
        
        WebViewOAuthDialogWrapper pageWrapper =
        	new WebViewOAuthDialogWrapper(webView);
        //
		pageWrapper.setConsumerKey(tauth.tw_key);
		pageWrapper.setConsumerSecret(tauth.tw_secret);
		pageWrapper.setCallbackUrl(tauth.tw_callback);
		pageWrapper.setOAuthListener(SimpleTwitterActivity.this);
		//
		pageWrapper.login();
	}
	
	public void onTwitterSetupClick(View v){
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				TwitterAuth tauth = auth();
				WebView webView = new WebView(SimpleTwitterActivity.this);
				SimpleTwitterActivity.this.setContentView(webView);
		        
		        WebViewOAuthDialogWrapper pageWrapper =
		        	new WebViewOAuthDialogWrapper(webView);
		        //
				pageWrapper.setConsumerKey(tauth.tw_key);
				pageWrapper.setConsumerSecret(tauth.tw_secret);
				pageWrapper.setCallbackUrl(tauth.tw_callback);
				pageWrapper.setOAuthListener(SimpleTwitterActivity.this);
				//
				pageWrapper.login();
			}
			
		});
	}

	private TwitterAuth auth() {
		return new TwitterAuth(
							LocalProperties.getTwitterKey(),
							LocalProperties.getTwitterSecret(),
							LocalProperties.getTwitterCallback()
					);
	}

	/**
	 * @see com.twitterapime.xauth.ui.OAuthDialogListener#onAuthorize(com.twitterapime.xauth.Token)
	 */
	public void onAuthorize(Token accessToken) {
		String secret = accessToken.getSecret();
		String token = accessToken.getToken();
		SharedPreferences prefs = this.getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
		prefs.edit().putString(Consts.PREFS_TWITTER_SECRET, secret).commit();
		prefs.edit().putString(Consts.PREFS_TWITTER_TOKEN, token).commit();

	}

	public void onAccessDenied(final String message) {
		this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				Toast.makeText(SimpleTwitterActivity.this, "Twitter: "+message, Toast.LENGTH_SHORT).show();
			}});
//		showMessage("Отказано в доступе!");
	}

	/**
	 * @see com.twitterapime.xauth.ui.OAuthDialogListener#onFail(java.lang.String, java.lang.String)
	 */
	public void onFail(final String error, String message) {
		this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				Toast.makeText(SimpleTwitterActivity.this, "Twitter: "+error, Toast.LENGTH_SHORT).show();
			}});
//		showMessage("Неправильное имя пользователя или пароль!");
	}
	
	/**
	 * @param msg
	 */
	private void showMessage(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		//
		builder.create().show();
	}
	class TwitterAuth {
		String tw_key;
		String tw_secret;
		String tw_callback;
		public TwitterAuth(String k, String s, String c){
			tw_key = k;
			tw_secret = s;
			tw_callback = c;
		}
	}
}

package org.dvaletin.apps.nabludatel;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NabludatelActivity extends Activity {
    private Button postButton;
	private Button postCancelButton;
	private Button mainPostNarushenijeButton;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.gotoMainScreen();
        
    }
    
    protected void showPostNarusheniye(){
    	setContentView(R.layout.post_narushenije);
    	postCancelButton = (Button) findViewById(R.id.bttn_post_cancel);
        postCancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.gotoMainScreen();
				
			}
        	
        });
        
        postButton = (Button) findViewById(R.id.post_narushenije);
	    postButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.postNarusheniye();
				
			}
		});
    }

    protected void gotoMainScreen(){
    	setContentView(R.layout.main);
    	mainPostNarushenijeButton = (Button) findViewById(R.id.bttn_main_post_narushenije);
        mainPostNarushenijeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NabludatelActivity.this.showPostNarusheniye();
				
			}
        	
        });
    }
	protected void postNarusheniye() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Narusheniye otpravleno", Toast.LENGTH_SHORT).show();
		
		this.gotoMainScreen();
		
	}
}
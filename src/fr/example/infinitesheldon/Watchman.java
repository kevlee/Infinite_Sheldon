package fr.example.infinitesheldon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Watchman extends Activity {

	private Location localisation;
	private LocationManager GPS;
	private LocationListener GPSlistener;
	private EditText username,password;
	private PlayerAlarm pl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchman);
		// block l'orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// recupération de edittext
		username = (EditText)findViewById(R.id.UserName);
		password = (EditText)findViewById(R.id.PassWord);
		// active le GPS
		//Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
		GPS = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		GPSlistener = new GPSlistener();
		GPS.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,GPSlistener);
		pl = (PlayerAlarm) new PlayerAlarm().execute(this);
		
	}
	
	public void onclickstopapp(View v){
		SharedPreferences preferences = getSharedPreferences("sheldon_pref", Context.MODE_PRIVATE);
		if (preferences.getAll().isEmpty()){
			Log.i("sharedpref","empty");
		}
		if(username.getText().toString().equals(preferences.getString("Login", "test")) && 
		   password.getText().toString().equals(preferences.getString("Password", "test"))){
			pl.cancel(true);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_watchman, menu);
		return true;
	}
	
	// classe pour écouter le GPS
	private class GPSlistener implements LocationListener 
    { 
 
        public void onProviderDisabled(String provider) { 
            // TODO Auto-generated method stub 
        } 
 
 
        public void onProviderEnabled(String provider) { 
            // TODO Auto-generated method stub 
        } 
 
 
        public void onStatusChanged(String provider, int status, 
            Bundle extras) { 
            // TODO Auto-generated method stub 
        } 
 
 
          public void onLocationChanged(Location location) { 
              localisation = location;
              GPS.removeUpdates(this);
          } 
     }
	
	 private class PlayerAlarm extends AsyncTask<Context, Long, Void> {
		 MediaPlayer mPlayer = null;
		 Context mycontext;
		 protected void playalarm(int idalarm){
	    	if(mPlayer != null) {
	    		mPlayer.stop();
	    		mPlayer.release();
	    	}
	    	mPlayer = MediaPlayer.create(mycontext,idalarm);
	    	float count=100*.01f;
	    	mPlayer.setVolume(count,count); 
	    	mPlayer.start();
	    	while(mPlayer.isPlaying()){
	    		if (this.isCancelled())
					break;
	    	}
		 }
		 
		protected Void doInBackground(Context... params) {
			mycontext = params[0];
			while(true){
				playalarm(R.raw.sir);
				if (this.isCancelled())
					break;
			}
			return null;
		}
		
		protected void onCancelled(){
			mPlayer.stop();
		}
		
	 }


}

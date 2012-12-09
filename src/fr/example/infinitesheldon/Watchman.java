package fr.example.infinitesheldon;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;

public class Watchman extends Activity {

	private Location localisation;
	private LocationManager GPS;
	private LocationListener GPSlistener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchman);
		// block l'orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// active le GPS
		Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
		GPS = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		GPSlistener = new GPSlistener();
		GPS.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,GPSlistener);
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


}

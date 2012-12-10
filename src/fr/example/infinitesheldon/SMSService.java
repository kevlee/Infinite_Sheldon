package fr.example.infinitesheldon;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class SMSService extends Service  {
	 
	String msg_recu;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Bundle extra = intent.getExtras();
		msg_recu = extra.getString("msg_recu");
		doSomething( );
	}

	public void doSomething(){
		SharedPreferences preferences = getSharedPreferences("sheldon_pref", Context.MODE_PRIVATE);
		if (!preferences.getAll().isEmpty()){
			preferences.edit().putString("Alarm", "true").commit();
			Toast.makeText(this, "Le message que l'on a reçu est: "+msg_recu,
					Toast.LENGTH_LONG).show();
			Intent watchman = new Intent(this, Watchman.class);
			watchman.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	this.startActivity(watchman);
		}
	}
}

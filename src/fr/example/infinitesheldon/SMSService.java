package fr.example.infinitesheldon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SMSService extends Service  {
	 
	 
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		doSomething();
	}

	public void doSomething(){
		Intent it = new Intent(this, Watchman.class);
    	it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	this.startActivity(it);
	}
}

package fr.example.infinitesheldon;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class SMSService extends Service  {
	 
	 
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		doSomething( intent);
	}

	public void doSomething(Intent data){
		Bundle extra = data.getExtras();
		String msg_recu = extra.getString("msg_recu");
		Toast.makeText(this, "Le message que l'on a reçu est: "+msg_recu,
				Toast.LENGTH_LONG).show();
		Intent it = new Intent(this, Watchman.class);
    	it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	this.startActivity(it);
	}
}

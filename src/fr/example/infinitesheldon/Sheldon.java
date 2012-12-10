package fr.example.infinitesheldon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Sheldon extends Activity {
	EditText username,password,email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sheldon);
		// block l'orientation
		SharedPreferences preferences = getSharedPreferences("sheldon_pref", Context.MODE_PRIVATE);
		if (!preferences.getAll().isEmpty()){
			Intent intent = new Intent(this, Watchman.class);
			startActivity(intent);
			this.finish();
		}
		// block l'orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		username = (EditText)findViewById(R.id.UserName);
		password = (EditText)findViewById(R.id.PassWord);
		email = (EditText)findViewById(R.id.Email);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sheldon, menu);
		return true;
	}
	
	public void onclicksheldon(View v){
		SharedPreferences preferences = getSharedPreferences("sheldon_pref", Context.MODE_PRIVATE);
		if (preferences.getAll().isEmpty()){
			Log.i("preferences","preferences null");
			preferences.edit().putString("Login",username.getText().toString());
			preferences.edit().putString("Password",password.getText().toString());
			preferences.edit().putString("Email",email.getText().toString());
			preferences.edit().putString("alarm","fasle");
			//Intent intent = new Intent(this, Watchman.class);
			//startActivity(intent);
			this.finish();
		}
		
		
	}



}

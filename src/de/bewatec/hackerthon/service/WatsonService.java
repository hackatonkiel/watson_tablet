package de.bewatec.hackerthon.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WatsonService extends Service{

	private static final String LOG_TAG = "WatsonService";
	
	public WatsonService() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void onCreate() {
		Log.d(LOG_TAG,"Service created");
		super.onCreate();
	}
	
	
	
	
	
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}

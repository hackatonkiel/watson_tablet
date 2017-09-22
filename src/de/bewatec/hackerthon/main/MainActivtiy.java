package de.bewatec.hackerthon.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivtiy extends Activity{

	private static final String LOG_TAG = "WatsonService";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG,"Activity Started!");
	}

}

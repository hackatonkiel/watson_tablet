package de.bewatec.hackerthon.actions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartTVApp implements Action{

	private static final String LOG_TAG = "WatsonAction";
	private Context context;
	
	public StartTVApp(Context context) {
		this.context = context;
	}

	@Override
	public void execute() {
		Log.d(LOG_TAG,"Would now start TV-APP");
		Intent intent = context.getPackageManager().getLaunchIntentForPackage("de.bewatec.bewatectv");
		
		
	}

	
	
	
	
}

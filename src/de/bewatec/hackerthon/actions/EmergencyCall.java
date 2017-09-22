package de.bewatec.hackerthon.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class EmergencyCall implements Action{

	private static final String LOG_TAG = "WatsonAction";
	private Context context;
	private String emergencyNumber;
	
	
	public EmergencyCall(Context context) {
		this.context = context;
	}

	@Override
	public void execute() {
		Log.d(LOG_TAG,"Now would execute emergency call!");
		
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("sip://normal.call.bewatec.de/" + Uri.encode(emergencyNumber) + "/" + Uri.encode(emergencyNumber)));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.context.startActivity(intent);
		
		
		
	}

	
	
	
}

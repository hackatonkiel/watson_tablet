package de.bewatec.hackerthon.bootstarter;

import de.bewatec.hackerthon.service.WatsonService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootStarter extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {

		//Here Start Service that listents to voice commands
		
		Intent service = new Intent(context, WatsonService.class);
		context.startService(service);
		
	}

}

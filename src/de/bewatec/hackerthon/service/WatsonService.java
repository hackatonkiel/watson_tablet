package de.bewatec.hackerthon.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.os.IBinder;
import android.util.Log;

public class WatsonService extends Service implements WatsonSpeechToTextListener, WatsonConversationListener, WatsonTextToSpeechListener{

	private AudioRecord audioRecorder;
	private AudioManager audioManager;
	
	
	protected enum State{
		IDLE,
		LISTENING,
		TALKING,
		EMERGENCY,
		ERROR;
	}
	
	
	private static final String LOG_TAG = "WatsonService";
	
	public WatsonService() {
		// TODO Auto-generated constructor stub
		
	
	}
	
	@Override
	public void onCreate() {
		Log.d(LOG_TAG,"Service created");
		super.onCreate();
		
	   audioManager = (AudioManager) this.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
		audioManager.setMicrophoneMute(true);
		
		
		WatsonSpeechToText speechToText = new WatsonSpeechToText();
		speechToText.startSpeechToText(this);
		
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated me
	
		return null;
	}

	@Override
	public void onConversationResult(String conversationResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpeechRecognized(String speech) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTexToSpeechResult(String fileName) {
		// TODO Auto-generated method stub
		
	}



		
		
		
		

}

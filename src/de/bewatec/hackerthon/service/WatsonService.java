package de.bewatec.hackerthon.service;

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WatsonService extends Service implements WatsonSpeechToTextListener, WatsonConversationListener, WatsonTextToSpeechListener{

	
	protected enum State{
		IDLE,
		LISTENING,
		TALKING,
		EMERGENCY,
		ERROR;
	}
	
	
	private static final String LOG_TAG = "WatsonService";
	private State currentState = State.IDLE;
	
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

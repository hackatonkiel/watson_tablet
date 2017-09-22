package de.bewatec.hackerthon.service;

import java.io.InputStream;
import java.util.List;

import okhttp3.WebSocket;

import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.util.Log;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechTimestamp;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

import de.bewatec.hackerthon.config.WatsonConfigData;
import de.bewatec.hackerton.utils.AudioStreamer;
import de.bewatec.hackerton.utils.WatsonUtils;


public class WatsonSpeechToText implements RecognizeCallback {
	private static int NORMAL_CLOSURE = 1000;
	
	private int bufferSize;
	
	private static String LOG_TAG = "WatsonSpeechToText";
	private SpeechToText service = null;
	private RecognizeOptions options;

	private WebSocket socket;
	
	private AudioRecord audioRecorder;
	
	private WatsonSpeechToTextListener textSink;
	
	private AudioStreamer micStreamer;
	
	
	
	public WatsonSpeechToText(WatsonSpeechToTextListener textSink){
		this.textSink = textSink;
		
		bufferSize = AudioRecord.getMinBufferSize(WatsonConfigData.sampleRate, WatsonConfigData.channelConfig,
				WatsonConfigData.audioFormat) * 2;
		Log.d(LOG_TAG, "BufferSize " + bufferSize);
		
		audioRecorder = new AudioRecord(AudioSource.MIC, WatsonConfigData.sampleRate, WatsonConfigData.channelConfig,
				WatsonConfigData.audioFormat, this.bufferSize);
		micStreamer = new AudioStreamer(audioRecorder);
		micStreamer.startRecording();
		
		options = new RecognizeOptions.Builder()
		.timestamps(true)
		.speakerLabels(true)
		// .continuous(true)
		.interimResults(true)
		.contentType(
				HttpMediaType.AUDIO_PCM + ";rate=" + WatsonConfigData.sampleRate + ";channels="
						+ WatsonConfigData.channelCount).build();

		service = new SpeechToText(WatsonConfigData.API_USER, WatsonConfigData.API_PW);
		service.setEndPoint(WatsonConfigData.API_URL);
		
	}
	
	
	public void startSpeechToText(final RecognizeCallback listener){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				socket = service.recognizeUsingWebSocket(micStreamer, options, listener);		
			
			}
		}).start();
		
		
	}
	
	public void stopSpeechToText(){
		if(socket != null)
			socket.close(NORMAL_CLOSURE, null);
	}


	@Override
	public void onConnected() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onInactivityTimeout(RuntimeException arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onListening() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTranscription(SpeechResults speech) {
	 String resultString = extractItems(speech);
		if(resultString != null)
		 Log.d(LOG_TAG, "Speech: "+resultString);
		
		final SpeechResults finalSpeech = speech;
		new Thread(new Runnable() {

			@Override
			public void run() {

				String transcript = extractItems(finalSpeech);

				textSink.onSpeechRecognized(transcript);

			}

		}).start();
	}



	@Override
	public void onTranscriptionComplete() {
		
	}
	
	private String extractItems(SpeechResults speech) {
		Transcript finalResult = null;
		SpeechAlternative alternativeWithMaxConf;
		List<SpeechTimestamp> timeStamps;

		if (speech != null) {
			if (speech.getResults() != null) {
				for (Transcript result : speech.getResults()) {
					if (result.isFinal()) {
						finalResult = result;
						break;
					}
				}

				if (finalResult != null) {

					alternativeWithMaxConf = WatsonUtils.getAlternativesWithMaxConfidence(finalResult);

					return alternativeWithMaxConf.getTranscript();

					// Log.d(LOG_TAG, "Final transcript: " + alternativeWithMaxConf.getTranscript());

					/*
					 * printAllSpeakers(); if (alternativeWithMaxConf != null) { timeStamps =
					 * alternativeWithMaxConf.getTimestamps(); saveTimestamps(timeStamps); sortWordToLabelByTimeStamp(); }
					 */
				}
			}

			// if(speech.getSpeakerLabels() != null){
			// Log.d(LOG_TAG,"Labels?: "+speech.toString());
			// }
			// addSpeakerLabels(speech.getSpeakerLabels());// If labels available they will be saved

		}
		return null;
	}
	
}
	
	

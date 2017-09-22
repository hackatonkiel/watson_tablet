package de.bewatec.hackerthon.service;

import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import de.bewatec.hackerthon.config.WatsonConfigData;

public class WatsonConversation {

	private static final String LOG_TAG = "WatsonConversation";
	private ConversationService service = null;
	private WatsonConversationListener listener;

	
	public WatsonConversation(WatsonConversationListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Takes a string to start or trigger a conversation(flow)
	 * returns the results to the listener
	 * @param text
	 */
	private void textToSpeech(String text){
	

			//Log.d(LOG_TAG, "Message: " + params);
		final String finalText = text;
			new Thread(new Runnable() {
				MessageRequest newMessage;

				@Override
				public void run() {
					if (finalText == null) {
						newMessage = new MessageRequest.Builder().build();
					} else {
						newMessage = new MessageRequest.Builder().inputText(finalText).build();
					}
					MessageResponse response = service.message(WatsonConfigData.CONVERSATION_WORKSPACE_ID, newMessage)
							.execute();
					if (response.getText().size() > 0) {
						Log.d(LOG_TAG, response.getText().get(0));

						listener.onConversationResult(response.getText().get(0));
					}
				}

			}).start();

		
	}
	
	
}

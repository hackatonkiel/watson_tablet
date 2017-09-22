package de.bewatec.hackerthon.service;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;

public class WatsonConversation {

	private static final String LOG_TAG = "Conversation";
	private ConversationService service = null;
	private WatsonConversationListener listener;

	
	public WatsonConversation(WatsonConversationListener listener) {
		this.listener = listener;
	}

	
	
	private void init(){
		
	}
	
}

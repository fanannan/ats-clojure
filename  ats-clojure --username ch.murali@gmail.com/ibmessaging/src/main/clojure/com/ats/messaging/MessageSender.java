package com.ats.messaging;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ITopic;


public class HazelCastMessageSender {
	private Map<String, ITopic> topicMap = new HashMap();
	public void sendMessage(final String topicName, final Object message) {
		ITopic<E> topic = topicMap.get(topicName);
		if (topic == null) {
			topic = Hazelcast.getTopic(topicName);
		}
		topic.publish(message);
	}
	
	public void addMessageListener(final String topicName, final Object listener) {
		ITopic<E> topic = topicMap.get(topicName);
		if (topic == null) {
			topic = Hazelcast.getTopic(topicName);
		}
		topic.addMessageListener(listener);
	}
}

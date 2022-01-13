package com.macys.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;

@Component
public class MessageSenderComponent {

	@Autowired
	private Queue jsonQueue;

	@Autowired
	private Queue xmlQueue;
	
	@Autowired
	AmqpTemplate xmlSendTemplate;

	@Autowired
	AmqpTemplate jsonSendTemplate;

	public void sendXMLMessage(FulfillmentOrder fulfillmentOrder) throws AmqpException {
		jsonSendTemplate.convertAndSend(xmlQueue.getName(), fulfillmentOrder);
	}

	public void sendJSONMessage(OrderMessageJson orderMessageJson) throws AmqpException {
		xmlSendTemplate.convertAndSend(jsonQueue.getName(), orderMessageJson);
	}
}
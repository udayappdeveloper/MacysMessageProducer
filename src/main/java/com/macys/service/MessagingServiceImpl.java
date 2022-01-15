package com.macys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.macys.component.MessageSenderComponent;
import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;
import com.macys.utility.MessagingConstants;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessageSenderComponent messageSender;
	@Autowired
	private PubSubTemplate pubSubTemplate;

	@Override
	public ResponseEntity<Boolean> sendXmlMessage(FulfillmentOrder fulfillmentOrder) {
		try {
			messageSender.sendXMLMessage(fulfillmentOrder);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> sendJsonMessage(OrderMessageJson orderMessageJson) {
		try {
			messageSender.sendJSONMessage(orderMessageJson);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> sendXmlMessageWithGCP(FulfillmentOrder fulfillmentOrder) {
		try {
			XmlMapper mapper = new XmlMapper();
			String xmlPayload = mapper.writeValueAsString(fulfillmentOrder);
			pubSubTemplate.publish(MessagingConstants.GCP_XML_TOPIC, xmlPayload);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Boolean> sendJsonMessageWithGCP(OrderMessageJson orderMessageJson) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonPayload = mapper.writeValueAsString(orderMessageJson);
			pubSubTemplate.publish(MessagingConstants.GCP_JSON_TOPIC, jsonPayload);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

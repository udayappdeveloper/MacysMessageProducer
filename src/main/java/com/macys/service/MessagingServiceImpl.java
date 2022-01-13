package com.macys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.macys.component.MessageSenderComponent;
import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessageSenderComponent messageSender;

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
}

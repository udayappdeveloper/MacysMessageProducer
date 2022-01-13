package com.macys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;
import com.macys.service.MessagingService;

@RestController
@RequestMapping(value = "/macys/producer")
public class MessagingController {

	@Autowired
	MessagingService messageService;

	@PostMapping(value = "/json", consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<Boolean> sendJsonMessage(@RequestBody OrderMessageJson orderMessageJson) {
		return messageService.sendJsonMessage(orderMessageJson);
	}

	@PostMapping(value = "/xml", consumes = { MediaType.APPLICATION_XML_VALUE })
	ResponseEntity<Boolean> sendXmlMessage(@RequestBody FulfillmentOrder fulfillmentOrder) {
		return messageService.sendXmlMessage(fulfillmentOrder);
	}

}

package com.macys.service;

import org.springframework.http.ResponseEntity;

import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;

public interface MessagingService {

	ResponseEntity<Boolean> sendXmlMessage(FulfillmentOrder fulfillmentOrder);

	ResponseEntity<Boolean> sendJsonMessage(OrderMessageJson orderMessageJson);

	ResponseEntity<Boolean> sendXmlMessageWithGCP(FulfillmentOrder fulfillmentOrder);

	ResponseEntity<Boolean> sendJsonMessageWithGCP(OrderMessageJson orderMessageJson);

}

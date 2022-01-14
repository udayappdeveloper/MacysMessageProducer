package com.macys.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.macys.dto.json.OrderMessageJson;
import com.macys.dto.xml.FulfillmentOrder;
import com.macys.service.MessagingService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessagingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MessagingService messagingService;

	@Test
	public void sendJsonMessageTest2() throws Exception {
		OrderMessageJson mockMsg = new OrderMessageJson();
		mockMsg.setMessageName("ITEMADDEDBYKALLURI");
		mockMsg.setCommand("ADD");
		mockMsg.setItemName("WALLETADDEDBYUDAY");
		mockMsg.setItemDescription("FLAT THICK WALLET");
		mockMsg.setItemLength(2.3);
		mockMsg.setItemWeight(1.5);
		mockMsg.setItemHeight(1.3);
		mockMsg.setImagePathname("");
		mockMsg.setRfidTagged("Y");
		mockMsg.setStorageAttribute(1);
		mockMsg.setPickType("");
		mockMsg.setUpcList("");
		when(messagingService.sendJsonMessage(mockMsg)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

		String inputJson = "{ \r\n" + "\r\n" + "    \"messageName\": \"ITEMADDEDBYKALLURI\", \r\n"
				+ "    \"command\": \"ADD\", \r\n" + "    \"itemName\": \"WALLETADDEDBYUDAY\", \r\n"
				+ "    \"itemDescription\": \"FLAT THICK WALLET\", \r\n" + "    \"itemLength\": 2.3, \r\n"
				+ "    \"itemWidth\": 0.5, \r\n" + "    \"itemHeight\": 1.3, \r\n" + "    \"itemWeight\": 1.5, \r\n"
				+ "    \"imagePathname\": null, \r\n" + "    \"rfidTagged\": \"true\", \r\n"
				+ "    \"storageAttribute\": 1, \r\n" + "    \"pickType\": \"F\", \r\n" + "    \"upcList\": null \r\n"
				+ "\r\n" + "} ";
		mockMvc.perform(post("/macys/producer/json").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void sendJsonMessageTest() throws Exception {
		OrderMessageJson mockMsg = new OrderMessageJson();

		mockMsg.setMessageName("ITEMADDEDBYKALLURI");
		mockMsg.setCommand("ADD");
		mockMsg.setItemName("WALLETADDEDBYUDAY");
		mockMsg.setItemDescription("FLAT THICK WALLET");
		mockMsg.setItemLength(2.3);
		mockMsg.setItemWeight(1.5);
		mockMsg.setItemHeight(1.3);
		mockMsg.setImagePathname("");
		mockMsg.setRfidTagged("Y");
		mockMsg.setStorageAttribute(1);
		mockMsg.setPickType("");
		mockMsg.setUpcList("");

		String inputJson = "{ \r\n" + "\r\n" + "    \"messageName\": \"ITEMADDEDBYKALLURI\", \r\n"
				+ "    \"command\": \"ADD\", \r\n" + "    \"itemName\": \"WALLETADDEDBYUDAY\", \r\n"
				+ "    \"itemDescription\": \"FLAT THICK WALLET\", \r\n" + "    \"itemLength\": 2.3, \r\n"
				+ "    \"itemWidth\": 0.5, \r\n" + "    \"itemHeight\": 1.3, \r\n" + "    \"itemWeight\": 1.5, \r\n"
				+ "    \"imagePathname\": null, \r\n" + "    \"rfidTagged\": \"true\", \r\n"
				+ "    \"storageAttribute\": 1, \r\n" + "    \"pickType\": \"F\", \r\n" + "    \"upcList\": null \r\n"
				+ "\r\n" + "} ";

		when(messagingService.sendJsonMessage(mockMsg)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/macys/producer/json")
				.accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void sendXmlMessageTest() throws Exception {
		FulfillmentOrder fullfillOrder = mock(FulfillmentOrder.class);

		String inputXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<ns0:fulfillmentOrder xmlns:ns0=\"http://www.mst.macys.com/main/eos/fulfillmentOrder/orderDetails.v1.1\">\r\n"
				+ "<ns0:orderID>490048499</ns0:orderID>\r\n" + "<ns0:orderTypeCode>Digital</ns0:orderTypeCode>\r\n"
				+ "<ns0:partnerOrderID>SAN10000137</ns0:partnerOrderID>\r\n"
				+ "<ns0:orderStatus>Created</ns0:orderStatus>\r\n"
				+ "<ns0:messageCreateTimeStamp>2017-08-16T15:20:29.657-04:00</ns0:messageCreateTimeStamp>\r\n"
				+ "<ns0:fulfillmentChannelCode>POOL</ns0:fulfillmentChannelCode>\r\n"
				+ "<ns0:orderStatusCode>110</ns0:orderStatusCode>\r\n"
				+ "<ns0:orderStatusDescription>IN_PROCESS</ns0:orderStatusDescription>\r\n"
				+ "<ns0:sellZLDivisionNbr>71</ns0:sellZLDivisionNbr>\r\n"
				+ "<ns0:sellZLLocationNbr>139</ns0:sellZLLocationNbr>\r\n" + "<ns0:source>\r\n"
				+ "<ns0:clientID>MCOM</ns0:clientID>\r\n" + "<ns0:subClientID>SITE</ns0:subClientID>\r\n"
				+ "<ns0:sellingChannelCode>MCOM</ns0:sellingChannelCode>\r\n"
				+ "<ns0:seperatorSource0>0</ns0:seperatorSource0>\r\n"
				+ "<ns0:separatorSource1>1</ns0:separatorSource1>\r\n" + "</ns0:source>\r\n" + "<ns0:orderTotals>\r\n"
				+ "<ns0:totalPurchaseAmount>41.95</ns0:totalPurchaseAmount>\r\n"
				+ "<ns0:seperatorOrderTotals0>0</ns0:seperatorOrderTotals0>\r\n" + "</ns0:orderTotals>\r\n"
				+ "<ns0:billingAddress>\r\n" + "<ns0:contact>\r\n" + "<ns0:custID>51238851</ns0:custID>\r\n"
				+ "<ns0:name>\r\n" + "<ns0:firstName>SUPRAJA</ns0:firstName>\r\n"
				+ "<ns0:lastName>CHIDURAL</ns0:lastName>\r\n" + "<ns0:seperatorName0>0</ns0:seperatorName0>\r\n"
				+ "<ns0:seperatorName1>1</ns0:seperatorName1>\r\n" + "</ns0:name>\r\n" + "<ns0:address>\r\n"
				+ "<ns0:line1>2200 Galvin Dr</ns0:line1>\r\n" + "<ns0:line2>2200 Galvin Dr</ns0:line2>\r\n"
				+ "<ns0:line3>2200 Galvin Dr</ns0:line3>\r\n" + "<ns0:city>BUFORD</ns0:city>\r\n"
				+ "<ns0:state>GA</ns0:state>\r\n" + "<ns0:zipCode>30087</ns0:zipCode>\r\n"
				+ "<ns0:countryCode>USA</ns0:countryCode>\r\n" + "<ns0:seperatorAddress0>0</ns0:seperatorAddress0>\r\n"
				+ "</ns0:address>\r\n" + "<ns0:daytimePhoneNbr>968-789-87642345</ns0:daytimePhoneNbr>\r\n"
				+ "<ns0:homePhoneNbr>968-789-8764</ns0:homePhoneNbr>\r\n"
				+ "<ns0:alternatePhoneNbr>741-119-3501</ns0:alternatePhoneNbr>\r\n"
				+ "<ns0:sendSMSMessage>false</ns0:sendSMSMessage>\r\n"
				+ "<ns0:emailAddress>SUPRAJA.CHIDURAL@MACYS.COM</ns0:emailAddress>\r\n"
				+ "<ns0:seperatorContact0>0</ns0:seperatorContact0>\r\n" + "</ns0:contact>\r\n"
				+ "</ns0:billingAddress>\r\n" + "</ns0:fulfillmentOrder>";

		when(messagingService.sendXmlMessage(fullfillOrder)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/macys/producer/xml")
				.accept(MediaType.APPLICATION_XML).content(inputXml).contentType(MediaType.APPLICATION_XML);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}

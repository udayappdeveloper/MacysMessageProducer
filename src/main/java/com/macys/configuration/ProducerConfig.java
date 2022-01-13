package com.macys.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.macys.utility.MessagingConstants;

@Configuration
public class ProducerConfig {

	@Bean
	Queue jsonQueue() {
		return new Queue(MessagingConstants.JSON_QUEUE, true);
	}

	@Bean
	Queue xmlQueue() {
		return new Queue(MessagingConstants.XML_QUEUE, true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(MessagingConstants.EXCHANGE);
	}

	@Bean
	Binding binding1(DirectExchange exchange) {
		return BindingBuilder.bind(jsonQueue()).to(exchange).with(jsonQueue().getName());
	}

	@Bean
	Binding binding2(DirectExchange exchange) {
		return BindingBuilder.bind(xmlQueue()).to(exchange).with(xmlQueue().getName());
	}

	
	@Bean
	public AmqpTemplate jsonSendTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setRoutingKey(MessagingConstants.ROUTING_KEY);
		rabbitTemplate.setMessageConverter(new Jackson2XmlMessageConverter());
		rabbitTemplate.setDefaultReceiveQueue(jsonQueue().getName());
		return rabbitTemplate;
	}
	
	
	@Bean
	public AmqpTemplate xmlSendTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setRoutingKey(MessagingConstants.ROUTING_KEY);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setDefaultReceiveQueue(xmlQueue().getName());
		return rabbitTemplate;
	}
	
	
	

}

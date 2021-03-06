package org.egov.propertytax.consumer;

import java.util.Map;

import org.egov.models.PropertyRequest;
import org.egov.propertytax.config.PropertiesManager;
import org.egov.propertytax.service.DemandService;
import org.egov.tracer.kafka.LogAwareKafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@Autowired
	PropertiesManager propertiesManager;

	@Autowired
	private DemandService demandService;

	@Autowired
	private LogAwareKafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * receive method
	 *
	 * @param PropertyRequest
	 *            This method is listened whenever property is created and
	 *            updated
	 */
	@KafkaListener(topics = { "#{propertiesManager.getCreatePropertyTaxCalculated()}" })
	public void receive(Map<String, Object> consumerRecord, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
			throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		PropertyRequest propertyRequest = objectMapper.convertValue(consumerRecord, PropertyRequest.class);
		log.info("consumer topic value is: " + topic + " consumer value is" + consumerRecord);
		demandService.createDemand(propertyRequest);
		log.info("demand generated >>>> \n next topic ----->> " + propertiesManager.getCreatePropertyTaxGenerated()
				+ " \n Property >>>>> " + propertyRequest);
		kafkaTemplate.send(propertiesManager.getCreatePropertyTaxGenerated(), propertyRequest);

	}
}

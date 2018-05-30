package com.wjma.apachekafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.dto.ServiceTxDTO;

@Configuration
public class SpringProducerConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> kafkaProps = new HashMap<>();
		kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return kafkaProps;
	}

	@Bean
	public ProducerFactory<String, ServiceTxDTO> producerFactoryTx() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public ProducerFactory<String, OrderDTO> producerFactoryDetail() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean(name="kafkaTemplateTx")
	public KafkaTemplate<String, ServiceTxDTO> kafkaTemplateTx() {
		return new KafkaTemplate<>(producerFactoryTx());
	}

	@Bean(name="kafkaTemplateDetail")
	public KafkaTemplate<String, OrderDTO> kafkaTemplateDetail() {
		return new KafkaTemplate<>(producerFactoryDetail());
	}
}

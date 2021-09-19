package com.boutiqaat.training.Online.Shop.Kafka;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.boutiqaat.training.Online.Shop.Entities.User;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka //for the consumer config
@Configuration
public class kafkaConfig {

	    @Bean
	    public ProducerFactory<String, User> producerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"); //here provide the kafka server you are communicating with 
	        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class); //key
	        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); //value

	        return new DefaultKafkaProducerFactory<>(config);
	    }

	    @Bean
	    public KafkaTemplate<String, User> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	    
	    @Bean
	    public ConsumerFactory<String, String> consumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);

	        return new DefaultKafkaConsumerFactory<>(config);
	    }


	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }
	    
	    
	    @Bean
	    public ConsumerFactory<String, User> userConsumerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
	                new JsonDeserializer<>(User.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(userConsumerFactory());
	        return factory;
	    }
	    
	    @Bean 
	    NewTopic topic1() {
	    	return TopicBuilder.name("AdminTopic")
	    			           .partitions(10)
	    			           .replicas(3)
	    			           .compact()
	    			           .build();
	 
	    }

	    @Bean 
	    NewTopic topic2() {
	    	return TopicBuilder.name("HelloTopic")
	    			           .partitions(10)
	    			           .replicas(3)
	    			           .compact()
	    			           .build();
	    	
	    }
	    
	    @Bean 
	    NewTopic topic3() {
	    	return TopicBuilder.name("ParTopic")
	    			           .partitions(10)
	    			           .replicas(3)
	    			           .compact()
	    			           .build();
	    	
	    }
	    
	    @Bean 
	    NewTopic topic4() {
	    	return TopicBuilder.name("Par2Topic")
	    			           .partitions(10)
	    			           .replicas(3)
	    			           .compact()
	    			           .build();
	    	
	    }
	    
	    @Bean 
	    NewTopic topic5() {
	    	return TopicBuilder.name("fa")
	    			           .partitions(10)
	    			           .replicas(3)
	    			           .compact()
	    			           .build();
	    	
	    }

	   
	
	
}

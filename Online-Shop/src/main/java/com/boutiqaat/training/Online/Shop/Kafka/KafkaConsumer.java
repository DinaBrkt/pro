package com.boutiqaat.training.Online.Shop.Kafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.boutiqaat.training.Online.Shop.Entities.User;

@Service
public class KafkaConsumer {

	//listening to string
    @KafkaListener(topics = "TestTopic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    //listening to json
    @KafkaListener(topics = "TestTopic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson( User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
    
    //consume messages from a specific partition //make it one topic example
    @KafkaListener( topicPartitions = 
    	{ @TopicPartition(topic = "fa", partitions = { "0","1" }), 
    	  @TopicPartition(topic = "Par2Topic", partitions = "0",
    	      partitionOffsets = @PartitionOffset(partition = "1",initialOffset ="3"))
        })
    public void listen(String str) {
        System.out.println("Consumed JSON Message from a partition: " + str);
    }
    
  
}
package com.boutiqaat.training.Online.Shop.Controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.validation.Valid;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boutiqaat.training.Online.Shop.Entities.User;
import com.boutiqaat.training.Online.Shop.Feign.CurrencyConversion;
import com.boutiqaat.training.Online.Shop.Feign.LimitsServiceProxy;
import com.boutiqaat.training.Online.Shop.Mappers.MapStructMapper;
import com.boutiqaat.training.Online.Shop.Repositories.UserRepo;
import com.boutiqaat.training.Online.Shop.Retrofit.RetrofitConfig;
import com.boutiqaat.training.Online.Shop.mapstruct.dtos.UserPostDto;

import retrofit2.Call;
import retrofit2.Response;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepository;
	
	@Autowired(required=false)
	private MapStructMapper mapstructmapper;
	
	@Autowired
	private RetrofitConfig retroconfig;
	
	@Autowired
	KafkaTemplate<String,User> kafkaTemplate;
	private static final String TOPIC ="HelloTopic";
	

	//create a new user
	@PostMapping("/mapstruct")
	public ResponseEntity<Void> createUser(@Valid @RequestBody UserPostDto userpostDto){
		
		userRepository.save(mapstructmapper.userPostDtoToUser(userpostDto));
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	//call an API
	@GetMapping("/retrofit")
	public void printQuote() throws IOException {
		
		final Call<String> call=(retroconfig.getquote()).quote();
		final Response<String> response=call.execute();
		final String quote;
		
		if(response.isSuccessful()) {
			quote = response.body();
			System.out.println(quote);
			
		}else {
			int httpErrorCode = response.code();
			String errorMsg = response.body();
			System.out.println(errorMsg);
		}
	    }
	
	  
	
	
	//sending to a topic in kafka
	@GetMapping("/kafka/publish")
	public String publish() {
		
		kafkaTemplate.send(TOPIC,User.builder().email("jaafar@gmail.com").password("pass").name("jaafar").surname("foster").build());
		return "published successfully";
	}
	
	//sending to a topic in kafka asynch
	@GetMapping("kafka/publish/2")
	public void sendToKafka() {
	   
	    ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(TOPIC,User.builder().email("ali@gmail.com").password("pass").name("ALI").surname("zubair").build());
	    future.addCallback(new KafkaSendCallback<String,User>() {

	        @Override
	        public void onSuccess(SendResult<String,User> result) {
	             //do something
	        }

	        @Override
	        public void onFailure(KafkaProducerException ex) {
	         ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();
	         System.out.println("Unable to send message due to : " + ex.getMessage());
	        }

	    });
	}
	
	
}

package com.boutiqaat.training.Online.Shop.Retrofit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

	
	public ThequoteAPI getquote() {
	     return new Retrofit.Builder()
			             .baseUrl("http://localhost:8080/")
			             .addConverterFactory(GsonConverterFactory.create())
			             .build()
			             .create(ThequoteAPI.class);
	}
	

}

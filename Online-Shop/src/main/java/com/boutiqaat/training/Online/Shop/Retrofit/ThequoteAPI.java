package com.boutiqaat.training.Online.Shop.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ThequoteAPI {

	@GET("/quote")
	Call<String> quote();
}

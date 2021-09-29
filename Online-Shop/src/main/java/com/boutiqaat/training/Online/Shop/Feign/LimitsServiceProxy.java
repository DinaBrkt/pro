package com.boutiqaat.training.Online.Shop.Feign;
import com.boutiqaat.training.Online.Shop.Feign.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="limits-service")
public interface LimitsServiceProxy {

	@GetMapping("/currency-exchange/{from}/to/{to}")
	public CurrencyConversion retrievCurrencyExchangeValue(
			@PathVariable String from ,@PathVariable String to
			);
	
}

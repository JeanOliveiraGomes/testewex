package com.wex.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wex.test.dto.RatesOfExchangeResponse;

@FeignClient(value = "jplaceholder", url = "https://api.fiscaldata.treasury.gov/services/api")
public interface FiscalDataAPI {
	
	@RequestMapping(method = RequestMethod.GET, value = "/fiscal_service/v1/accounting/od/rates_of_exchange", consumes = "application/json")
	RatesOfExchangeResponse getRatesOfExchange(@RequestParam("fields") String fields, @RequestParam("filter") String filter);

}

package com.wex.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeDataResponse {
	
	private String currency;
	
	private String exchange_rate;

	private String record_date;
}

package com.wex.test.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatesOfExchangeResponse {

	List<ExchangeDataResponse> data;
}

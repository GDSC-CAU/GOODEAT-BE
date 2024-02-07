package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.dto.CurrencyResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

  public List<CurrencyResponse> findAllCurrencies() {
    return Arrays.stream(Currency.values())
        .map(CurrencyResponse::from)
        .toList();
  }
}

package com.gdsc.goodeat.controller;

import com.gdsc.goodeat.dto.CalculatePriceRequest;
import com.gdsc.goodeat.dto.CalculatePriceResponse;
import com.gdsc.goodeat.dto.CurrencyResponse;
import com.gdsc.goodeat.service.CurrencyService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

  private final CurrencyService currencyService;

  public CurrencyController(final CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping("/currency")
  public ResponseEntity<List<CurrencyResponse>> getCurrencies() {
    final List<CurrencyResponse> currencyResponses = currencyService.findAllCurrencies();
    return ResponseEntity.ok(currencyResponses);
  }

  @PostMapping("/currency/calculate")
  public ResponseEntity<CalculatePriceResponse> calculateTotalPrice(
      @RequestBody final CalculatePriceRequest calculatePriceRequest
  ) {
    final CalculatePriceResponse response
        = currencyService.calculateOrdersPrice(calculatePriceRequest);
    return ResponseEntity.ok(response);
  }
}

package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.domain.CurrencyPriceConcater;
import com.gdsc.goodeat.dto.CalculatePriceRequest;
import com.gdsc.goodeat.dto.CalculatePriceRequest.OrderElementRequest;
import com.gdsc.goodeat.dto.CalculatePriceResponse;
import com.gdsc.goodeat.dto.CurrencyResponse;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {

  private final CurrencyPriceConcater currencyPriceConcater;

  public List<CurrencyResponse> findAllCurrencies() {
    return Arrays.stream(Currency.values())
        .map(CurrencyResponse::from)
        .toList();
  }

  public CalculatePriceResponse calculateOrdersPrice(final CalculatePriceRequest request) {
    final Currency originCurrency = Currency.fromCurrencyName(request.originCurrency());
    final Currency userCurrency = Currency.fromCurrencyName(request.userCurrency());

    final List<OrderElementRequest> orders = request.orders();

    final Double originTotalPrice = calculatePrice(orders, OrderElementRequest::originPrice);
    final Double userTotalPrice = calculatePrice(orders, OrderElementRequest::userPrice);

    final String originPriceWithCurrencyUnit = currencyPriceConcater
        .concatPriceWithCurrency(originCurrency.getISO4217Code(), originTotalPrice);
    final String userPriceWithCurrencyUnit = currencyPriceConcater
        .concatPriceWithCurrency(userCurrency.getISO4217Code(), userTotalPrice);

    return new CalculatePriceResponse(originPriceWithCurrencyUnit, userPriceWithCurrencyUnit);
  }

  private Double calculatePrice(
      final List<OrderElementRequest> orderElementRequests,
      final Function<OrderElementRequest, Double> priceSupplier
  ) {
    return orderElementRequests.stream()
        .mapToDouble(orderElement -> priceSupplier.apply(orderElement) * orderElement.quantity())
        .sum();
  }
}

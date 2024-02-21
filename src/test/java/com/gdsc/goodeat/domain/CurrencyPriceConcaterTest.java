package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Currency.SOUTH_KOREAN_WON;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CurrencyPriceConcaterTest {

  private final CurrencyPriceConcater concater = new CurrencyPriceConcater();

  @Test
  void 통화와_가격을_합칠_수_있다() {
    final Double amount = 41234.1234;

    final String actual = concater.concatPriceWithCurrency(
        SOUTH_KOREAN_WON.getISO4217Code(), amount
    );
    final String expected = "₩ 41,234.12";

    assertThat(actual)
        .isEqualTo(expected);
  }
}
package com.gdsc.goodeat.domain;


import java.text.NumberFormat;
import org.springframework.stereotype.Component;

@Component
public class CurrencyPriceConcater {

  private static final int DISPLAY_MAXIMUM_FRACTION_DIGITS_COUNT = 2;
  private static final int DISPLAY_MINIMUM_FRACTION_DIGITS_COUNT = 0;

  public String concatPriceWithCurrency(final String currencyIsoCode, final Double amount) {
    final NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
    numberFormatter.setCurrency(java.util.Currency.getInstance(currencyIsoCode));
    numberFormatter.setMaximumFractionDigits(DISPLAY_MAXIMUM_FRACTION_DIGITS_COUNT);
    numberFormatter.setMinimumFractionDigits(DISPLAY_MINIMUM_FRACTION_DIGITS_COUNT);
    final String priceWithCurrencyUnit = numberFormatter.format(amount);
    return addSpaceBetweenPriceAndUnit(priceWithCurrencyUnit);
  }

  private String addSpaceBetweenPriceAndUnit(final String priceWithCurrencyUnit) {
    final StringBuilder result = new StringBuilder();

    for (int i = 0; i < priceWithCurrencyUnit.length(); i++) {
      final char currentChar = priceWithCurrencyUnit.charAt(i);

      if (i > 0) {
        final char prevChar = priceWithCurrencyUnit.charAt(i - 1);
        if (isDifferentType(prevChar, currentChar) && isNotCommaAndDot(prevChar, currentChar)) {
          result.append(' ');
        }
      }
      result.append(currentChar);
    }
    return result.toString();
  }

  private boolean isNotCommaAndDot(final char prevChar, final char currentChar) {
    return prevChar != ',' && currentChar != ',' && prevChar != '.' && currentChar != '.';
  }

  private static boolean isDifferentType(final char prevChar, final char currentChar) {
    return (!Character.isDigit(prevChar) && Character.isDigit(currentChar)) ||
        (Character.isDigit(prevChar) && !Character.isDigit(currentChar));
  }
}


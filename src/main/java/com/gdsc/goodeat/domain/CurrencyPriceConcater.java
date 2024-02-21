package com.gdsc.goodeat.domain;


import java.text.NumberFormat;
import org.springframework.stereotype.Component;

@Component
public class CurrencyPriceConcater {

  public static final int DISPLAY_FRACTION_DIGITS_COUNT = 2;

  public String concatPriceWithCurrency(final Currency currency, final Double amount) {
    final NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
    numberFormatter.setCurrency(java.util.Currency.getInstance(currency.getISO4217Code()));
    numberFormatter.setMaximumFractionDigits(DISPLAY_FRACTION_DIGITS_COUNT);
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
    return !(Character.isDigit(prevChar) && Character.isDigit(currentChar)) ||
        (Character.isDigit(prevChar) && !Character.isDigit(currentChar));
  }
}


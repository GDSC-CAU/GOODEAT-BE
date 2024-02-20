package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Language.ENGLISH;
import static java.lang.System.lineSeparator;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScriptGeneratorImpl {

  private static final String ORDER_ITEM_FORMAT = "%d %s";
  private static final String ENGLISH_ORDER_SCRIPT_PREFIX = "Hello i want to order";

  private final TranslationClient translationClient;

  /**
   * @param sourceLanguage 주문 목록의 언어
   * @param targetLanguage 번역하고 싶은 언어
   * @param orderItems     주문 목록
   * @return sourceLanguage 형태의 스크립트 + targetLanguage 형태의 스크립트
   */
  public Script generate(
      final Language sourceLanguage, final Language targetLanguage, final List<OrderItem> orderItems
  ) {
    final String concatOrders = concatOrder(orderItems);

    final String orderScriptPrefix = translationClient.translate(
        ENGLISH, sourceLanguage, ENGLISH_ORDER_SCRIPT_PREFIX
    );

    final String userScript = orderScriptPrefix + lineSeparator() + concatOrders;
    final String travelScript = translationClient.translate(
        sourceLanguage, targetLanguage, userScript
    );

    return new Script(userScript, travelScript);
  }

  private static String concatOrder(final List<OrderItem> orderItems) {
    return orderItems.stream()
        .map(orderItem -> String.format(ORDER_ITEM_FORMAT, orderItem.quantity(),
            orderItem.originMenuName()))
        .collect(Collectors.joining(lineSeparator()));
  }
}

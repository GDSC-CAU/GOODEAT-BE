package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Language.VIETNAMESE;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class VietnamScriptGenerator implements ScriptGenerator {

  private static final String SCRIPT_TEMPLATE = "Xin chào. Tôi muốn đặt %s";
  private static final String MENU_ITEM_DELIMITER = " và ";
  private static final String MENU_ITEM_TEMPLATE = "%d %s";

  @Override
  public Script generate(final List<MenuItem> menuItems) {
    final String menuItemScript = menuItems.stream()
        .map(menuItem -> String.format(MENU_ITEM_TEMPLATE, menuItem.quantity(),
            menuItem.originMenuName()))
        .collect(Collectors.joining(MENU_ITEM_DELIMITER));
    final String script = String.format(SCRIPT_TEMPLATE, menuItemScript);

    return new Script(script);
  }

  @Override
  public Language supportedLanguage() {
    return VIETNAMESE;
  }
}

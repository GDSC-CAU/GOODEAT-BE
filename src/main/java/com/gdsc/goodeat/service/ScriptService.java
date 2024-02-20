package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.OrderItem;
import com.gdsc.goodeat.domain.Script;
import com.gdsc.goodeat.domain.ScriptGenerator;
import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptGenerateRequest.MenuItemRequest;
import com.gdsc.goodeat.dto.ScriptResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScriptService {

  private final ScriptGenerator scriptGenerator;

  public ScriptResponse generateScript(final ScriptGenerateRequest request) {
    final Language sourceLanguage = Language.fromLanguageName(request.originLanguageName());
    final Language targetLanguage = Language.fromLanguageName(request.userLanguageName());

    final List<OrderItem> orderItems = request.menuItems()
        .stream()
        .map(MenuItemRequest::toDomain)
        .toList();

    final Script script = scriptGenerator.generate(sourceLanguage, targetLanguage, orderItems);

    return new ScriptResponse(script.userScript(), script.originScript());
  }
}

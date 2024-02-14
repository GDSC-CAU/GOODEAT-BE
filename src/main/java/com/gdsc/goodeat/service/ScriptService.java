package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.OrderItem;
import com.gdsc.goodeat.domain.Script;
import com.gdsc.goodeat.domain.ScriptGeneratorComposite;
import com.gdsc.goodeat.domain.TranslationClient;
import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptGenerateRequest.MenuItemRequest;
import com.gdsc.goodeat.dto.ScriptResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScriptService {

  private final ScriptGeneratorComposite scriptGeneratorComposite;
  private final TranslationClient translationClient;

  public ScriptResponse generateScript(final ScriptGenerateRequest request) {
    final Language sourceLanguage = Language.fromLanguageName(request.originLanguageName());
    final Language targetLanguage = Language.fromLanguageName(request.userLanguageName());

    final List<OrderItem> orderItems = request.menuItems()
        .stream()
        .map(MenuItemRequest::toDomain)
        .toList();

    final Script travleScript = scriptGeneratorComposite.genrateScript(sourceLanguage, orderItems);
    final String userScript = translationClient
        .translate(sourceLanguage, targetLanguage, travleScript.script());

    return new ScriptResponse(userScript, travleScript.script());
  }
}

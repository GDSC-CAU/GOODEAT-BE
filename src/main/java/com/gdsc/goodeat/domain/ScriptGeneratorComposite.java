package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.ScriptExceptionType.SCRIPT_GENERATOR_NOT_FOUND;

import com.gdsc.goodeat.exception.ScriptException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ScriptGeneratorComposite {

  private final Map<Language, ScriptGenerator> scriptGeneratorMap;

  public ScriptGeneratorComposite(final List<ScriptGenerator> scriptGenerators) {
    scriptGeneratorMap = scriptGenerators.stream()
        .collect(Collectors.toMap(ScriptGenerator::supportedLanguage, Function.identity()));
  }

  public Script genrateScript(final Language language, final List<OrderItem> orderItems) {
    final Optional<ScriptGenerator> scriptGenerator = Optional.ofNullable(scriptGeneratorMap.get(language));

    return scriptGenerator
        .map(generator -> generator.generate(orderItems))
        .orElseThrow(() -> new ScriptException(SCRIPT_GENERATOR_NOT_FOUND));
  }
}

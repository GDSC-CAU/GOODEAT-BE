package com.gdsc.goodeat.domain;

import java.util.List;

public interface ScriptGenerator {

  Script generate(final List<MenuItem> menuItems);

  Language supportedLanguage();
}

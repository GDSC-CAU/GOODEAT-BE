package com.gdsc.goodeat.external;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.TranslationClient;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class GoogleTranslationClient implements TranslationClient {

  private final Translate translate;

  public GoogleTranslationClient(
      @Value("${google.credential.path:not prod}") final String path,
      @Value("${spring.profiles.active}") final String profile
  ) {
    if (profile.equals("prod")) {
      translate = initTranslateIfProd(path);
    } else {
      translate = new EmptyTranslate();
    }
  }

  private Translate initTranslateIfProd(final String path) {
    try (final InputStream serviceStream = new ClassPathResource(path).getInputStream()) {
      final ServiceAccountCredentials credential = ServiceAccountCredentials
          .fromStream(serviceStream);

      return TranslateOptions.newBuilder()
          .setCredentials(credential)
          .build()
          .getService();

    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String translate(final Language source, final Language target, final String content) {
    if (source == target) {
      return content;
    }
    final Translation translation = translate.translate(
        content
        , TranslateOption.sourceLanguage(source.getISO639Code())
        , TranslateOption.targetLanguage(target.getISO639Code())
        , TranslateOption.model("base")
    );
    return translation.getTranslatedText();
  }
}

package com.gdsc.goodeat.external;

import static org.apache.naming.ResourceRef.SCOPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.goodeat.domain.FoodInfo;
import com.gdsc.goodeat.domain.FoodScrapper;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class GeminiFoodScrapper implements FoodScrapper {

  private final GoogleCredentials credentials;
  private final ObjectMapper objectMapper;
  private final Map<String, FoodInfo> foodInfoCache = new ConcurrentHashMap<>();

  public GeminiFoodScrapper(
      @Value("${google.credential.path:not prod}") final String credentialPath,
      final ObjectMapper objectMapper
  ) throws IOException {
    this.credentials = GoogleCredentials
        .fromStream(new ClassPathResource(credentialPath)
            .getInputStream()).createScoped(SCOPE);
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public List<FoodInfo> scrape(final List<String> foodList) {
    return null;
  }
}

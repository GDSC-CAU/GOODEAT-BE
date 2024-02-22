package com.gdsc.goodeat.external;

import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_RESULT_IS_NOT_JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.goodeat.domain.FoodInfo;
import com.gdsc.goodeat.domain.FoodScrapper;
import com.gdsc.goodeat.exception.FoodException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

@Deprecated
public class GeminiFoodScrapper implements FoodScrapper {

  private static final String PROJECT_ID = "sc24-goodeat";
  private static final String LOCATION = "us-central1";
  private static final String MODEL_NAME = "gemini-pro-vision";
  private static final String SCOPE = "https://www.googleapis.com/auth/cloud-platform";

  private final GoogleCredentials credentials;
  private final ObjectMapper objectMapper;
  private final Map<String, FoodInfo> foodInfoCache = new ConcurrentHashMap<>();

  public GeminiFoodScrapper(
      @Value("${google.credential.path:not prod}") final String credentialPath
  ) throws IOException {
    this.credentials = GoogleCredentials
        .fromStream(new ClassPathResource(credentialPath)
            .getInputStream()).createScoped(SCOPE);
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public List<FoodInfo> scrap(final List<String> foodNames) {
    return foodNames.stream()
        .map(foodName -> foodInfoCache.computeIfAbsent(foodName, this::scrapFoodInfo))
        .toList();
  }

  private FoodInfo scrapFoodInfo(final String foodName) {
    try (final VertexAI vertexAI = new VertexAI(PROJECT_ID, LOCATION, credentials)) {
      final GenerativeModel model = new GenerativeModel(MODEL_NAME, vertexAI);

      final GenerateContentResponse response = model.generateContent(
          ContentMaker.fromString(
              String.format(
                  "Get the 200-character description and image url for food %s from the tasteatlas site\n",
                  foodName)
                  + "Please print it out in valid JSON format\n"
                  + "{"
                  + "image: valid image url of food (String)"
                  + "description: 200-character description of food(String)"
                  + "}"
          ));
      final String unicodeString = response.getCandidates(0).getContent().getParts(0).getText();
      final String serializedString = decodeUnicode(unicodeString);

      return deserialize(serializedString);
    } catch (final IOException e) {
      throw new FoodException(FOOD_RESULT_IS_NOT_JSON);
    }
  }

  private String decodeUnicode(String unicodeString) {
    final StringBuilder result = new StringBuilder();
    int index = 0;
    while (index < unicodeString.length()) {
      char c = unicodeString.charAt(index);
      if (c == '\\' && index + 1 < unicodeString.length()) {
        char nextChar = unicodeString.charAt(index + 1);
        if (nextChar == 'u' && index + 5 < unicodeString.length()) {
          String unicode = unicodeString.substring(index + 2, index + 6);
          int codePoint = Integer.parseInt(unicode, 16);
          result.append((char) codePoint);
          index += 6; // Move to the next Unicode sequence
          continue;
        }
      }
      result.append(c);
      index++;
    }

    return removeMarkDownSyntax(result.toString());
  }

  private String removeMarkDownSyntax(final String result) {
    return result
        .replaceFirst("json", "")
        .replaceFirst("JSON", "")
        .replaceAll("```", "");
  }

  public FoodInfo deserialize(final String serializeStr) {
    try {
      return objectMapper.readValue(serializeStr, FoodInfo.class);
    } catch (final JsonProcessingException e) {
      throw new FoodException(FOOD_RESULT_IS_NOT_JSON);
    }
  }
}

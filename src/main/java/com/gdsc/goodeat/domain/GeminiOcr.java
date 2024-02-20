package com.gdsc.goodeat.domain;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public class GeminiOcr {
  private final String PROJECT_ID = "sc24-goodeat";
  private final String LOCATION = "us-central1";
  private final String MODEL_NAME = "gemini-pro-vision";
  private final String SCOPE = "https://www.googleapis.com/auth/cloud-platform";

  List<MenuItem> read(final String base64encodedImage) throws IOException {

    GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("credentials.json")
        .getInputStream()).createScoped(SCOPE);

    ByteString byteStringImage = ByteString.copyFrom(Base64.getDecoder().decode(base64encodedImage));

    String output = runGemini(PROJECT_ID, LOCATION, MODEL_NAME, credentials, byteStringImage);

    System.out.println(output);

    List<MenuItem> menuItemList = new ArrayList<>();
    menuItemList.add(new MenuItem("bun", new Price(Currency.SOUTH_KOREAN_WON, 0.0)));
    return menuItemList;
  }

  public String runGemini(String projectId, String location, String modelName, GoogleCredentials credentials, ByteString byteStringImage)
      throws IOException {
    try (VertexAI vertexAI = new VertexAI(projectId, location, credentials)) {
      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
      GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
          PartMaker.fromMimeTypeAndData("image/jpeg", byteStringImage),
          "Please extract the name and price of the food from this menu image.\n"
          + "Please print it out in JSON format\n"
              + "[{"
              + "name: name of food (String)"
              + "price: price of food (double)"
              + "}, ... ]"
      ));

      String unicodeString = response.getCandidates(0).getContent().getParts(0).getText();

      return decodeUnicode(unicodeString);
    }
  }

  private String decodeUnicode(String unicodeString) {
    StringBuilder result = new StringBuilder();
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

    return result.toString();
  }
}

package com.gdsc.goodeat.domain;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

public class Quickstart {
  public static void main(String[] args) throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "sc24-goodeat";
    String location = "us-central1";
    String modelName = "gemini-pro-vision";
    GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("credentials.json").getInputStream()).createScoped("https://www.googleapis.com/auth/cloud-platform");


    String output = quickstart(projectId, location, modelName, credentials);
    System.out.println(output);
  }

  // Analyzes the provided Multimodal input.
  public static String quickstart(String projectId, String location, String modelName, GoogleCredentials credentials)
      throws IOException {
    // Initialize client that will be used to send requests. This client only needs
    // to be created once, and can be reused for multiple requests.
    try (VertexAI vertexAI = new VertexAI(projectId, location, credentials)) {
      String imageUri = "gs://generativeai-downloads/images/scones.jpg";

      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
      GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
          PartMaker.fromMimeTypeAndData("image/jpeg", imageUri),
          "What's in this photo"
      ));

      return response.toString();
    }
  }

}
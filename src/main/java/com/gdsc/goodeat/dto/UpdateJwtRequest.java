package com.gdsc.goodeat.dto;

public class UpdateJwtRequest {

  private final String token;

  private UpdateJwtRequest() {
    this(null);
  }

  public UpdateJwtRequest(final String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}

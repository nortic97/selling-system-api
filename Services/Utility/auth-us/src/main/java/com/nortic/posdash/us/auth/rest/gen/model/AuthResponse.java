package com.nortic.posdash.us.auth.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AuthResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-23T19:51:55.445686500-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class AuthResponse {

  private String accessToken;

  private String refreshToken;

  private String type;

  public AuthResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AuthResponse(String accessToken, String refreshToken, String type) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.type = type;
  }

  public AuthResponse accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * JWT access token.
   * @return accessToken
   */
  @NotNull 
  @Schema(name = "accessToken", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", description = "JWT access token.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accessToken")
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public AuthResponse refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  /**
   * JWT refresh token.
   * @return refreshToken
   */
  @NotNull 
  @Schema(name = "refreshToken", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", description = "JWT refresh token.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("refreshToken")
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public AuthResponse type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Token type (usually \"Bearer\").
   * @return type
   */
  @NotNull 
  @Schema(name = "type", example = "Bearer", description = "Token type (usually \"Bearer\").", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthResponse authResponse = (AuthResponse) o;
    return Objects.equals(this.accessToken, authResponse.accessToken) &&
        Objects.equals(this.refreshToken, authResponse.refreshToken) &&
        Objects.equals(this.type, authResponse.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, refreshToken, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthResponse {\n");
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


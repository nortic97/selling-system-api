package com.nortic.pos.es.users.rest.gen.model;

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
 * HttpError
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-25T16:38:56.733506700-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class HttpError {

  private Integer code;

  private String message;

  public HttpError() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public HttpError(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public HttpError code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * HTTP status code.
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "400", description = "HTTP status code.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public HttpError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Error message.
   * @return message
   */
  @NotNull 
  @Schema(name = "message", example = "Invalid request body.", description = "Error message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HttpError httpError = (HttpError) o;
    return Objects.equals(this.code, httpError.code) &&
        Objects.equals(this.message, httpError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HttpError {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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


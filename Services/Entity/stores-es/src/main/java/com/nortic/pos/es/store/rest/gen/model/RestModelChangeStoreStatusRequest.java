package com.nortic.pos.es.store.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RestModelChangeStoreStatusRequest
 */

@JsonTypeName("changeStoreStatus_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-29T20:16:59.680500100-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelChangeStoreStatusRequest {

  private Boolean isActive;

  public RestModelChangeStoreStatusRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelChangeStoreStatusRequest(Boolean isActive) {
    this.isActive = isActive;
  }

  public RestModelChangeStoreStatusRequest isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
   */
  @NotNull 
  @Schema(name = "isActive", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isActive")
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelChangeStoreStatusRequest changeStoreStatusRequest = (RestModelChangeStoreStatusRequest) o;
    return Objects.equals(this.isActive, changeStoreStatusRequest.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelChangeStoreStatusRequest {\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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


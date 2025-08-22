package com.nortic.pos.es.role.rest.gen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * RestModelChangeRoleStatusRequest
 */

@JsonTypeName("changeRoleStatus_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-26T11:34:35.336979600-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelChangeRoleStatusRequest {

  private Boolean isActive;

  public RestModelChangeRoleStatusRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelChangeRoleStatusRequest(Boolean isActive) {
    this.isActive = isActive;
  }

  public RestModelChangeRoleStatusRequest isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
   */
  @NotNull 
  @Schema(name = "isActive", requiredMode = Schema.RequiredMode.REQUIRED)
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
    RestModelChangeRoleStatusRequest changeRoleStatusRequest = (RestModelChangeRoleStatusRequest) o;
    return Objects.equals(this.isActive, changeRoleStatusRequest.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelChangeRoleStatusRequest {\n");
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


package com.nortic.pos.es.branch.rest.gen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * RestModelChangeBranchStatusRequest
 */

@JsonTypeName("changeBranchStatus_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-26T09:11:53.008208800-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelChangeBranchStatusRequest {

  private Boolean isActive;

  public RestModelChangeBranchStatusRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelChangeBranchStatusRequest(Boolean isActive) {
    this.isActive = isActive;
  }

  public RestModelChangeBranchStatusRequest isActive(Boolean isActive) {
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
    RestModelChangeBranchStatusRequest changeBranchStatusRequest = (RestModelChangeBranchStatusRequest) o;
    return Objects.equals(this.isActive, changeBranchStatusRequest.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelChangeBranchStatusRequest {\n");
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


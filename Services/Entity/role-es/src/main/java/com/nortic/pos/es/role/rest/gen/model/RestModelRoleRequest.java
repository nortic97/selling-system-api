package com.nortic.pos.es.role.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RestModelRoleRequest
 */

@JsonTypeName("RoleRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-29T09:58:34.784626200-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelRoleRequest {

  private UUID storeId;

  private String name;

  private @Nullable String description;

  public RestModelRoleRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelRoleRequest(UUID storeId, String name) {
    this.storeId = storeId;
    this.name = name;
  }

  public RestModelRoleRequest storeId(UUID storeId) {
    this.storeId = storeId;
    return this;
  }

  /**
   * Get storeId
   * @return storeId
   */
  @NotNull @Valid 
  @Schema(name = "storeId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("storeId")
  public UUID getStoreId() {
    return storeId;
  }

  public void setStoreId(UUID storeId) {
    this.storeId = storeId;
  }

  public RestModelRoleRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull @Size(min = 3, max = 50) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RestModelRoleRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @Size(max = 255) 
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelRoleRequest roleRequest = (RestModelRoleRequest) o;
    return Objects.equals(this.storeId, roleRequest.storeId) &&
        Objects.equals(this.name, roleRequest.name) &&
        Objects.equals(this.description, roleRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storeId, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelRoleRequest {\n");
    sb.append("    storeId: ").append(toIndentedString(storeId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


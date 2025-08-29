package com.nortic.pos.es.role.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nortic.pos.es.role.rest.gen.model.RestModelRole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RestModelRolePagination
 */

@JsonTypeName("RolePagination")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-29T09:58:34.784626200-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelRolePagination {

  private @Nullable Integer totalItems;

  private @Nullable Integer totalPages;

  private Integer page;

  private Integer limit;

  private Boolean hasNextPage;

  private Boolean hasPreviousPage;

  @Valid
  private List<@Valid RestModelRole> rolesList = new ArrayList<>();

  public RestModelRolePagination() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelRolePagination(Integer page, Integer limit, Boolean hasNextPage, Boolean hasPreviousPage) {
    this.page = page;
    this.limit = limit;
    this.hasNextPage = hasNextPage;
    this.hasPreviousPage = hasPreviousPage;
  }

  public RestModelRolePagination totalItems(Integer totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Total number of roles matching the query in that store
   * @return totalItems
   */
  
  @Schema(name = "totalItems", example = "300", description = "Total number of roles matching the query in that store", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalItems")
  public Integer getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Integer totalItems) {
    this.totalItems = totalItems;
  }

  public RestModelRolePagination totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Total number of pages
   * @return totalPages
   */
  
  @Schema(name = "totalPages", example = "30", description = "Total number of pages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public RestModelRolePagination page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Current page number
   * @return page
   */
  @NotNull 
  @Schema(name = "page", example = "2", description = "Current page number", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public RestModelRolePagination limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Number of roles per page
   * @return limit
   */
  @NotNull 
  @Schema(name = "limit", example = "10", description = "Number of roles per page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("limit")
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public RestModelRolePagination hasNextPage(Boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
    return this;
  }

  /**
   * True if there is a next page
   * @return hasNextPage
   */
  @NotNull 
  @Schema(name = "hasNextPage", example = "true", description = "True if there is a next page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hasNextPage")
  public Boolean getHasNextPage() {
    return hasNextPage;
  }

  public void setHasNextPage(Boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  public RestModelRolePagination hasPreviousPage(Boolean hasPreviousPage) {
    this.hasPreviousPage = hasPreviousPage;
    return this;
  }

  /**
   * True if there is a previous page
   * @return hasPreviousPage
   */
  @NotNull 
  @Schema(name = "hasPreviousPage", example = "true", description = "True if there is a previous page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hasPreviousPage")
  public Boolean getHasPreviousPage() {
    return hasPreviousPage;
  }

  public void setHasPreviousPage(Boolean hasPreviousPage) {
    this.hasPreviousPage = hasPreviousPage;
  }

  public RestModelRolePagination rolesList(List<@Valid RestModelRole> rolesList) {
    this.rolesList = rolesList;
    return this;
  }

  public RestModelRolePagination addRolesListItem(RestModelRole rolesListItem) {
    if (this.rolesList == null) {
      this.rolesList = new ArrayList<>();
    }
    this.rolesList.add(rolesListItem);
    return this;
  }

  /**
   * Get rolesList
   * @return rolesList
   */
  @Valid 
  @Schema(name = "rolesList", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rolesList")
  public List<@Valid RestModelRole> getRolesList() {
    return rolesList;
  }

  public void setRolesList(List<@Valid RestModelRole> rolesList) {
    this.rolesList = rolesList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelRolePagination rolePagination = (RestModelRolePagination) o;
    return Objects.equals(this.totalItems, rolePagination.totalItems) &&
        Objects.equals(this.totalPages, rolePagination.totalPages) &&
        Objects.equals(this.page, rolePagination.page) &&
        Objects.equals(this.limit, rolePagination.limit) &&
        Objects.equals(this.hasNextPage, rolePagination.hasNextPage) &&
        Objects.equals(this.hasPreviousPage, rolePagination.hasPreviousPage) &&
        Objects.equals(this.rolesList, rolePagination.rolesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalItems, totalPages, page, limit, hasNextPage, hasPreviousPage, rolesList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelRolePagination {\n");
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    hasNextPage: ").append(toIndentedString(hasNextPage)).append("\n");
    sb.append("    hasPreviousPage: ").append(toIndentedString(hasPreviousPage)).append("\n");
    sb.append("    rolesList: ").append(toIndentedString(rolesList)).append("\n");
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


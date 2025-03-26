package com.nortic.pos.es.users.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nortic.pos.es.users.rest.gen.model.RestModelUser;
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
 * RestModelPaginatedUsers
 */

@JsonTypeName("PaginatedUsers")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T15:48:27.001507100-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelPaginatedUsers {

  private @Nullable Integer totalPages;

  private @Nullable Integer totalElements;

  private @Nullable Integer size;

  private @Nullable Integer currentPage;

  @Valid
  private List<@Valid RestModelUser> users = new ArrayList<>();

  public RestModelPaginatedUsers totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
   */
  
  @Schema(name = "totalPages", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public RestModelPaginatedUsers totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
   */
  
  @Schema(name = "totalElements", example = "50", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalElements")
  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public RestModelPaginatedUsers size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   */
  
  @Schema(name = "size", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public RestModelPaginatedUsers currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * @return currentPage
   */
  
  @Schema(name = "currentPage", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currentPage")
  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public RestModelPaginatedUsers users(List<@Valid RestModelUser> users) {
    this.users = users;
    return this;
  }

  public RestModelPaginatedUsers addUsersItem(RestModelUser usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
   */
  @Valid 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("users")
  public List<@Valid RestModelUser> getUsers() {
    return users;
  }

  public void setUsers(List<@Valid RestModelUser> users) {
    this.users = users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelPaginatedUsers paginatedUsers = (RestModelPaginatedUsers) o;
    return Objects.equals(this.totalPages, paginatedUsers.totalPages) &&
        Objects.equals(this.totalElements, paginatedUsers.totalElements) &&
        Objects.equals(this.size, paginatedUsers.size) &&
        Objects.equals(this.currentPage, paginatedUsers.currentPage) &&
        Objects.equals(this.users, paginatedUsers.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPages, totalElements, size, currentPage, users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelPaginatedUsers {\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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


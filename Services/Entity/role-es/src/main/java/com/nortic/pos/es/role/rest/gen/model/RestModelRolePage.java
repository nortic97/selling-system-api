package com.nortic.pos.es.role.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nortic.pos.es.role.rest.gen.model.RestModelPageable;
import com.nortic.pos.es.role.rest.gen.model.RestModelRole;
import com.nortic.pos.es.role.rest.gen.model.RestModelSort;
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
 * RestModelRolePage
 */

@JsonTypeName("RolePage")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-29T09:58:34.784626200-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelRolePage {

  @Valid
  private List<@Valid RestModelRole> content = new ArrayList<>();

  private @Nullable RestModelPageable pageable;

  private @Nullable Boolean last;

  private @Nullable Integer totalPages;

  private @Nullable Long totalElements;

  private @Nullable Integer size;

  private @Nullable Integer number;

  private @Nullable RestModelSort sort;

  private @Nullable Boolean first;

  private @Nullable Integer numberOfElements;

  private @Nullable Boolean empty;

  public RestModelRolePage content(List<@Valid RestModelRole> content) {
    this.content = content;
    return this;
  }

  public RestModelRolePage addContentItem(RestModelRole contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
   */
  @Valid 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public List<@Valid RestModelRole> getContent() {
    return content;
  }

  public void setContent(List<@Valid RestModelRole> content) {
    this.content = content;
  }

  public RestModelRolePage pageable(RestModelPageable pageable) {
    this.pageable = pageable;
    return this;
  }

  /**
   * Get pageable
   * @return pageable
   */
  @Valid 
  @Schema(name = "pageable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageable")
  public RestModelPageable getPageable() {
    return pageable;
  }

  public void setPageable(RestModelPageable pageable) {
    this.pageable = pageable;
  }

  public RestModelRolePage last(Boolean last) {
    this.last = last;
    return this;
  }

  /**
   * Get last
   * @return last
   */
  
  @Schema(name = "last", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last")
  public Boolean getLast() {
    return last;
  }

  public void setLast(Boolean last) {
    this.last = last;
  }

  public RestModelRolePage totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
   */
  
  @Schema(name = "totalPages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public RestModelRolePage totalElements(Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
   */
  
  @Schema(name = "totalElements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalElements")
  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public RestModelRolePage size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   */
  
  @Schema(name = "size", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public RestModelRolePage number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
   */
  
  @Schema(name = "number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public RestModelRolePage sort(RestModelSort sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get sort
   * @return sort
   */
  @Valid 
  @Schema(name = "sort", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sort")
  public RestModelSort getSort() {
    return sort;
  }

  public void setSort(RestModelSort sort) {
    this.sort = sort;
  }

  public RestModelRolePage first(Boolean first) {
    this.first = first;
    return this;
  }

  /**
   * Get first
   * @return first
   */
  
  @Schema(name = "first", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first")
  public Boolean getFirst() {
    return first;
  }

  public void setFirst(Boolean first) {
    this.first = first;
  }

  public RestModelRolePage numberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
    return this;
  }

  /**
   * Get numberOfElements
   * @return numberOfElements
   */
  
  @Schema(name = "numberOfElements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfElements")
  public Integer getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public RestModelRolePage empty(Boolean empty) {
    this.empty = empty;
    return this;
  }

  /**
   * Get empty
   * @return empty
   */
  
  @Schema(name = "empty", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("empty")
  public Boolean getEmpty() {
    return empty;
  }

  public void setEmpty(Boolean empty) {
    this.empty = empty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelRolePage rolePage = (RestModelRolePage) o;
    return Objects.equals(this.content, rolePage.content) &&
        Objects.equals(this.pageable, rolePage.pageable) &&
        Objects.equals(this.last, rolePage.last) &&
        Objects.equals(this.totalPages, rolePage.totalPages) &&
        Objects.equals(this.totalElements, rolePage.totalElements) &&
        Objects.equals(this.size, rolePage.size) &&
        Objects.equals(this.number, rolePage.number) &&
        Objects.equals(this.sort, rolePage.sort) &&
        Objects.equals(this.first, rolePage.first) &&
        Objects.equals(this.numberOfElements, rolePage.numberOfElements) &&
        Objects.equals(this.empty, rolePage.empty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, pageable, last, totalPages, totalElements, size, number, sort, first, numberOfElements, empty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelRolePage {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    pageable: ").append(toIndentedString(pageable)).append("\n");
    sb.append("    last: ").append(toIndentedString(last)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    first: ").append(toIndentedString(first)).append("\n");
    sb.append("    numberOfElements: ").append(toIndentedString(numberOfElements)).append("\n");
    sb.append("    empty: ").append(toIndentedString(empty)).append("\n");
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


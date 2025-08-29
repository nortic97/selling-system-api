package com.nortic.pos.es.role.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nortic.pos.es.role.rest.gen.model.RestModelSort;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RestModelPageable
 */

@JsonTypeName("Pageable")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-29T09:58:34.784626200-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelPageable {

  private @Nullable Integer pageNumber;

  private @Nullable Integer pageSize;

  private @Nullable RestModelSort sort;

  private @Nullable Long offset;

  private @Nullable Boolean paged;

  private @Nullable Boolean unpaged;

  public RestModelPageable pageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  /**
   * Get pageNumber
   * @return pageNumber
   */
  
  @Schema(name = "pageNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageNumber")
  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public RestModelPageable pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
   */
  
  @Schema(name = "pageSize", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public RestModelPageable sort(RestModelSort sort) {
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

  public RestModelPageable offset(Long offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Get offset
   * @return offset
   */
  
  @Schema(name = "offset", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offset")
  public Long getOffset() {
    return offset;
  }

  public void setOffset(Long offset) {
    this.offset = offset;
  }

  public RestModelPageable paged(Boolean paged) {
    this.paged = paged;
    return this;
  }

  /**
   * Get paged
   * @return paged
   */
  
  @Schema(name = "paged", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("paged")
  public Boolean getPaged() {
    return paged;
  }

  public void setPaged(Boolean paged) {
    this.paged = paged;
  }

  public RestModelPageable unpaged(Boolean unpaged) {
    this.unpaged = unpaged;
    return this;
  }

  /**
   * Get unpaged
   * @return unpaged
   */
  
  @Schema(name = "unpaged", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unpaged")
  public Boolean getUnpaged() {
    return unpaged;
  }

  public void setUnpaged(Boolean unpaged) {
    this.unpaged = unpaged;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelPageable pageable = (RestModelPageable) o;
    return Objects.equals(this.pageNumber, pageable.pageNumber) &&
        Objects.equals(this.pageSize, pageable.pageSize) &&
        Objects.equals(this.sort, pageable.sort) &&
        Objects.equals(this.offset, pageable.offset) &&
        Objects.equals(this.paged, pageable.paged) &&
        Objects.equals(this.unpaged, pageable.unpaged);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNumber, pageSize, sort, offset, paged, unpaged);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelPageable {\n");
    sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    paged: ").append(toIndentedString(paged)).append("\n");
    sb.append("    unpaged: ").append(toIndentedString(unpaged)).append("\n");
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


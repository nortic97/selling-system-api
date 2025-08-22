package com.nortic.pos.es.branch.rest.gen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RestModelBranchPagination
 */

@JsonTypeName("BranchPagination")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-26T09:11:53.008208800-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public class RestModelBranchPagination {

  private @Nullable Integer totalItems;

  private @Nullable Integer totalPages;

  private Integer page;

  private Integer limit;

  private Boolean hasNextPage;

  private Boolean hasPreviousPage;

  @Valid
  private List<@Valid RestModelBranch> branchesList = new ArrayList<>();

  public RestModelBranchPagination() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RestModelBranchPagination(Integer page, Integer limit, Boolean hasNextPage, Boolean hasPreviousPage) {
    this.page = page;
    this.limit = limit;
    this.hasNextPage = hasNextPage;
    this.hasPreviousPage = hasPreviousPage;
  }

  public RestModelBranchPagination totalItems(Integer totalItems) {
    this.totalItems = totalItems;
    return this;
  }

  /**
   * Total number of branches matching the query in that store
   * @return totalItems
   */
  
  @Schema(name = "totalItems", example = "300", description = "Total number of branches matching the query in that store", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalItems")
  public Integer getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(Integer totalItems) {
    this.totalItems = totalItems;
  }

  public RestModelBranchPagination totalPages(Integer totalPages) {
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

  public RestModelBranchPagination page(Integer page) {
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

  public RestModelBranchPagination limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Number of branches per page
   * @return limit
   */
  @NotNull 
  @Schema(name = "limit", example = "10", description = "Number of branches per page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("limit")
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public RestModelBranchPagination hasNextPage(Boolean hasNextPage) {
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

  public RestModelBranchPagination hasPreviousPage(Boolean hasPreviousPage) {
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

  public RestModelBranchPagination branchesList(List<@Valid RestModelBranch> branchesList) {
    this.branchesList = branchesList;
    return this;
  }

  public RestModelBranchPagination addBranchesListItem(RestModelBranch branchesListItem) {
    if (this.branchesList == null) {
      this.branchesList = new ArrayList<>();
    }
    this.branchesList.add(branchesListItem);
    return this;
  }

  /**
   * Get branchesList
   * @return branchesList
   */
  @Valid 
  @Schema(name = "branchesList", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("branchesList")
  public List<@Valid RestModelBranch> getBranchesList() {
    return branchesList;
  }

  public void setBranchesList(List<@Valid RestModelBranch> branchesList) {
    this.branchesList = branchesList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestModelBranchPagination branchPagination = (RestModelBranchPagination) o;
    return Objects.equals(this.totalItems, branchPagination.totalItems) &&
        Objects.equals(this.totalPages, branchPagination.totalPages) &&
        Objects.equals(this.page, branchPagination.page) &&
        Objects.equals(this.limit, branchPagination.limit) &&
        Objects.equals(this.hasNextPage, branchPagination.hasNextPage) &&
        Objects.equals(this.hasPreviousPage, branchPagination.hasPreviousPage) &&
        Objects.equals(this.branchesList, branchPagination.branchesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalItems, totalPages, page, limit, hasNextPage, hasPreviousPage, branchesList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestModelBranchPagination {\n");
    sb.append("    totalItems: ").append(toIndentedString(totalItems)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    hasNextPage: ").append(toIndentedString(hasNextPage)).append("\n");
    sb.append("    hasPreviousPage: ").append(toIndentedString(hasPreviousPage)).append("\n");
    sb.append("    branchesList: ").append(toIndentedString(branchesList)).append("\n");
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


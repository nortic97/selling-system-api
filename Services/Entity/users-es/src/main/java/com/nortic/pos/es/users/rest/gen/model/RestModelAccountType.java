package com.nortic.pos.es.users.rest.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets AccountType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-26T15:48:27.001507100-05:00[America/Bogota]", comments = "Generator version: 7.12.0")
public enum RestModelAccountType {
  
  INTERNAL("Internal"),
  
  STORE_OWNER("Store owner"),
  
  EMPLOYEE("Employee");

  private String value;

  RestModelAccountType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static RestModelAccountType fromValue(String value) {
    for (RestModelAccountType b : RestModelAccountType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}


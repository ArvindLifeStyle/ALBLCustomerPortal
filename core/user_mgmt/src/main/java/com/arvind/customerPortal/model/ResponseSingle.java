package com.arvind.customerPortal.model;

import java.util.Objects;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSingle   {
  @JsonProperty("status")
  private Status status = null;

  @JsonProperty("store")
  private Store store = null;

  public ResponseSingle status(Status status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/

  @Valid

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public ResponseSingle store(Store store) {
    this.store = store;
    return this;
  }

   /**
   * Get store
   * @return store
  **/

  @Valid

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseSingle responseSingle = (ResponseSingle) o;
    return Objects.equals(this.status, responseSingle.status) &&
        Objects.equals(this.store, responseSingle.store);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, store);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseSingle {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    store: ").append(toIndentedString(store)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

public class RequestUserstore   {
  @JsonProperty("userstore")
  private Userstore userstore = null;

  public RequestUserstore userstore(Userstore userstore) {
    this.userstore = userstore;
    return this;
  }

  @Valid

  public Userstore getUserstore() {
    return userstore;
  }

  public void setUserstore(Userstore userstore) {
    this.userstore = userstore;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestUserstore requestUserstore = (RequestUserstore) o;
    return Objects.equals(this.userstore, requestUserstore.userstore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userstore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestUserstore {\n");
    
    sb.append("    userstore: ").append(toIndentedString(userstore)).append("\n");
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


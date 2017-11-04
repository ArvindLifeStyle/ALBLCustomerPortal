package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class Store   {
  @JsonProperty("name")
  @NotNull
  private String name = null;

  @JsonProperty("address")
  @NotNull
  private String address = null;

  @JsonProperty("storeid")
  @NotNull
  private String storeid;

  @JsonProperty("phone")
  @NotNull
  private Phone phone = null;

  public Store name(String name) {
    this.name = name;
    return this;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Store address(String address) {
    this.address = address;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Store storeid(String storeid) {
    this.storeid = storeid;
    return this;
  }

  public String getStoreid() {
    return storeid;
  }

  public void setStoreid(String storeid) {
    this.storeid = storeid;
  }

  public Store phone(Phone phone) {
    this.phone = phone;
    return this;
  }

  public Phone getPhone() {
    return phone;
  }

  public void setPhone(Phone phone) {
    this.phone = phone;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Store store = (Store) o;
    return Objects.equals(this.name, store.name) &&
        Objects.equals(this.address, store.address) &&
        Objects.equals(this.storeid, store.storeid) &&
        Objects.equals(this.phone, store.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, storeid, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Store {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    storeid: ").append(toIndentedString(storeid)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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


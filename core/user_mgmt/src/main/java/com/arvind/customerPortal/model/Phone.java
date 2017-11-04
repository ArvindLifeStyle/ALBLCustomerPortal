package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class Phone   {
  @JsonProperty("number")
  @NotNull
  private String number = null;

  @JsonProperty("cc")
  @NotNull
  private String cc = null;

  public Phone number(String number) {
    this.number = number;
    return this;
  }

  


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Phone cc(String cc) {
    this.cc = cc;
    return this;
  }


  public String getCc() {
    return cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Phone phone = (Phone) o;
    return Objects.equals(this.number, phone.number) &&
        Objects.equals(this.cc, phone.cc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, cc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Phone {\n");
    
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    cc: ").append(toIndentedString(cc)).append("\n");
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


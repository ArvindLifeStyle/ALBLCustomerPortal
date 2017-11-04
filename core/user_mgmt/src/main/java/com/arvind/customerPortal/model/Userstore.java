package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class Userstore   {
  @JsonProperty("userid")
  private int userid;

  @JsonProperty("storeid")
  private String storeid = null;

  public Userstore userid(int userid) {
    this.userid = userid;
    return this;
  }

   /**
   * registered customer id
   * @return userid
  **/
  @NotNull


  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public Userstore storeid(String storeid) {
    this.storeid = storeid;
    return this;
  }

   /**
   * unique store or customer id of user defined in ALBL system
   * @return storeid
  **/
  @NotNull


  public String getStoreid() {
    return storeid;
  }

  public void setStoreid(String storeid) {
    this.storeid = storeid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Userstore userstore = (Userstore) o;
    return Objects.equals(this.userid, userstore.userid) &&
        Objects.equals(this.storeid, userstore.storeid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userid, storeid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Userstore {\n");
    
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    storeid: ").append(toIndentedString(storeid)).append("\n");
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


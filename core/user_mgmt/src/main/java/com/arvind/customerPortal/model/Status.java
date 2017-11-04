package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Status   {
  @JsonProperty("ok")
  private Boolean ok = null;

  @JsonProperty("http$")
  private Integer http = null;

  @JsonProperty("why")
  private String why = null;

  public Status ok(Boolean ok) {
    this.ok = ok;
    return this;
  }

   /**
   * true or false indicate request outcome
   * @return ok
  **/


  public Boolean getOk() {
    return ok;
  }

  public void setOk(Boolean ok) {
    this.ok = ok;
  }

  public Status http(Integer http) {
    this.http = http;
    return this;
  }

   /**
   * http status code
   * @return http
  **/


  public Integer getHttp() {
    return http;
  }

  public void setHttp(Integer http) {
    this.http = http;
  }

  public Status why(String why) {
    this.why = why;
    return this;
  }

   /**
   * status message indicating success or failure reason
   * @return why
  **/


  public String getWhy() {
    return why;
  }

  public void setWhy(String why) {
    this.why = why;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Status status = (Status) o;
    return Objects.equals(this.ok, status.ok) &&
        Objects.equals(this.http, status.http) &&
        Objects.equals(this.why, status.why);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ok, http, why);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Status {\n");
    
    sb.append("    ok: ").append(toIndentedString(ok)).append("\n");
    sb.append("    http: ").append(toIndentedString(http)).append("\n");
    sb.append("    why: ").append(toIndentedString(why)).append("\n");
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


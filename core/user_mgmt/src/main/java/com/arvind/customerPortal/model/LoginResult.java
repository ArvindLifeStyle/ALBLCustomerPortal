package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoginResult
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-30T11:47:53.319Z")

public class LoginResult   {
  @JsonProperty("statusMessage")
  private String statusMessage = null;

  @JsonProperty("statusCode")
  private String statusCode = null;

  @JsonProperty("token")
  private String token = null;

  @JsonProperty("errorMessage")
  private String errorMessage = null;

  @JsonProperty("customerName")
  private String customerName = null;

  @JsonProperty("role")
  private String role = null;

  public LoginResult statusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
    return this;
  }

   /**
   * Success
   * @return statusMessage
  **/
  @ApiModelProperty(example = "Success", value = "Success")


  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public LoginResult statusCode(String statusCode) {
    this.statusCode = statusCode;
    return this;
  }

   /**
   * Status code.
   * @return statusCode
  **/
  @ApiModelProperty(value = "Status code.")


  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public LoginResult token(String token) {
    this.token = token;
    return this;
  }

   /**
   * Security token for the user.
   * @return token
  **/
  @ApiModelProperty(value = "Security token for the user.")


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LoginResult errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * Error message if any.
   * @return errorMessage
  **/
  @ApiModelProperty(value = "Error message if any.")


  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public LoginResult customerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

   /**
   * customer name for the user
   * @return customerName
  **/
  @ApiModelProperty(value = "customer name for the user")


  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public LoginResult role(String role) {
    this.role = role;
    return this;
  }

   /**
   * role of the customer
   * @return role
  **/
  @ApiModelProperty(value = "role of the customer")


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginResult loginResult = (LoginResult) o;
    return Objects.equals(this.statusMessage, loginResult.statusMessage) &&
        Objects.equals(this.statusCode, loginResult.statusCode) &&
        Objects.equals(this.token, loginResult.token) &&
        Objects.equals(this.errorMessage, loginResult.errorMessage) &&
        Objects.equals(this.customerName, loginResult.customerName) &&
        Objects.equals(this.role, loginResult.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusMessage, statusCode, token, errorMessage, customerName, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginResult {\n");
    
    sb.append("    statusMessage: ").append(toIndentedString(statusMessage)).append("\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    customerName: ").append(toIndentedString(customerName)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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


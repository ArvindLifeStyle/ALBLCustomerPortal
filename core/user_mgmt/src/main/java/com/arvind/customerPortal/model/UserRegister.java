package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserRegister
 */
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-30T11:47:53.319Z")

public class UserRegister   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("role")
  private String role = null;

  public UserRegister name(String name) {
    this.name = name;
    return this;
  }

   /**
   * human name of user
   * @return name
  **/
  @ApiModelProperty(required = true, value = "human name of user")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserRegister email(String email) {
    this.email = email;
    return this;
  }

   /**
   * email address
   * @return email
  **/
  @ApiModelProperty(required = true, value = "email address")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRegister password(String password) {
    this.password = password;
    return this;
  }

   /**
   * password
   * @return password
  **/
  @ApiModelProperty(required = true, value = "password")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRegister role(String role) {
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


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
    UserRegister userRegister = (UserRegister) o;
    return Objects.equals(this.name, userRegister.name) &&
        Objects.equals(this.email, userRegister.email) &&
        Objects.equals(this.password, userRegister.password) &&
        Objects.equals(this.role, userRegister.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegister {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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


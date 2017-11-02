package com.arvind.customerPortal.model;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.arvind.customerPortal.model.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.core.io.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-30T11:47:53.319Z")

public class User   {
  @JsonProperty("nick")
  private String nick = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("verified")
  private Boolean verified = null;

  @JsonProperty("role")
  private Role role = null;

  @JsonProperty("resource")
  private Resource resource = null;

  @JsonProperty("created")
  private Date created = null;

  public User nick(String nick) {
    this.nick = nick;
    return this;
  }

   /**
   * name of the user., If not provided, will be set to email value
   * @return nick
  **/
  @ApiModelProperty(example = "ABC", required = true, value = "name of the user., If not provided, will be set to email value")
  @NotNull


  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

   /**
   * name of the user
   * @return name
  **/
  @ApiModelProperty(example = "ABC", required = true, value = "name of the user")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

   /**
   * password of the user.
   * @return password
  **/
  @ApiModelProperty(required = true, value = "password of the user.")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

   /**
   * email id of the user.
   * @return email
  **/
  @ApiModelProperty(required = true, value = "email id of the user.")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User active(Boolean active) {
    this.active = active;
    return this;
  }

   /**
   * active can be true or false, Default user is active false.
   * @return active
  **/
  @ApiModelProperty(required = true, value = "active can be true or false, Default user is active false.")
  @NotNull


  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public User verified(Boolean verified) {
    this.verified = verified;
    return this;
  }

   /**
   * verified can be true or false, Default user is active false.
   * @return verified
  **/
  @ApiModelProperty(required = true, value = "verified can be true or false, Default user is active false.")
  @NotNull


  public Boolean getVerified() {
    return verified;
  }

  public void setVerified(Boolean verified) {
    this.verified = verified;
  }

  public User role(Role role) {
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public User resource(Resource resource) {
    this.resource = resource;
    return this;
  }

   /**
   * Get resource
   * @return resource
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public User created(Date created) {
    this.created = created;
    return this;
  }

   /**
   * use to mention created time
   * @return created
  **/
  @ApiModelProperty(value = "use to mention created time")

  @Valid

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.nick, user.nick) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.active, user.active) &&
        Objects.equals(this.verified, user.verified) &&
        Objects.equals(this.role, user.role) &&
        Objects.equals(this.resource, user.resource) &&
        Objects.equals(this.created, user.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nick, name, password, email, active, verified, role, resource, created);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    nick: ").append(toIndentedString(nick)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    verified: ").append(toIndentedString(verified)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
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


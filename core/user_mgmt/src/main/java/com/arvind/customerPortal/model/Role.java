package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Role
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-30T11:47:53.319Z")

public class Role   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  public Role name(String name) {
    this.name = name;
    return this;
  }

   /**
   * role name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "role name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role code(String code) {
    this.code = code;
    return this;
  }

   /**
   * role code
   * @return code
  **/
  @ApiModelProperty(required = true, value = "role code")
  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Role description(String description) {
    this.description = description;
    return this;
  }

   /**
   * purpose of creating this role
   * @return description
  **/
  @ApiModelProperty(value = "purpose of creating this role")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(this.name, role.name) &&
        Objects.equals(this.code, role.code) &&
        Objects.equals(this.description, role.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, code, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Role {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


package com.abc.portal.models;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource
 */

public class Resource {
	@JsonProperty("name")
	private String name = null;

	@JsonProperty("code")
	private String code = null;

	public Resource name(String name) {
		this.name = name;
		return this;
	}

	@NotNull

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Resource code(String code) {
		this.code = code;
		return this;
	}

	@NotNull
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Resource resource = (Resource) o;
		return Objects.equals(this.name, resource.name) && Objects.equals(this.code, resource.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, code);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Resource {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
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

package com.arvind.customerPortal.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class ResponseMultiple {
	@JsonProperty("status")
	private Status status = null;

	@JsonProperty("stores")
	private List<Store> stores = null;

	public ResponseMultiple status(Status status) {
		this.status = status;
		return this;
	}

	@Valid

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ResponseMultiple stores(List<Store> stores) {
		this.stores = stores;
		return this;
	}

	public ResponseMultiple addStoresItem(Store storesItem) {
		if (this.stores == null) {
			this.stores = new ArrayList<Store>();
		}
		this.stores.add(storesItem);
		return this;
	}

	@Valid
	@Size(max = 100)
	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseMultiple responseMultiple = (ResponseMultiple) o;
		return Objects.equals(this.status, responseMultiple.status)
				&& Objects.equals(this.stores, responseMultiple.stores);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, stores);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseMultiple {\n");

		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    stores: ").append(toIndentedString(stores)).append("\n");
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

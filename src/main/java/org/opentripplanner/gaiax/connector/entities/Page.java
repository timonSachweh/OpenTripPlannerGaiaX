package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
	@JsonSetter(nulls = Nulls.SKIP)
	private int size;
	@JsonSetter(nulls = Nulls.SKIP)
	private int totalElements;
	@JsonSetter(nulls = Nulls.SKIP)
	private int totalPages;
	@JsonSetter(nulls = Nulls.SKIP)
	private int number;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}

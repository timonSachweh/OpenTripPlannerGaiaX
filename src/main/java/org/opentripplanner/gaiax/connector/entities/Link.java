package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link implements Serializable {
	private String href;
	@JsonSetter(nulls = Nulls.SKIP)
	private boolean templated;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isTemplated() {
		return templated;
	}

	public void setTemplated(boolean templated) {
		this.templated = templated;
	}
}

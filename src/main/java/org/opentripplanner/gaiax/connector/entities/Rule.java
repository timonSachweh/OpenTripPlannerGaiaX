package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule implements Serializable {
	@JsonSetter(nulls = Nulls.SKIP)
	private String title;
	@JsonSetter(nulls = Nulls.SKIP)
	private String description;
	@JsonSetter(nulls = Nulls.SKIP)
	private String value;
	@JsonSetter(nulls = Nulls.SKIP)
	@JsonProperty("_links")
	private Links links;

	public Rule() {

	}

	public Rule(String title, String description, String value) {
		this.title = title;
		this.description = description;
		this.value = value;
	}

	public static Rule defaultEntity() {
		String title = "Gaiax4roms Usage Policy";
		String description = "Usage policy provide access applied";
		String value = "{\n  \"@context\" : {\n    \"ids\" : \"https://w3id.org/idsa/core/\",\n    \"idsc\" : \"https://w3id.org/idsa/code/\"\n  },\n  \"@type\" : \"ids:Permission\",\n  \"@id\" : \"https://w3id.org/idsa/autogen/permission/51f5f7e4-f97f-4f91-bc57-b243714642be\",\n  \"ids:description\" : [ {\n    \"@value\" : \"Usage policy provide access applied\",\n    \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"\n  } ],\n  \"ids:title\" : [ {\n    \"@value\" : \"Example Usage Policy\",\n    \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"\n  } ],\n    \"ids:action\" : [ {\n    \"@id\" : \"https://w3id.org/idsa/code/USE\"\n  } ]\n }";
		return new Rule(title, description, value);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}
}

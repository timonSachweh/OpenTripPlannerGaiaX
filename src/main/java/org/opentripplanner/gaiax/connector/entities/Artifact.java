package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artifact implements Serializable {
	@JsonSetter(nulls = Nulls.SKIP)
	private String title;
	@JsonSetter(nulls = Nulls.SKIP)
	private String description;
	@JsonSetter(nulls = Nulls.SKIP)
	private String accessUrl;
	@JsonSetter(nulls = Nulls.SKIP)
	private BasicAuth basicAuth;
	@JsonSetter(nulls = Nulls.SKIP)
	private String value;
	@JsonSetter(nulls = Nulls.SKIP)
	private boolean automatedDownload;

	@JsonSetter(nulls = Nulls.SKIP)
	@JsonProperty("_links")
	private Links links;

	public Artifact() {
	}

	public Artifact(String title, String description, String accessUrl, BasicAuth auth, String value,
	                boolean automatedDownload) {
		this.title = title;
		this.description = description;
		this.accessUrl = accessUrl;
		this.basicAuth = auth;
		this.value = value;
		this.automatedDownload = automatedDownload;
	}

	public static Artifact defaultEntity() {
		String title = "ETA Service";
		String description = "Access to the eta service backend";
		String accessUrl = "https://eta.roms.tsachweh.de/otp/gaiax/routing";
		BasicAuth basicAuth = new BasicAuth("gaiax4roms", "ASDkn358ASskdj!b34");
		String value = "application/json";
		boolean automatedDownload = true;
		return new Artifact(title, description, accessUrl, basicAuth, value, automatedDownload);
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

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public BasicAuth getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(BasicAuth basicAuth) {
		this.basicAuth = basicAuth;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isAutomatedDownload() {
		return automatedDownload;
	}

	public void setAutomatedDownload(boolean automatedDownload) {
		this.automatedDownload = automatedDownload;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}
}

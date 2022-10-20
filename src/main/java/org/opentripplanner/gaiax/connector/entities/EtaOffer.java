package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EtaOffer implements Serializable {

	private String title;
	private String description;
	private List<String> keywords;
	private String publisher;
	private String language;
	private String license;
	private String sovereign;
	private String endpointDocumentation;
	private String paymentModality;

	@JsonSetter(nulls = Nulls.SKIP)
	@JsonProperty("_links")
	private Links links;

	public EtaOffer() {

	}

	public EtaOffer(
			String title,
			String description,
			List<String> keywords,
			String publisher,
			String language,
			String license,
			String sovereign,
			String endpointDocumentation,
			String paymentModality
	) {
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.publisher = publisher;
		this.language = language;
		this.license = license;
		this.sovereign = sovereign;
		this.endpointDocumentation = endpointDocumentation;
		this.paymentModality = paymentModality;
	}

	public static EtaOffer defaultOffer() {
		String title = "TU DO ETA service";
		String description = "Routing and eta calculation service";
		List<String> keywords = new ArrayList<>() {
			{
				add("ETA");
				add("Routing");
				add("GAIAX4ROMS");
			}
		};
		String publisher = "https://eta.roms.tsachweh.de";
		String language = "DE";
		String license = "";
		String sovereign = "https://eta.roms.tsachweh.de";
		String endpointDocumentation = "";
		String paymentModality = "undefined";
		return new EtaOffer(
				title,
				description,
				keywords,
				publisher,
				language,
				license,
				sovereign,
				endpointDocumentation,
				paymentModality
		);
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

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSovereign() {
		return sovereign;
	}

	public void setSovereign(String sovereign) {
		this.sovereign = sovereign;
	}

	public String getEndpointDocumentation() {
		return endpointDocumentation;
	}

	public void setEndpointDocumentation(String endpointDocumentation) {
		this.endpointDocumentation = endpointDocumentation;
	}

	public String getPaymentModality() {
		return paymentModality;
	}

	public void setPaymentModality(String paymentModality) {
		this.paymentModality = paymentModality;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}
}

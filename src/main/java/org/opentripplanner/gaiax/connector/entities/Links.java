package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links implements Serializable {
	@JsonSetter(nulls = Nulls.SKIP)
	private Link self;
	@JsonSetter(nulls = Nulls.SKIP)
	private Link contracts;
	@JsonSetter(nulls = Nulls.SKIP)
	private Link representations;
	@JsonSetter(nulls = Nulls.SKIP)
	private Link catalogs;
	@JsonSetter(nulls = Nulls.SKIP)
	private Link subscriptions;
	@JsonSetter(nulls = Nulls.SKIP)
	private Link brokers;

	@JsonSetter(nulls = Nulls.SKIP)
	private Link offers;

	public Link getSelf() {
		return self;
	}

	public void setSelf(Link self) {
		this.self = self;
	}

	public Link getContracts() {
		return contracts;
	}

	public void setContracts(Link contracts) {
		this.contracts = contracts;
	}

	public Link getRepresentations() {
		return representations;
	}

	public void setRepresentations(Link representations) {
		this.representations = representations;
	}

	public Link getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(Link catalogs) {
		this.catalogs = catalogs;
	}

	public Link getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Link subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Link getBrokers() {
		return brokers;
	}

	public void setBrokers(Link brokers) {
		this.brokers = brokers;
	}

	public Link getOffers() {
		return offers;
	}

	public void setOffers(Link offers) {
		this.offers = offers;
	}
}

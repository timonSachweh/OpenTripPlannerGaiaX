package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;
import org.opentripplanner.standalone.config.GaiaxConfig;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog implements Serializable {

  private String title;
  private String description;

  @JsonSetter(nulls = Nulls.SKIP)
  @JsonProperty("_links")
  private Links links;

  public Catalog() {}

  public Catalog(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public static Catalog defaultEntity(GaiaxConfig config) {
    return new Catalog(config.getConnectorCatalogTitle(), config.getConnectorCatalogDescription());
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

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }
}

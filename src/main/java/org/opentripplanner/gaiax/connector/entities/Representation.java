package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;
import org.opentripplanner.standalone.config.GaiaxConfig;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Representation implements Serializable {

  @JsonSetter(nulls = Nulls.SKIP)
  private String title;

  @JsonSetter(nulls = Nulls.SKIP)
  private String description;

  @JsonSetter(nulls = Nulls.SKIP)
  private String mediaType;

  @JsonSetter(nulls = Nulls.SKIP)
  private String language;

  @JsonSetter(nulls = Nulls.SKIP)
  @JsonProperty("_links")
  private Links links;

  public Representation() {}

  public Representation(String title, String description, String mediaType, String language) {
    this.title = title;
    this.description = description;
    this.mediaType = mediaType;
    this.language = language;
  }

  public static Representation defaultEntity(GaiaxConfig config) {
    return new Representation(
      config.getConnectorRepresentationTitle(),
      config.getConnectorRepresentationDescription(),
      config.getConnectorRepresentationMediaType(),
      config.getConnectorRepresentationLanguage()
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

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }
}

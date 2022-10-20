package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.opentripplanner.standalone.config.GaiaxConfig;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contract implements Serializable {

  @JsonSetter(nulls = Nulls.SKIP)
  private String title;

  @JsonSetter(nulls = Nulls.SKIP)
  private String description;

  @JsonSetter(nulls = Nulls.SKIP)
  private String provider;

  @JsonSetter(nulls = Nulls.SKIP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private ZonedDateTime start;

  @JsonSetter(nulls = Nulls.SKIP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private ZonedDateTime end;

  @JsonSetter(nulls = Nulls.SKIP)
  @JsonProperty("_links")
  private Links links;

  public Contract() {}

  public Contract(
    String title,
    String description,
    String provider,
    ZonedDateTime start,
    ZonedDateTime end
  ) {
    this.title = title;
    this.description = description;
    this.provider = provider;
    this.start = start;
    this.end = end;
  }

  public static Contract defaultEntity(GaiaxConfig config) {
    ZonedDateTime start = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
    ZonedDateTime end = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
    return new Contract(
      config.getConnectorContractTitle(),
      config.getConnectorContractDescription(),
      config.getServiceBaseUrl(),
      start,
      end
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

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public ZonedDateTime getStart() {
    return start;
  }

  public void setStart(ZonedDateTime start) {
    this.start = start;
  }

  public ZonedDateTime getEnd() {
    return end;
  }

  public void setEnd(ZonedDateTime end) {
    this.end = end;
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }
}

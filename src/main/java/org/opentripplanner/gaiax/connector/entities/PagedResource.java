package org.opentripplanner.gaiax.connector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagedResource implements Serializable {

  private Object elements;

  @JsonProperty("_links")
  private Links links;

  private Page page;

  public <T> List<T> getElements(TypeReference<List<T>> reference) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper.convertValue(this.elements, reference);
  }

  @JsonProperty("_embedded")
  private void unpackEmbedded(Map<String, Object> embeddedJson) {
    if (embeddedJson.isEmpty()) {
      this.elements = new ArrayList<>();
    } else {
      String key = (String) embeddedJson.keySet().toArray()[0];
      this.elements = embeddedJson.get(key);
    }
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }
}

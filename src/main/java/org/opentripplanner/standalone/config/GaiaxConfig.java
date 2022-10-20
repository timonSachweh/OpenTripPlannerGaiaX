package org.opentripplanner.standalone.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an object representation of the 'router-config.json'.
 */
public class GaiaxConfig implements Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(GaiaxConfig.class);

  public static final GaiaxConfig DEFAULT = new GaiaxConfig(
    MissingNode.getInstance(),
    "DEFAULT",
    false
  );

  /**
   * The raw JsonNode three kept for reference and (de)serialization.
   */
  private final JsonNode rawJson;
  private final String connectorBaseUrl;
  private final String connectorOfferTitle;
  private final String connectorOfferDescription;
  private final String connectorCatalogTitle;
  private final String connectorCatalogDescription;
  private final String connectorRuleTitle;
  private final String connectorRuleDescription;
  private final String connectorContractTitle;
  private final String connectorContractDescription;
  private final String connectorArtifactTitle;
  private final String connectorArtifactDescription;
  private final String connectorArtifactAuthUsername;
  private final String connectorArtifactAuthPassword;
  private final String connectorArtifactValue;
  private final String connectorRepresentationTitle;
  private final String connectorRepresentationDescription;
  private final String connectorRepresentationMediaType;
  private final String connectorRepresentationLanguage;
  private final String serviceBaseUrl;
  private final String servicePath;

  public GaiaxConfig(JsonNode node, String source, boolean logUnusedParams) {
    NodeAdapter adapter = new NodeAdapter(node, source);
    this.rawJson = node;
    this.connectorBaseUrl = adapter.asText("connectorBaseUrl", null);
    this.connectorOfferTitle = adapter.asText("connectorOfferTitle", "TU DO ETA service");
    this.connectorOfferDescription =
      adapter.asText("connectorOfferDescription", "Routing and eta calculation service");
    this.connectorCatalogTitle = adapter.asText("connectorCatalogTitle", "GAIAX4ROMS");
    this.connectorCatalogDescription =
      adapter.asText(
        "connectorCatalogDescription",
        "This catalog contains all data for the gaiax4roms project"
      );
    this.connectorRuleTitle = adapter.asText("connectorRuleTitle", "Gaiax4roms Usage Policy");
    this.connectorRuleDescription =
      adapter.asText("connectorRuleDescription", "Usage policy provide access applied");
    this.connectorContractTitle = adapter.asText("connectorContractTitle", "Gaiax4roms Contract");
    this.connectorContractDescription =
      adapter.asText("connectorContractDescription", "Contract for eta calculation in gaiax4roms.");
    this.connectorArtifactTitle = adapter.asText("connectorArtifactTitle", "ETA Service");
    this.connectorArtifactDescription =
      adapter.asText("connectorArtifactDescription", "Access to the eta service backend");
    this.connectorArtifactAuthUsername =
      adapter.asText("connectorArtifactAuthUsername", "gaiax4roms");
    this.connectorArtifactAuthPassword =
      adapter.asText("connectorArtifactAuthPassword", "ASDkn358ASskdj!b34");
    this.connectorArtifactValue = adapter.asText("connectorArtifactValue", "application/json");
    this.connectorRepresentationTitle =
      adapter.asText("connectorRepresentationTitle", "Json Representation");
    this.connectorRepresentationDescription =
      adapter.asText("connectorRepresentationDescription", "");
    this.connectorRepresentationMediaType =
      adapter.asText("connectorRepresentationMediaType", "application/json");
    this.connectorRepresentationLanguage =
      adapter.asText("connectorRepresentationLanguage", "https://w3id.org/idsa/code/EN");
    this.serviceBaseUrl = adapter.asText("serviceBaseUrl", null);
    this.servicePath = adapter.asText("servicePath", "/otp/gaiax/routing");

    if (logUnusedParams) {
      adapter.logAllUnusedParameters(LOG);
    }
  }

  public String getConnectorBaseUrl() {
    return connectorBaseUrl;
  }

  public String getConnectorOfferTitle() {
    return connectorOfferTitle;
  }

  public String getConnectorOfferDescription() {
    return connectorOfferDescription;
  }

  public String getConnectorCatalogTitle() {
    return connectorCatalogTitle;
  }

  public String getConnectorCatalogDescription() {
    return connectorCatalogDescription;
  }

  public String getConnectorRuleTitle() {
    return connectorRuleTitle;
  }

  public String getConnectorRuleDescription() {
    return connectorRuleDescription;
  }

  public String getConnectorContractTitle() {
    return connectorContractTitle;
  }

  public String getConnectorContractDescription() {
    return connectorContractDescription;
  }

  public String getConnectorArtifactTitle() {
    return connectorArtifactTitle;
  }

  public String getConnectorArtifactDescription() {
    return connectorArtifactDescription;
  }

  public String getConnectorArtifactAuthUsername() {
    return connectorArtifactAuthUsername;
  }

  public String getConnectorArtifactAuthPassword() {
    return connectorArtifactAuthPassword;
  }

  public String getConnectorArtifactValue() {
    return connectorArtifactValue;
  }

  public String getConnectorRepresentationTitle() {
    return connectorRepresentationTitle;
  }

  public String getConnectorRepresentationDescription() {
    return connectorRepresentationDescription;
  }

  public String getConnectorRepresentationMediaType() {
    return connectorRepresentationMediaType;
  }

  public String getConnectorRepresentationLanguage() {
    return connectorRepresentationLanguage;
  }

  public String getServiceBaseUrl() {
    return serviceBaseUrl;
  }

  public String getServicePath() {
    return servicePath;
  }

  /**
   * If {@code true} the config is loaded from file, in not the DEFAULT config is used.
   */
  public boolean isDefault() {
    return this.rawJson.isMissingNode();
  }

  public String toJson() {
    return rawJson.isMissingNode() ? "" : rawJson.toString();
  }

  public String toString() {
    // Print ONLY the values set, not deafult values
    return rawJson.toPrettyString();
  }
}

package org.opentripplanner.gaiax.connector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.opentripplanner.gaiax.connector.entities.Artifact;
import org.opentripplanner.gaiax.connector.entities.Catalog;
import org.opentripplanner.gaiax.connector.entities.Contract;
import org.opentripplanner.gaiax.connector.entities.EtaOffer;
import org.opentripplanner.gaiax.connector.entities.PagedResource;
import org.opentripplanner.gaiax.connector.entities.Representation;
import org.opentripplanner.gaiax.connector.entities.Rule;

public class GaiaxConnectorStore {


  private static final String CONNECTOR_BASE_URL = "https://connector.roms.tsachweh.de:8083";
  private com.fasterxml.jackson.databind.ObjectMapper mapper;
  private HttpClient client;

  public GaiaxConnectorStore() {
    mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    try {
      this.client =
        HttpClients
          .custom()
          .setDefaultHeaders(this.defaultHeaders())
          .setSSLHostnameVerifier(new NoopHostnameVerifier())
          .setSslcontext(this.sslContext())
          .setDefaultCredentialsProvider(this.connectorCredentials())
          .build();
    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
      throw new RuntimeException(e);
    }
  }

  private Collection<? extends Header> defaultHeaders() {
    List<Header> list = new ArrayList<>();
    list.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
    return list;
  }

  public void registerAsDataOffering() {
    try {
      EtaOffer offer = this.postOffer();
      Catalog catalog = this.postCatalog();
      this.addOfferToCatalog(catalog, offer);
      Rule rule = this.postRule();
      Contract contract = this.postContract();
      this.addRuleToContract(contract, rule);
      Artifact artifact = this.postArtifact();
      Representation representation = this.postRepresentation();
      this.addArtifactToRepresentation(representation, artifact);
      this.addRepresentationToOffer(offer, representation);
      this.addContractToOffer(offer, contract);
      System.out.println("GAIAX: Successfully served data offering through connector: " + CONNECTOR_BASE_URL);
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Contract addContractToOffer(EtaOffer offer, Contract contract) throws IOException {
    if (offer == null || offer.getLinks() == null || offer.getLinks().getSelf() == null)
      return null;
    if (contract == null || contract.getLinks() == null || contract.getLinks().getSelf() == null)
      return null;
    String offerRef = offer.getLinks().getSelf().getHref();
    String contractRef = contract.getLinks().getSelf().getHref();

    HttpPost request = new HttpPost(offerRef + "/contracts");
    request.setEntity(getBody(List.of(contractRef)));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: contract linked to offer");
      PagedResource responseContract = this.getResponseBody(response, new TypeReference<PagedResource>() {});
      return responseContract.getElements(new TypeReference<List<Contract>>() {}).get(0);
    }
    return null;
  }

  private Representation addRepresentationToOffer(EtaOffer offer, Representation representation) throws IOException {
    if (offer == null || offer.getLinks() == null || offer.getLinks().getSelf() == null)
      return null;
    if (representation == null || representation.getLinks() == null || representation.getLinks().getSelf() == null)
      return null;
    String offerRef = offer.getLinks().getSelf().getHref();
    String representationRef = representation.getLinks().getSelf().getHref();

    HttpPost request = new HttpPost(offerRef + "/representations");
    request.setEntity(getBody(List.of(representationRef)));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: representation linked to offer");
      PagedResource responseRepresentation = this.getResponseBody(response, new TypeReference<PagedResource>() {});
      return responseRepresentation.getElements(new TypeReference<List<Representation>>() {}).get(0);
    }
    return null;
  }

  private Artifact addArtifactToRepresentation(Representation representation, Artifact artifact) throws IOException {
    if (representation == null || representation.getLinks() == null || representation.getLinks().getSelf() == null)
      return null;
    if (artifact == null || artifact.getLinks() == null || artifact.getLinks().getSelf() == null)
      return null;
    String representationRef = representation.getLinks().getSelf().getHref();
    String artifactRef = artifact.getLinks().getSelf().getHref();

    HttpPost request = new HttpPost(representationRef + "/artifacts");
    request.setEntity(getBody(List.of(artifactRef)));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Artifact linked to representation");
      PagedResource responseArtifact = this.getResponseBody(response, new TypeReference<PagedResource>() {});
      return responseArtifact.getElements(new TypeReference<List<Artifact>>() {}).get(0);
    }
    return null;
  }

  private Representation postRepresentation() throws IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/representations");
    request.setEntity(getBody(Representation.defaultEntity()));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Artifact created");
      return this.getResponseBody(response, new TypeReference<Representation>() {});
    }
    return null;
  }

  private Artifact postArtifact() throws IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/artifacts");
    request.setEntity(getBody(Artifact.defaultEntity()));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Representation created");
      return this.getResponseBody(response, new TypeReference<Artifact>() {});
    }
    return null;
  }

  private Rule addRuleToContract(Contract contract, Rule rule) throws IOException {
    if (contract == null || contract.getLinks() == null || contract.getLinks().getSelf() == null)
      return null;
    if (rule == null || rule.getLinks() == null || rule.getLinks().getSelf() == null)
      return null;
    String contractRef = contract.getLinks().getSelf().getHref();
    String ruleRef = rule.getLinks().getSelf().getHref();

    HttpPost request = new HttpPost(contractRef + "/rules");
    request.setEntity(getBody(List.of(ruleRef)));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Offer linked to catalog");
      PagedResource responseRule = this.getResponseBody(response, new TypeReference<PagedResource>() {});
      return responseRule.getElements(new TypeReference<List<Rule>>() {}).get(0);
    }
    return null;
  }

  private Contract postContract() throws IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/contracts");
    request.setEntity(getBody(Contract.defaultEntity()));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Contract created");
      return this.getResponseBody(response, new TypeReference<Contract>() {});
    }
    return null;
  }

  private Rule postRule() throws IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/rules");
    request.setEntity(getBody(Rule.defaultEntity()));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() == 201) {
      System.out.println("GAIAX: Rule created");
      return getResponseBody(response, new TypeReference<Rule>() {});
    }
    return null;
  }

  private EtaOffer addOfferToCatalog(Catalog catalog, EtaOffer offer) throws IOException {
    if (catalog == null || catalog.getLinks() == null || catalog.getLinks().getSelf() == null)
      return null;
    if (offer == null || offer.getLinks() == null || offer.getLinks().getSelf() == null)
      return null;
    String catalogRef = catalog.getLinks().getSelf().getHref();
    String offerRef = offer.getLinks().getSelf().getHref();

    HttpPost request = new HttpPost(catalogRef + "/offers");
    request.setEntity(getBody(List.of(offerRef)));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() < 300) {
      System.out.println("GAIAX: Offer linked to catalog");
      PagedResource responseOffer = this.getResponseBody(response,
        new TypeReference<PagedResource>() {});
      return responseOffer.getElements(new TypeReference<List<EtaOffer>>() {}).get(0);
    }
    return null;
  }

  private EtaOffer postOffer() throws URISyntaxException, IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/offers");
    request.setEntity(getBody(EtaOffer.defaultOffer()));
    request.setHeader("Content-type", "application/json");
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() == 201) {
      System.out.println("GAIAX: Offer created");
      return this.getResponseBody(response, new TypeReference<EtaOffer>() {});
    }
    return null;
  }

  private Catalog postCatalog() throws IOException {
    HttpPost request = new HttpPost(CONNECTOR_BASE_URL + "/api/catalogs");
    request.setEntity(getBody(Catalog.defaultCatalog()));
    request.setHeader("Content-type", "application/json");
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() == 201) {
      System.out.println("GAIAX: Catalog created");
      return this.getResponseBody(response, new TypeReference<Catalog>() {});
    }
    return null;
  }

  private <T> T getResponseBody(HttpResponse response, TypeReference<T> entity) throws IOException {
    String json = EntityUtils.toString(response.getEntity());
    return mapper.readValue(json, entity);
  }


  private HttpEntity getBody(Object obj) throws IOException {
    return new StringEntity(mapper.writeValueAsString(obj));
  }

  private SSLContext sslContext()
    throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    return new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
  }

  private CredentialsProvider connectorCredentials() {
    CredentialsProvider provider = new BasicCredentialsProvider();
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "password");
    provider.setCredentials(AuthScope.ANY, credentials);
    return provider;
  }
}

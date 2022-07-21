package org.opentripplanner.gaiax.model.response;

public enum GaiaXFeatureType {
  FEATURE_COLLECTION("FeatureCollection"),
  FEATURE("Feature"),
  POINT("Point"),
  LINE_STRING("LineString"),
  POLYGON("Polygon");

  private final String text;

  /**
   * @param text
   */
  GaiaXFeatureType(final String text) {
    this.text = text;
  }

  /* (non-Javadoc)
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return text;
  }
}

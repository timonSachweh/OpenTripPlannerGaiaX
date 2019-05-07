package org.opentripplanner.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 * The purpose of this class is to be able to turn features on and off.
 * <p/>
 * This configuration is optional an found under "feature" in the top
 * level 'otp-config.json' file.
 */
public enum OTPFeature {
    APIExternalGeocoder(true),
    APIBikeRental(true),
    APIAlertPatcher(true),
    APIRouters(true),
    APIServerInfo(true),
    APIGraphInspectorTile(true),
    APIUpdaterStatus(true);

    private static final String VALUE_ON = "on";
    private static final String VALUE_OFF = "off";
    private static final String VALUE_ENABLE = "enable";
    private static final String VALUE_DISABLE = "disable";

    private static final Logger LOG = LoggerFactory.getLogger(OTPFeature.class);

    OTPFeature(boolean defaultEnabled) {
        this.enabled = defaultEnabled;
    }

    private boolean enabled;

    /**
     * Return {@code true} if feature is turned 'on'.
     */
    public boolean on() {
        return enabled;
    }

    /**
     * Return {@code true} if feature is turned 'off'.
     */
    public boolean off() {
        return !enabled;
    }

    /**
     * Allow unit test and this class to enable/disable a feature.
     */
    void set(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Return "on" if feature is enabled, if not return "off".
     */
    public String valueAsString() {
        return enabled ? VALUE_ON : VALUE_OFF;
    }

    /**
     * Configure features using given JSON.
     */
    public static void configure(@NotNull JsonNode otpConfig) {
        JsonNode features = otpConfig.path("features");
        for (OTPFeature feature : values()) {
            setFeatureFromConfig(feature, features.path(feature.name()));
        }
    }


    /* private members */

    private static void setFeatureFromConfig(OTPFeature feature, JsonNode node) {
        if (!node.isMissingNode()) {
            boolean featureValue = isOn(node.asText(feature.valueAsString()), feature.name());
            if (featureValue != feature.enabled) {
                feature.set(featureValue);
                logFeature(feature);
            } else {
                logFeatureDefault(feature);
            }
        } else {
            logFeatureDefault(feature);
        }
    }

    private static void logFeature(OTPFeature feature) {
        LOG.info("Feature '{}' is '{}'.", feature.name(), feature.valueAsString());
    }

    private static void logFeatureDefault(OTPFeature feature) {
        LOG.info("Feature '{}' is '{}' (default).", feature.name(), feature.valueAsString());
    }

    private static boolean isOn(String value, String key) {
        if (match(value, VALUE_ON) || match(value, VALUE_ENABLE)) {
            return true;
        } else if (match(value, VALUE_OFF) || match(value, VALUE_DISABLE)) {
            return false;
        }
        throw new IllegalArgumentException(
                "Feature values is not 'on'/'enable' or 'off'/'disable'. Unable to parse "
                        + "value for feature: " + key
        );
    }

    /**
     * Return true if the given value matches, ignoring case, the given expected value.
     */
    private static boolean match(String value, @NotNull String expected) {
        return expected.equalsIgnoreCase(value);
    }
}

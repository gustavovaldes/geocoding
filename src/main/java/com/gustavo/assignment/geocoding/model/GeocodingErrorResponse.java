package com.gustavo.assignment.geocoding.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gvaldes
 */

public class GeocodingErrorResponse {
    @JsonProperty
    private String error;
    @JsonProperty
    private String errorDescription;
    @JsonProperty
    private String reference;


    public GeocodingErrorResponse(){

    }
    public GeocodingErrorResponse(String error, String errorDescription, String reference) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.reference = reference;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

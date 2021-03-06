package com.gustavo.assinment.helper;


/**
 * Created by gvaldes
 */

public class GeocodingErrorResponse {

    private String error;
    private String errorDescription;
    private String reference;

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

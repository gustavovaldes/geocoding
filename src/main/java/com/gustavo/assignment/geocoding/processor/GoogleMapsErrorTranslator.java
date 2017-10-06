package com.gustavo.assignment.geocoding.processor;

import com.gustavo.assignment.geocoding.model.GeocodingErrorResponse;
import org.apache.camel.Exchange;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

/**
 * Created by gvaldes
 */
@Component("googleMapsErrorTranslator")
public class GoogleMapsErrorTranslator {

    public void handler(Exchange exchange) {
        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        String error;
        String description;
        if (exception instanceof HttpOperationFailedException ||
                exception instanceof ConnectException) {
            error = "External Service problem";
            description = exception.getMessage();
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 503);
        } else {
            error = "Internal Error";
            description = exception.getMessage();
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
        exchange.getOut().setBody(new GeocodingErrorResponse(error, description, exchange.getExchangeId()));
    }
}

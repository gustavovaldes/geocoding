package com.gustavo.assignment.geocoding.processor;

import com.gustavo.assignment.geocoding.model.GeocodingErrorResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

/**
 * Created by gvaldes
 */
@Component("geocodingErrorTranslator")
public class GeocodingErrorTranslator {
    @Handler
    public void handler(Exchange exchange){
        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        String error = "Internal Error";
        String description = exception.getMessage();
        String reference = exchange.getExchangeId();
        exchange.getOut().setBody(new GeocodingErrorResponse(error,description,reference));
    }
}

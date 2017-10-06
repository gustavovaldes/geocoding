package com.gustavo.assignment.geocoding.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.assignment.geocoding.model.GeocodingErrorResponse;
import com.gustavo.assignment.geocoding.model.MapAddress;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by gvaldes
 */

@Component("googleMapsResponseTranslator")
public class GoogleMapsResponseTranslator {

    @Handler
    public void processResponse(Exchange exchange) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        String originalJson = exchange.getIn().getBody(String.class);
        MapAddress mapAddress = jsonMapper.readValue(originalJson, MapAddress.class);
        if("OK".equalsIgnoreCase(mapAddress.getStatus())){
            exchange.getOut().setBody(mapAddress);
        } else {
            GeocodingErrorResponse error = new GeocodingErrorResponse("Not possible to get the information",
                    mapAddress.getStatus(), exchange.getExchangeId());
            exchange.getOut().setBody(error);
            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 503);
        }
    }
}

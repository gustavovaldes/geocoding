package com.gustavo.assignment.geocoding.routes;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Created by gvaldes
 */
@Component
public class CamelGeocodingRestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");


        restConfiguration()
                .endpointProperty("servletName", "camelHttpTransportServlet")
                .setBindingMode(RestBindingMode.auto);


        errorHandler(deadLetterChannel("direct:processError"));
        onException(Exception.class).to("direct:processError");

        rest().description("Geocoding REST service")
                .produces("application/json")
                .get()
                .to("direct:googleMapsApi");

        from("direct:processError")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"));


    }
}

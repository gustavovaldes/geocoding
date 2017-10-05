package com.gustavo.assignment.geocoding.service;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gvaldes
 */
@Component
public class GoogleMapApiClientImpl extends RouteBuilder {

    @Value("${external.map.google.api.url}")
    private String GOOGLE_MAP_URI_XMLOUTPUT;

    @Override
    public void configure() throws Exception {

        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");

        //onException(Exception.class).handled(true).to("direct:processError");

        from("direct:googleMapsApi")
                .to("log:input")
                .removeHeaders("CamelHttp*", Exchange.HTTP_QUERY)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .streamCaching()
                .setHeader(Exchange.REST_HTTP_URI, simple(GOOGLE_MAP_URI_XMLOUTPUT))
                .to("http4://executeRequest")
                .to("log:output")
                .marshal(xmlJsonFormat)
                .to("bean:googleMapsResponseTranslator");
        from("direct:processError2")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
                .setBody(constant("xsx"));
    }
}

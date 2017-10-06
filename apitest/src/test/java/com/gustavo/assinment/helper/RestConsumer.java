package com.gustavo.assinment.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by gvaldes
 */
public class RestConsumer {

    public HttpResponse get(String address) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8092/geocoding/?address=" + address);
        request.addHeader("accept", "application/json");
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse;
    }

    public HttpResponse validateStub(String address) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:1580/maps/api/geocode/xml?address="+address);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse;
    }

    public static String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

    public static GeocodingResponse convertResponseToGeodingModel(HttpResponse response) throws IOException {
        String  json = convertResponseToString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, GeocodingResponse.class);
    }

    public static GeocodingErrorResponse convertResponseToErrorModel(HttpResponse response) throws IOException {
        String  json = convertResponseToString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, GeocodingErrorResponse.class);
    }
}

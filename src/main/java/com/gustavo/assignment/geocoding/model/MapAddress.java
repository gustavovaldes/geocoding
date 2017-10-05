package com.gustavo.assignment.geocoding.model;

import com.fasterxml.jackson.annotation.*;



@JsonIgnoreProperties(ignoreUnknown = true)
public class MapAddress {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String status;
    @JsonProperty
    private String address;
    @JsonProperty
    private String latitude;
    @JsonProperty
    private String longitude;


    @JsonSetter("result")
    public void setResult(Result result) {
        this.address = result.formatted_address;
        this.latitude = result.geometry.location.lat;
        this.longitude = result.geometry.location.lng;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Result {
        @JsonProperty
        private String formatted_address;
        @JsonProperty
        private Geometry geometry;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Geometry {
            @JsonProperty
            Location location;

            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Location {
                @JsonProperty
                String lat;
                @JsonProperty
                String lng;
            }
        }
    }




}

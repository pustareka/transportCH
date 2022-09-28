package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationObjectJson {
    private List<LocationJson> stations;

    public List<LocationJson> getStations() {
        return stations;
    }

    public void setStations(List<LocationJson> stations) {
        this.stations = stations;
    }
}

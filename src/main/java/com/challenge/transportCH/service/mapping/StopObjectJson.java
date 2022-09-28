package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopObjectJson {

    private LocationJson station;
    private String arrival;
    private Long arrivalTimestamp;
    private String departure;
    private Long departureTimestamp;
    private Long delay;
    private String platform;
    private PrognosisJson prognosis;
    private LocationJson location;

    public LocationJson getStation() {
        return station;
    }

    public void setStation(LocationJson station) {
        this.station = station;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Long getArrivalTimestamp() {
        return arrivalTimestamp;
    }

    public void setArrivalTimestamp(Long arrivalTimestamp) {
        this.arrivalTimestamp = arrivalTimestamp;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Long getDepartureTimestamp() {
        return departureTimestamp;
    }

    public void setDepartureTimestamp(Long departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public PrognosisJson getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(PrognosisJson prognosis) {
        this.prognosis = prognosis;
    }

    public LocationJson getLocation() {
        return location;
    }

    public void setLocation(LocationJson location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopObjectJson)) return false;
        StopObjectJson that = (StopObjectJson) o;
        return Objects.equals(station, that.station) &&
                Objects.equals(arrival, that.arrival) &&
                Objects.equals(arrivalTimestamp, that.arrivalTimestamp) &&
                Objects.equals(departure, that.departure) &&
                Objects.equals(departureTimestamp, that.departureTimestamp) &&
                Objects.equals(delay, that.delay) &&
                Objects.equals(platform, that.platform) &&
                Objects.equals(prognosis, that.prognosis) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, arrival, arrivalTimestamp, departure, departureTimestamp, delay, platform, prognosis, location);
    }
}

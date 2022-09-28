package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionJson {

    private JourneyJson journey; //can be null
    private StopObjectJson departure;
    private StopObjectJson arrival;

    public JourneyJson getJourney() {
        return journey;
    }

    public void setJourney(JourneyJson journey) {
        this.journey = journey;
    }

    public StopObjectJson getDeparture() {
        return departure;
    }

    public void setDeparture(StopObjectJson departure) {
        this.departure = departure;
    }

    public StopObjectJson getArrival() {
        return arrival;
    }

    public void setArrival(StopObjectJson arrival) {
        this.arrival = arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionJson)) return false;
        SectionJson section = (SectionJson) o;
        return Objects.equals(journey, section.journey) &&
                departure.equals(section.departure) &&
                arrival.equals(section.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journey, departure, arrival);
    }
}

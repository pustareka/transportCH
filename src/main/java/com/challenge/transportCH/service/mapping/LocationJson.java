package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationJson {

    private String id;
    private String name;
    private String score;
    private Long distance;
    private CoordinateJson coordinate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public CoordinateJson getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateJson coordinate) {
        this.coordinate = coordinate;
    }
}

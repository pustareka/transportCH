package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionObjectJson {

    List<ConnectionJson> connections;

    public List<ConnectionJson> getConnections() {
        return connections;
    }

    public void setConnections(List<ConnectionJson> connections) {
        this.connections = connections;
    }
}

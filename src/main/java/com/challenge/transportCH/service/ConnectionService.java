package com.challenge.transportCH.service;

import com.challenge.transportCH.service.mapping.ConnectionObjectJson;
import org.springframework.stereotype.Component;

@Component
public interface ConnectionService {

    ConnectionObjectJson getConnections(String from, String to, String date, String time);
}

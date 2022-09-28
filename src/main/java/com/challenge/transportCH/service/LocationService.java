package com.challenge.transportCH.service;

import com.challenge.transportCH.service.mapping.LocationObjectJson;

import java.math.BigDecimal;

public interface LocationService {

    LocationObjectJson getLocations(String town, BigDecimal x, BigDecimal y, String type);
}

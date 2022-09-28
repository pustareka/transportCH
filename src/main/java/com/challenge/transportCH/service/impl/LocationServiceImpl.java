package com.challenge.transportCH.service.impl;

import com.challenge.transportCH.exception.GeneralServerError;
import com.challenge.transportCH.exception.LocationInvalidRequestException;
import com.challenge.transportCH.service.mapping.LocationObjectJson;
import com.challenge.transportCH.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger LOG = LoggerFactory.getLogger(LocationServiceImpl.class);

    private static final String CH_TRANSPORTATION_API_LOCATION = "http://transport.opendata.ch/v1/locations";

    @Override
    public LocationObjectJson getLocations(String town, BigDecimal x, BigDecimal y, String type) {
        String locationUrl = null;
        final ObjectMapper objectMapper = new ObjectMapper();

        if (town != null && !town.isEmpty()) {
            locationUrl = CH_TRANSPORTATION_API_LOCATION + "?query=" + urlEncode(town);
        } else if (x != null && y != null) {
            locationUrl = CH_TRANSPORTATION_API_LOCATION + "?x=" + x + "&y=" + y;
        } else {
            throw new LocationInvalidRequestException(LocationServiceImpl.class);
        }
        if (type != null && !type.isEmpty()) {
            locationUrl += "&type" + urlEncode(type);
        }

        try {
            return objectMapper.readValue(new URL(locationUrl), LocationObjectJson.class);
        } catch (IOException e) {
            LOG.error("Something went wrong while requesting location", e);
            throw new GeneralServerError(e.getMessage(), LocationServiceImpl.class);
        }
    }

    String urlEncode(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }
}

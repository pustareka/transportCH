package com.challenge.transportCH.controller;

import com.challenge.transportCH.exception.LocationObjectNotFoundException;
import com.challenge.transportCH.service.LocationService;
import com.challenge.transportCH.service.mapping.LocationObjectJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/publicTransportation")
public class LocationObjectController {

    private static final Logger LOG = LoggerFactory.getLogger(LocationObjectController.class);

    private final LocationService locationService;

    public LocationObjectController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<Object> getLocations(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) BigDecimal x,
            @RequestParam(required = false) BigDecimal y,
            @RequestParam(required = false) String type) {
        final LocationObjectJson location = locationService.getLocations(query, x, y, type);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            LOG.error("Something went wrong. Location {} not found ", query);
            throw new LocationObjectNotFoundException(query, LocationObjectJson.class);
        }
    }
}

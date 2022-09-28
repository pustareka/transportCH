package com.challenge.transportCH.service;

import com.challenge.transportCH.exception.ConnectionInvalidRequestException;
import com.challenge.transportCH.exception.LocationInvalidRequestException;
import com.challenge.transportCH.service.impl.LocationServiceImpl;
import com.challenge.transportCH.service.mapping.LocationObjectJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LocationServiceImplTest {

    @Autowired
    private LocationServiceImpl underTest;

    @Test
    public void getLocationsUsingRequiredParamStationName_success() {

        LocationObjectJson locationObjectJson = underTest.getLocations("Basel SBB", null, null, null);

        assertThat(locationObjectJson, is(notNullValue()));
        assertThat(locationObjectJson.getStations().size(), is(10));
        assertThat(locationObjectJson.getStations().get(0).getName(), is("Basel SBB"));
    }

    @Test
    public void getLocationsUsingRequiredParamsForCoordinates_success() {

        LocationObjectJson locationObjectJson = underTest.getLocations(null, new BigDecimal("47.548284"), new BigDecimal("7.59031"), null);

        assertThat(locationObjectJson, is(notNullValue()));
        assertThat(locationObjectJson.getStations().size(), is(10));
    }

    @Test()
    public void getLocationsWithoutUsingRequiredParams_shouldThrowException() {

        Exception exception = assertThrows(LocationInvalidRequestException.class, () -> {
            underTest.getLocations(null, null, null, null);
        });
        assertEquals("Requested should contain station name or coordinates.", exception.getMessage());
    }
}

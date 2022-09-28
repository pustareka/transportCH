package com.challenge.transportCH.service;

import com.challenge.transportCH.exception.ConnectionInvalidRequestException;
import com.challenge.transportCH.service.impl.ConnectionServiceImpl;
import com.challenge.transportCH.service.mapping.ConnectionObjectJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class ConnectionServiceImplTest {

    @Autowired
    private ConnectionServiceImpl underTest;

    @Test
    public void getConnectionsUsingOnlyRequiredParams_success() {

        ConnectionObjectJson connectionObjectJson = underTest.getConnections("Basel", "Lausanne", null, null);

        assertThat(connectionObjectJson, is(notNullValue()));
        assertThat(connectionObjectJson.getConnections().size(), is(4));
        assertThat(connectionObjectJson.getConnections().get(0).getFrom().getStation().getName(), is("Basel SBB"));
        assertThat(connectionObjectJson.getConnections().get(0).getTo().getStation().getName(), is("Lausanne"));
        assertThat(connectionObjectJson.getConnections().get(0).getDuration(), is(notNullValue()));

    }

    @Test
    public void getConnectionsUsingRequiredParamsAndDateTime_success() {

        final LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        final LocalDate travelDate = LocalDate.now(ZoneId.systemDefault()).plusDays(2);
        final LocalTime timeToTravel = LocalTime.now(ZoneId.systemDefault()).plusHours(2);

        ConnectionObjectJson connectionObjectJson = underTest.getConnections(
                "Basel SBB",
                "Lausanne",
                travelDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                timeToTravel.format(DateTimeFormatter.ofPattern("HH:mm")));

        assertThat(connectionObjectJson, is(notNullValue()));
        assertThat(connectionObjectJson.getConnections().size(), is(4));
        assertThat(connectionObjectJson.getConnections().get(0).getFrom().getStation().getName(), is("Basel SBB"));
        assertThat(connectionObjectJson.getConnections().get(0).getTo().getStation().getName(), is("Lausanne"));
        assertThat(connectionObjectJson.getConnections().get(0).getDuration(), is(notNullValue()));

        String departureTimeFirst = connectionObjectJson.getConnections().get(0).getFrom().getDeparture();
        String departureTimeSecond = connectionObjectJson.getConnections().get(1).getFrom().getDeparture();
        String departureTimeThird = connectionObjectJson.getConnections().get(2).getFrom().getDeparture();
        String departureTimeFourth = connectionObjectJson.getConnections().get(3).getFrom().getDeparture();

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertThat(LocalDateTime.parse(departureTimeFirst, newFormatter), greaterThan(now.plusDays(2).plusHours(1)));
        assertThat(LocalDateTime.parse(departureTimeFirst, newFormatter), lessThan(now.plusDays(2).plusHours(4)));
        assertThat(LocalDateTime.parse(departureTimeSecond, newFormatter), greaterThan(now.plusDays(2).plusHours(1)));
        assertThat(LocalDateTime.parse(departureTimeSecond, newFormatter), lessThan(now.plusDays(2).plusHours(4)));
        assertThat(LocalDateTime.parse(departureTimeThird, newFormatter), greaterThan(now.plusDays(2).plusHours(1)));
        assertThat(LocalDateTime.parse(departureTimeThird, newFormatter), lessThan(now.plusDays(2).plusHours(4)));
        assertThat(LocalDateTime.parse(departureTimeFourth, newFormatter), greaterThan(now.plusDays(2).plusHours(1)));
        assertThat(LocalDateTime.parse(departureTimeFourth, newFormatter), lessThan(now.plusDays(2).plusHours(4)));
    }

    @Test
    public void getConnectionsUsingRequiredParamsAndDateTime_destinationNotInCH_emptyConnections() {

        ConnectionObjectJson connectionObjectJson = underTest.getConnections(
                "Belgrade",
                "Lausanne",
                null,
                null);

        assertThat(connectionObjectJson, is(notNullValue()));
        assertThat(connectionObjectJson.getConnections().size(), is(0));
    }

    @Test()
    public void getConnectionsUsingOnlyRequiredParamFrom_shouldThrowException() {

        Exception exception = assertThrows(ConnectionInvalidRequestException.class, () -> {
            underTest.getConnections("Basel", null, null, null);
        });
        assertEquals("Requested should contain start location and end destination.", exception.getMessage());
    }

    @Test()
    public void getConnectionsUsingOnlyRequiredParamTo_shouldThrowException() {

        Exception exception = assertThrows(ConnectionInvalidRequestException.class, () -> {
            underTest.getConnections(null, "Basel", null, null);
        });
        assertEquals("Requested should contain start location and end destination.", exception.getMessage());
    }
}

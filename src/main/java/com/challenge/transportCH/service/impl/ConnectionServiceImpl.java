package com.challenge.transportCH.service.impl;

import com.challenge.transportCH.exception.ConnectionInvalidRequestException;
import com.challenge.transportCH.exception.GeneralServerError;
import com.challenge.transportCH.service.ConnectionService;
import com.challenge.transportCH.service.mapping.ConnectionJson;
import com.challenge.transportCH.service.mapping.ConnectionObjectJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionServiceImpl.class);

    private static final String CH_TRANSPORTATION_API_CONNECTION = "https://transport.opendata.ch/v1/connections";

    @Override
    public ConnectionObjectJson getConnections(String from, String to, String date, String time) {
        String connectionsUrl = null;
        final ObjectMapper objectMapper = new ObjectMapper();

        if (StringUtils.hasLength(from) && StringUtils.hasLength(to)) {
            connectionsUrl = CH_TRANSPORTATION_API_CONNECTION + "?from=" + urlEncode(from) + "&to=" + urlEncode(to);
        } else {
            throw new ConnectionInvalidRequestException(ConnectionServiceImpl.class);
        }
        if (StringUtils.hasLength(date)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            connectionsUrl += "&date=" + localDate;
        }
        if (StringUtils.hasLength(time)) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            connectionsUrl += "&time=" + localTime;
        }
        LOG.info("Calling connections api {}", connectionsUrl);

        try {
            ConnectionObjectJson connectionObjectJson = objectMapper.readValue(new URL(connectionsUrl), ConnectionObjectJson.class);
            return formatDatesForDisplay(connectionObjectJson);
        } catch (IOException e) {
            LOG.error("Something went wrong while requesting connections", e);
            throw new GeneralServerError(e.getMessage(), ConnectionServiceImpl.class);
        }
    }

    String urlEncode(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }

    protected ConnectionObjectJson formatDatesForDisplay(ConnectionObjectJson connectionObjectJson) {
        final DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (ConnectionJson connection : connectionObjectJson.getConnections()) {
            OffsetDateTime departureTimeSource = OffsetDateTime.parse(connection.getFrom().getDeparture(), formatter);
            OffsetDateTime arrivalTimeSource = OffsetDateTime.parse(connection.getTo().getArrival(), formatter);
            connection.getFrom().setDeparture(departureTimeSource.format(newFormatter));
            connection.getTo().setArrival(arrivalTimeSource.format(newFormatter));
        }
        return connectionObjectJson;
    }

    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            // date/time
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            // offset (hh:mm - "+00:00" when it's zero)
            .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
            // offset (hhmm - "+0000" when it's zero)
            .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
            // offset (hh - "Z" when it's zero)
            .optionalStart().appendOffset("+HH", "Z").optionalEnd()
            // create formatter
            .toFormatter();
}

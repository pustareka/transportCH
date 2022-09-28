package com.challenge.transportCH.controller;

import com.challenge.transportCH.exception.ConnectionObjectNotFoundException;
import com.challenge.transportCH.service.ConnectionService;
import com.challenge.transportCH.service.mapping.ConnectionObjectJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConnectionObjectController {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionObjectController.class);

    private final ConnectionService connectionService;

    public ConnectionObjectController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping(value = "/index")
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value = "/connections")
    public String getConnections(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(required = false) String departureDate,
            @RequestParam(required = false) String departureTime,
            @ModelAttribute("model") ModelMap model) {
        final ConnectionObjectJson connections = connectionService.getConnections(from, to, departureDate, departureTime);
        model.addAttribute("connectionResults", connections);
        if (connections != null) {
            return "index";
        } else {
            LOG.error("Something went wrong. Connections not found.");
            throw new ConnectionObjectNotFoundException(from, to, ConnectionObjectJson.class);
        }
    }
}

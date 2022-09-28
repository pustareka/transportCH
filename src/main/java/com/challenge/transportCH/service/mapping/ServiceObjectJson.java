package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceObjectJson {
    private String regular;
    private String irregular;

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getIrregular() {
        return irregular;
    }

    public void setIrregular(String irregular) {
        this.irregular = irregular;
    }
}

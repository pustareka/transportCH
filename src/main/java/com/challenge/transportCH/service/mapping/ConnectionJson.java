package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionJson {

    private StopObjectJson from;
    private StopObjectJson to;
    private String duration;
    private ServiceObjectJson service;
    private List<String> products;
    private Integer capacity1st;
    private Integer capacity2nd;
    private List<SectionJson> sections;

    public StopObjectJson getFrom() {
        return from;
    }

    public void setFrom(StopObjectJson from) {
        this.from = from;
    }

    public StopObjectJson getTo() {
        return to;
    }

    public void setTo(StopObjectJson to) {
        this.to = to;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ServiceObjectJson getService() {
        return service;
    }

    public void setService(ServiceObjectJson service) {
        this.service = service;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public Integer getCapacity1st() {
        return capacity1st;
    }

    public void setCapacity1st(Integer capacity1st) {
        this.capacity1st = capacity1st;
    }

    public Integer getCapacity2nd() {
        return capacity2nd;
    }

    public void setCapacity2nd(Integer capacity2nd) {
        this.capacity2nd = capacity2nd;
    }

    public List<SectionJson> getSections() {
        return sections;
    }

    public void setSections(List<SectionJson> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionJson)) return false;
        ConnectionJson that = (ConnectionJson) o;
        return from.equals(that.from) &&
                to.equals(that.to) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(service, that.service) &&
                Objects.equals(products, that.products) &&
                Objects.equals(capacity1st, that.capacity1st) &&
                Objects.equals(capacity2nd, that.capacity2nd) &&
                Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}

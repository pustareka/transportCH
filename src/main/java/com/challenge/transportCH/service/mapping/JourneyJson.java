package com.challenge.transportCH.service.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyJson {

    private String name;
    private String category;
    private String categoryCode;
    private String subcategory;
    private String number;
    private String operator;
    private String to;
    private List<StopObjectJson> passList;
    private String capacity1st;
    private String capacity2nd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<StopObjectJson> getPassList() {
        return passList;
    }

    public void setPassList(List<StopObjectJson> passList) {
        this.passList = passList;
    }

    public String getCapacity1st() {
        return capacity1st;
    }

    public void setCapacity1st(String capacity1st) {
        this.capacity1st = capacity1st;
    }

    public String getCapacity2nd() {
        return capacity2nd;
    }

    public void setCapacity2nd(String capacity2nd) {
        this.capacity2nd = capacity2nd;
    }
}

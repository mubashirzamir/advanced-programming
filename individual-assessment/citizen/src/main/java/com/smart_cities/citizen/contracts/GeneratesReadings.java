package com.smart_cities.citizen.contracts;

public interface GeneratesReadings {
    public Long getId();
    public String getType();
    public int generateReading();
}

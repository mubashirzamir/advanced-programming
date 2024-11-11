package com.smart_cities.citizen.contracts;

public interface GenerateBehavior {
    public Long generate();

    public Long getCurrentNumber();

    public void setCurrentNumber(Long currentNumber);
}

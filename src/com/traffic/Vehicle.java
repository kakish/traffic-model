package com.traffic;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Usie on 13.11.2016.
 */
public class Vehicle {

    private Integer id;
    private static Integer nextId;

    private Integer length;
    private Integer maxSpeed;
    private Driver driver;

    private Integer speed;
    private BigDecimal bias;

    private Integer roadId;
    private Integer lane;
    private Integer position;

    public void accelerate(Integer acceleration) {
        this.speed += acceleration;
        if (this.speed > this.maxSpeed ) {
            this.speed = maxSpeed;
        }
    }

    public void brake(Integer deceleration) {
        this.speed -= deceleration;
        if (this.speed < 0 ) {
            this.speed = 0;
        }
    }

    public void hesitate() {

        Random randomGenerator = new Random();
        Integer probability = driver.getHesitateProbability().multiply(new BigDecimal("100")).intValueExact();
        Integer randomNumber = randomGenerator.nextInt(100);

        if (randomNumber <= probability) {
            brake(1);
        }

    }


    public Vehicle(Integer length, Integer maxSpeed, Driver driver, Integer roadId, Integer lane) {
        this.id = nextId;
        nextId++;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.driver = driver;
        this.roadId = roadId;
        this.lane = lane;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


}

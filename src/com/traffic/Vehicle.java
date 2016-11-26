package com.traffic;

import java.math.BigDecimal;
import java.util.Random;
import java.util.List;

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

    public boolean hesitate() {

        Random randomGenerator = new Random();
        Integer probability = driver.getHesitateProbability().multiply(new BigDecimal("100")).intValueExact();
        Integer randomNumber = randomGenerator.nextInt(100);

        if (randomNumber < probability) {
            brake(1);
            return true;
        }
        return false;
    }

    public void move() {
        this.position += this.speed;
    }

    public void keepLane(List<Vehicle> vehicleList, Integer visibility) {

        if (!hesitate()) {
            accelerate(this.getDriver().getAcceleration());
        }
        Integer aheadGap = this.aheadGap(vehicleList, 0, visibility) - 1;
        Integer deceleration = this.speed - aheadGap;
        if (deceleration > 0) {
            brake(deceleration);
        }
        move();
    }


    public Integer aheadGap(List<Vehicle> vehicleList, Integer relativeLane,  Integer visibility) {

        Integer gap = visibility;

        if (vehicleList != null && vehicleList.size()>0) {
            for (Vehicle vehicle : vehicleList) {

                if (vehicle.getId() == this.id) {
                    continue;
                }
                if (vehicle.getRoadId() != this.roadId ||
                        vehicle.getLane() != this.lane + relativeLane ||
                        vehicle.getPosition() < this.position) {
                    continue;
                }
                if (gap > vehicle.getPosition() - this.position) {
                    gap = vehicle.getPosition() - this.position;
                }
            }
        }
        return gap;
    }

    public Integer behindGap(List<Vehicle> vehicleList, Integer relativeLane,  Integer visibility) {

        Integer gap = visibility;

        if (vehicleList != null && vehicleList.size()>0) {
            for (Vehicle vehicle : vehicleList) {

                if (vehicle == this) {
                    continue;
                }
                if (vehicle.getRoadId() != this.roadId ||
                        vehicle.getLane() != this.lane + relativeLane ||
                        vehicle.getPosition() > this.position) {
                    continue;
                }
                if (gap > this.position -  vehicle.getPosition()) {
                    gap = this.position -  vehicle.getPosition();
                }
            }
        }
        return gap;
    }

    public Vehicle(Integer length, Integer maxSpeed, Driver driver, Integer roadId, Integer lane) {
        this.id = nextId;
        nextId++;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.driver = driver;
        this.roadId = roadId;
        this.lane = lane;
        this.position = 0;
        this.speed = 0;
        this.bias = new BigDecimal("0");
    }

    public Vehicle()
    {

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

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Driver getDriver() {
        return driver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBias() {
        return bias;
    }

    public void setBias(BigDecimal bias) {
        this.bias = bias;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", length=" + length +
                ", maxSpeed=" + maxSpeed +
                ", driver=" + driver +
                ", speed=" + speed +
                ", bias=" + bias +
                ", roadId=" + roadId +
                ", lane=" + lane +
                ", position=" + position +
                '}';
    }

    static {
        nextId = 1;
    }
}

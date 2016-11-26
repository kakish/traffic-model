package com.traffic;

import java.math.BigDecimal;

/**
 * Created by Usie on 13.11.2016.
 */
public class Driver
{
    private Integer acceleration;
    private BigDecimal hesitateProbability;

    public Integer getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Integer acceleration) {
        this.acceleration = acceleration;
    }

    public BigDecimal getHesitateProbability() {
        return hesitateProbability;
    }

    public void setHesitateProbability(BigDecimal hesitateProbability) {
        this.hesitateProbability = hesitateProbability;
    }

    public Driver(Integer acceleration, BigDecimal hesitateProbability) {
        this.acceleration = acceleration;
        this.hesitateProbability = hesitateProbability;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "acceleration=" + acceleration +
                ", hesitateProbability=" + hesitateProbability +
                '}';
    }
}

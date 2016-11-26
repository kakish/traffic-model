package com.traffic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Usie on 13.11.2016.
 */
public class Road {

    private void putLane(Lane lane) {
        if (this.getLanes() != null) {
            this.getLanes().add(lane);
        } else {
            List<Lane> newLanes = new LinkedList<>();
            newLanes.add(lane);
            this.setLanes(newLanes);
        }
    }

    public void addNotConnectedLane() {
        addLane(null, null);
    }

    public void addNotConnectedLane(Integer length) {
        addLane(null, length, null);
    }

    public void addLane(Integer startConnector, Integer endConnector) {
        addLane(startConnector, this.length, endConnector);
    }

    public void addLane(Integer startConnector, Integer length, Integer endConnector) {
        addLane(0, startConnector, length, endConnector);
    }

    public void addLane(Integer startPosition, Integer startConnector, Integer length, Integer endConnector) {
        Lane lane = new Lane(startPosition, length, startConnector, endConnector, (startConnector == null), (endConnector == null));
        putLane(lane);
        if (this.length < length + startPosition) {
            this.length = length + startPosition;
        }
    }

    public void addOnRamp(Integer startConnector, Integer length) {
        addLane(startConnector, length, null);
    }

    public void addOnRamp(Integer length) {
        addOnRamp(null, length);
    }

    public void addOffRamp(Integer length, Integer endConnector) {
        Integer offPosition = this.length - length;
        addLane(offPosition, null, length, endConnector);
    }

    public void addOffRamp(Integer length) {
        addOffRamp(length, null);
    }

    public void removeLastLane() {
        if (lanes == null || lanes.size() == 0) {
            return;
        }
        lanes.remove(lanes.size());
    }


    private Integer id;
    private static Integer nextId;
    private List<Lane> lanes;
    private Integer length;

    public Road(Integer length) {
        id = nextId;
        nextId++;
        this.length = length;
    }

    public Road() {
        new Road(0);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public void setLanes(List<Lane> lanes) {
        this.lanes = lanes;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", lanes=" + lanes +
                ", length=" + length +
                '}';
    }

    static {
        nextId = 1;
    }
}


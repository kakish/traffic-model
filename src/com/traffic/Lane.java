package com.traffic;

/**
 * Created by Usie on 13.11.2016.
 */
public class Lane {
    private Integer startPosition;
    private Integer endPosition;
    private Integer startConnector;
    private Integer endConnector;
    private boolean freeStart;
    private boolean freeEnd;



    public Lane(Integer startPosition, Integer endPosition, Integer startConnector, Integer endConnector, boolean freeStart, boolean freeEnd) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.startConnector = startConnector;
        this.endConnector = endConnector;
        this.freeStart = freeStart;
        this.freeEnd = freeEnd;
    }

    public Lane() {

    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public Integer getStartConnector() {
        return startConnector;
    }

    public void setStartConnector(Integer startConnector) {
        this.startConnector = startConnector;
    }

    public Integer getEndConnector() {
        return endConnector;
    }

    public void setEndConnector(Integer endConnector) {
        this.endConnector = endConnector;
    }

    public boolean isFreeStart() {
        return freeStart;
    }

    public void setFreeStart(boolean freeStart) {
        this.freeStart = freeStart;
    }

    public boolean isFreeEnd() {
        return freeEnd;
    }

    public void setFreeEnd(boolean freeEnd) {
        this.freeEnd = freeEnd;
    }
    @Override
    public String toString() {
        return "Lane{" +
                "startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", startConnector=" + startConnector +
                ", endConnector=" + endConnector +
                ", freeStart=" + freeStart +
                ", freeEnd=" + freeEnd +
                '}';
    }
}

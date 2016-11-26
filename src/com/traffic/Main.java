package com.traffic;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        Driver standardDriver = new Driver(1, new BigDecimal("0.1"));

        List<Vehicle> vehicleList = new LinkedList<>();
        List<Road> roadList = new LinkedList<>();

        BigDecimal alfa = new BigDecimal("0.8");

        Road myRoad = new Road(1000);
        myRoad.addNotConnectedLane();
//        myRoad.addNotConnectedLane();
//        myRoad.addOnRamp(6);

        roadList.add(myRoad);

        for (int i = 0; i < 1000; i++) {

            List<Vehicle> vehicleListState = copyVehicleList(vehicleList);
            List<Vehicle> vehiclesToRemove = new LinkedList<>();

            if (checkProbability(alfa) && checkSpace(vehicleList, myRoad.getId(), 0)) {
                vehicleList.add(new Vehicle(1, 20, standardDriver, myRoad.getId(), 0));
            }

            for (Vehicle vehicle : vehicleList) {
                vehicle.keepLane(vehicleListState, 20);
                if (!resolvePosition(vehicle, roadList)) {
                    vehiclesToRemove.add(vehicle);
                }
            }

            // System.out.println(vehiclesToRemove.toString());

            vehicleList.removeAll(vehiclesToRemove);
//            System.out.println(vehicleList.toString());
            drawRoad(roadList.get(0), vehicleList);


        }
//
//        vehicleList.add(new Vehicle(1, 5, standardDriver, myRoad.getId(), 0));
//        vehicleList.add(new Vehicle(1, 5, standardDriver, myRoad.getId(), 0));
//        vehicleList.get(0).setPosition(10);

//        Road mySecondRoad = new Road(15);
//        mySecondRoad.addOffRamp(15,2);
//
//        System.out.println(myRoad.toString());
//        System.out.println(vehicleList.toString());
//        System.out.println(vehicleList.get(1).aheadGap(vehicleList, 0, 30));
//        System.out.println(vehicleList.get(0).behindGap(vehicleList, 0, 30));
//        System.out.println(mySecondRoad.toString());
    }

    private static List<Vehicle> copyVehicleList( List<Vehicle> vehicleList ) {

        List<Vehicle> newVehicleList = new LinkedList<>();

        for (Vehicle vehicle: vehicleList) {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setId(vehicle.getId());
            newVehicle.setLength(vehicle.getLength());
            newVehicle.setMaxSpeed(vehicle.getMaxSpeed());
            newVehicle.setDriver(vehicle.getDriver());
            newVehicle.setRoadId(vehicle.getRoadId());
            newVehicle.setLane(vehicle.getLane());
            newVehicle.setPosition(vehicle.getPosition());
            newVehicle.setSpeed(vehicle.getSpeed());
            newVehicle.setBias(vehicle.getBias());

            newVehicleList.add(newVehicle);
        }
        return newVehicleList;
    }

    private static boolean checkProbability(BigDecimal p) {

        Random randomGenerator = new Random();
        Integer probability = p.multiply(new BigDecimal("100")).intValueExact();
        Integer randomNumber = randomGenerator.nextInt(100);

        if (randomNumber < probability) {
            return true;
        }
        return false;
    }

    public static void drawRoad (Road road, List<Vehicle> vehicleList) {
        char[][] roadMap = new char[road.getLanes().size()][road.getLength()+1];

        Integer laneId = 0;
        for (Lane lane : road.getLanes()) {
            for ( Integer position = 0 ; position <= road.getLength() ; position++) {
                if (position >= lane.getStartPosition() && position <= lane.getEndPosition()) {
                    roadMap[laneId][position] = '-';
                } else {
                    roadMap[laneId][position] = ' ';
                }
            }
            laneId++;
        }

        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getRoadId() == road.getId()) {
                roadMap[vehicle.getLane()][vehicle.getPosition()] = 'o';
            }
        }

        for (char[] line : roadMap) {
            System.out.println(new String(line));
        }
        //System.out.println();

    }

    public static boolean checkSpace(List<Vehicle> vehicleList, Integer roadId, Integer laneId) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getRoadId() == roadId && vehicle.getLane() == laneId && vehicle.getPosition() == 0) {
                return false;
            }
        }
        return true;
    }

    public static Road getRoadById(List<Road> roadList, Integer id) {

        Road foundRoad = new Road();

        for (Road road : roadList) {
            if (road.getId() == id) {
                foundRoad = road;
                break;
            }
        }
        return foundRoad;
    }

    public static boolean resolvePosition(Vehicle vehicle, List<Road> roadList) {

        Road vehicleRoad = getRoadById(roadList, vehicle.getRoadId());
        Lane vehicleLane = vehicleRoad.getLanes().get(vehicle.getLane());

        if (vehicle.getPosition() > vehicleLane.getEndPosition()) {

            //System.out.println("pos: " + vehicle.getPosition() + " end: " + vehicleLane.getEndPosition());

            if (vehicleLane.isFreeEnd()) {
                return false;
            }

            Integer remainLanes = 0;

            Road newRoad = getRoadById(roadList, vehicleLane.getEndConnector());

            Integer newLaneId = 0;
            Lane newLane = new Lane();
            for (Lane lane : newRoad.getLanes()) {
                if (lane.getStartConnector() == vehicle.getRoadId()) {
                    if (remainLanes == 0) {
                        newLane = lane;
                        break;
                    } else {
                        remainLanes--;
                    }
                    newLaneId++;
                }
            }

            Integer newPosition = vehicleLane.getEndPosition()-vehicle.getPosition()+newLane.getStartPosition();

            vehicle.setRoadId(newRoad.getId());
            vehicle.setLane(newLaneId);
            vehicle.setPosition(newPosition);

        }

        return true;

    }

}

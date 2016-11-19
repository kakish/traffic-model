package com.traffic;

public class Main {

    public static void main(String[] args) {


        Road myRoad = new Road(20);
        myRoad.addNotConnectedLane();
//        myRoad.addNotConnectedLane();
        myRoad.addOnRamp(6);

//        Road mySecondRoad = new Road(15);
//        mySecondRoad.addOffRamp(15,2);

        System.out.println(myRoad.toString());
//        System.out.println(mySecondRoad.toString());
    }
}

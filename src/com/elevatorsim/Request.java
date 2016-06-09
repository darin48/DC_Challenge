package com.elevatorsim;

/**
 * Created by Darin on 6/9/2016.
 */
public class Request {

    private int sourceFloor;
    private int destinationFloor;

    public Request(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

}

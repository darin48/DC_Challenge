package com.elevatorsim;

/**
 * Created by Darin on 6/9/2016.
 */
public class Building {
    private int floorCount;
    private int elevatorCount;
    private int minimumFloor;
    private int maximumFloor;

    public Building() {
        // Default floor and elevator count to 1 if not specified
        this.floorCount = 1;
        this.minimumFloor = 1;
        this.elevatorCount = 1;
    }

    public Building(int floorCount, int elevatorCount) {
        this.floorCount = floorCount;
        this.minimumFloor = 1;
        this.maximumFloor = floorCount;
        this.elevatorCount = elevatorCount;
    }

    public int getFloorCount() {
        return this.floorCount;
    }

    public int getElevatorCount() {
        return this.elevatorCount;
    }

    public int getMinimumFloor() {
        return this.minimumFloor;
    }

    public int getMaximumFloor() {
        return this.maximumFloor;
    }
}

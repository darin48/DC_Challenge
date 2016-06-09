package com.elevatorsim;

/**
 * Created by Darin on 6/9/2016.
 */
public class Elevator {
    public enum DoorState {OPEN, CLOSED}
    public enum OccupationState {UNOCCUPIED, OCCUPIED}
    private int currentFloor;
    private int tripCount;
    private static int maxFloor;
    private static int minFloor;
    private boolean serviceNeeded;
    private DoorState doorState;
    private OccupationState occupationState;

    public Elevator() {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = 1;
        this.maxFloor = 10;
        this.tripCount = 0;
        this.currentFloor = minFloor;
        this.serviceNeeded = false;
    }

    public Elevator(Building building) {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = building.getMinimumFloor();
        this.maxFloor = building.getMaximumFloor();
        this.tripCount = 0;
        this.currentFloor = minFloor;
        this.serviceNeeded = false;
    }

    public void serviceElevator() {
        this.serviceNeeded = false;
    }

    public void processRequest(int sourceFloor, int destinationFloor) {
        if ()
    }

}

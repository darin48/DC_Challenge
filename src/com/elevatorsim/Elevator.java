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
    private Controller controller;

    public Elevator() {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = 1;
        this.maxFloor = 10;
        this.tripCount = 0;
        this.currentFloor = minFloor;
        this.serviceNeeded = false;
    }

    public Elevator(Building building, Controller controller) {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = building.getMinimumFloor();
        this.maxFloor = building.getMaximumFloor();
        this.tripCount = 0;
        this.currentFloor = minFloor;
        this.serviceNeeded = false;
        this.controller = controller;
    }

    public void serviceElevator() {
        this.serviceNeeded = false;
    }

    public boolean processRequest(int sourceFloor, int destinationFloor) {
        if (floorValid(sourceFloor) && floorValid(destinationFloor)) {
            if ((this.currentFloor != sourceFloor) &&
                    (this.doorState == DoorState.OPEN)) {
                closeDoors();
            }
            this.doorState = DoorState.CLOSED;
        }
    }

    private boolean floorValid(int floorNumber) {
        if ((floorNumber < this.minFloor) ||
                (floorNumber > this.maxFloor)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean closeDoors() {
        this.doorState = DoorState.CLOSED;

    }
}

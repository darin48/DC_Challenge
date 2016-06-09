package com.elevatorsim;

import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Darin on 6/9/2016.
 */
public class Elevator {
    public enum DoorState {OPEN, CLOSED}
    public enum OccupationState {UNOCCUPIED, OCCUPIED}
    private int currentFloor;
    private SortedSet<Integer> destinationFloors;
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
        this.destinationFloors = new TreeSet();
        this.serviceNeeded = false;
    }

    public Elevator(Building building, Controller controller) {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = building.getMinimumFloor();
        this.maxFloor = building.getMaximumFloor();
        this.tripCount = 0;
        this.currentFloor = minFloor;
        this.destinationFloors = new TreeSet();
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

    public void proceed() {
        if (!this.serviceNeeded) {
            if (this.currentFloor != this.destinationFloor) {
                if (this.currentFloor > this.destinationFloor) {
                    this.currentFloor--;
                } else {
                    this.currentFloor++;
                }
            } else if (this.occupationState == OccupationState.OCCUPIED) {
                this.doorState = DoorState.OPEN;
                // TODO: need to check if there is a second destination floor
                this.occupationState = OccupationState.UNOCCUPIED;
            }
        }
    }
}

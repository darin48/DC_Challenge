package com.elevatorsim;

import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by Darin on 6/9/2016.
 */
public class Elevator {
    public enum DoorState {OPEN, CLOSED}
    public enum OccupationState {UNOCCUPIED, OCCUPIED}
    public static final int MAX_TRIPS_BEFORE_SERVICE = 100;
    private int currentFloor;
    private SortedSet<Request> requestsToPickup;
    private SortedSet<Request> requestsToDropOff;
    private int tripCount;
    private int nextServiceTripCount;
    private int floorCount;
    private static int maxFloor;
    private static int minFloor;
    private boolean serviceNeeded;
    private DoorState doorState;
    private OccupationState occupationState;
    private Controller controller;


    public Elevator(Building building, Controller controller) {
        this.doorState = DoorState.CLOSED;
        this.occupationState = OccupationState.UNOCCUPIED;
        this.minFloor = building.getMinimumFloor();
        this.maxFloor = building.getMaximumFloor();
        this.tripCount = 0;
        this.nextServiceTripCount = 0;
        this.floorCount = 0;
        this.currentFloor = minFloor;
        this.requestsToPickup = new TreeSet();
        this.requestsToDropOff = new TreeSet();
        this.serviceNeeded = false;
        this.controller = controller;
    }

    public void serviceElevator() {
        this.serviceNeeded = false;
        this.nextServiceTripCount += MAX_TRIPS_BEFORE_SERVICE;
    }

    private boolean floorValid(int floorNumber) {
        if ((floorNumber < this.minFloor) ||
                (floorNumber > this.maxFloor)) {
            return false;
        } else {
            return true;
        }
    }

    private int getClosestStop() {
        Request currentRequest;
        int minDistance = this.maxFloor;
        int currentDistance;
        int bestFloor = this.currentFloor;
        Iterator<Request> requestIterator;
        if (!this.requestsToPickup.isEmpty()) {
            requestIterator = this.requestsToPickup.iterator();
            while (requestIterator.hasNext()) {
                currentRequest = requestIterator.next();
                currentDistance = getFloorDistance(currentRequest.getSourceFloor());
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestFloor = currentRequest.getSourceFloor();
                }
            }
        }
        if (!this.requestsToDropOff.isEmpty()) {
            requestIterator = this.requestsToDropOff.iterator();
            while (requestIterator.hasNext()) {
                currentRequest = requestIterator.next();
                currentDistance = getFloorDistance(currentRequest.getDestinationFloor());
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestFloor = currentRequest.getDestinationFloor();
                }
            }
        }
        return bestFloor;
    }

    public void step() {
        if (!this.serviceNeeded) {
            int nextStop = getClosestStop();
            if (this.currentFloor != nextStop) {
                if (this.doorState == DoorState.OPEN) {
                    this.doorState = DoorState.CLOSED;
                }
                else {
                    this.floorCount++;
                    if (this.currentFloor > nextStop) {
                        this.currentFloor--;
                    } else {
                        this.currentFloor++;
                    }
                }
            } else {
                if (this.doorState == DoorState.CLOSED) {
                    this.doorState = DoorState.OPEN;
                } else {
                    this.tripCount += clearDestinationRequest();
                    if (this.tripCount >= this.nextServiceTripCount) {
                        this.serviceNeeded = true;
                        // Kick anyone still on the elevator off
                        this.occupationState = OccupationState.UNOCCUPIED;
                        this.requestsToDropOff.clear();
                        this.requestsToPickup.clear();
                    } else {
                        makePickup();
                        if (this.requestsToDropOff.isEmpty()) {
                            this.occupationState = OccupationState.UNOCCUPIED;
                        } else {
                            this.occupationState = OccupationState.OCCUPIED;
                        }
                    }
                }
            }
        }
    }

    public int getFloorDistance(int floorToCheck) {
        if (!this.serviceNeeded) {
            // TODO: check if the destination floor is different than current.  If different, and
            // current trip pushes over 100 trips, will need service after current trip
            return abs(floorToCheck - this.currentFloor);
        } else {
            return this.maxFloor;
        }
    }

    public void addRequest(Request req) {
        this.requestsToPickup.add(req);
    }

    private int clearDestinationRequest() {
        Iterator<Request> requestIterator = this.requestsToDropOff.iterator();
        int clearedCount = 0;
        while (requestIterator.hasNext()) {
            Request request = requestIterator.next();
            if (request.getDestinationFloor() == this.currentFloor) {
                this.requestsToDropOff.remove(request);
                clearedCount++;
            }
        }
        return clearedCount;
    }

    private int makePickup() {
        Iterator<Request> requestIterator = this.requestsToPickup.iterator();
        int clearedCount = 0;
        while (requestIterator.hasNext()) {
            Request request = requestIterator.next();
            if (request.getSourceFloor() == this.currentFloor) {
                this.requestsToPickup.remove(request);
                this.requestsToDropOff.add(request);
                clearedCount++;
            }
        }
        return clearedCount;
    }
}

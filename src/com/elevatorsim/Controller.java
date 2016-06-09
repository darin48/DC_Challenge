package com.elevatorsim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Darin on 6/9/2016.
 */
public class Controller {
    private List<Elevator> elevatorList;
    private List<Request> requestList;
    private Building building;

    public Controller(Building building) {
        this.elevatorList = new ArrayList<>();
        this.requestList = new ArrayList<>();
        this.building = building;
        for (int i = 0; i < building.getElevatorCount(); i++) {
            elevatorList.add(new Elevator(building, this));
        }
    }

    public boolean addRequest(Request req) {
        // TODO: Add request validation
        Iterator<Elevator> iterator = elevatorList.iterator();
        int minFloorDiff = building.getMaximumFloor();
        int floorDiff;
        Elevator bestElevatorOption = null;
        Elevator elevator;
        while (iterator.hasNext()) {
            elevator = iterator.next();
            floorDiff = elevator.getFloorDistance(req.getSourceFloor());
            if (floorDiff < minFloorDiff) {
                bestElevatorOption = elevator;
                minFloorDiff = floorDiff;
            }
        }
        if (bestElevatorOption != null) {
            bestElevatorOption.addRequest(req);
        }
    }
}

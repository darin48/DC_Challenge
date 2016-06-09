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

    public Controller(Building building) {
        this.elevatorList = new ArrayList<>();
        this.requestList = new ArrayList<>();
        for (int i = 0; i < building.getElevatorCount(); i++) {
            elevatorList.add(new Elevator(building, this));
        }
    }

    public boolean addRequest(Request req) {
        // TODO: Add request validation
        Iterator<Elevator> iterator = elevatorList.iterator();
        while (iterator.hasNext()) {
            
        }
    }
}

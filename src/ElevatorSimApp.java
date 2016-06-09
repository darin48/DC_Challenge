import com.elevatorsim.Building;
import com.elevatorsim.Controller;
import com.elevatorsim.Request;

import static java.lang.Math.floor;

/**
 * Created by Darin on 6/9/2016.
 */
public class ElevatorSimApp {
    private static Controller myController;
    private static int maxFloor;

    public static void main(String args[]) {
        // Process arguments that specify the number of elevators and the number of floors
        // Validate both argumentsA
        int maxFloor = 10;
        int elevatorArgument = 3;
        int stepCount = 100;
        Building myBuilding = new Building(maxFloor, elevatorArgument);
        myController = new Controller(myBuilding);
        for (int i = 0; i < stepCount; i++) {
            myController.addRequest(generateRequest());
            myController.step();
        }
    }

    public static Request generateRequest() {
        int source = (int) floor(Math.random() * maxFloor) + 1;
        int destination = (int) floor(Math.random() * maxFloor) + 1;
        Request newReq = new Request(source, destination);
        return newReq;
    }
}

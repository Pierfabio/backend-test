package rover;

import coordinates.RoverCoordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MarsRover {

    private RoverCoordinates coordinates;

    public boolean receiveCommand(char command) throws Exception {
        switch(Character.toUpperCase(command)) {
            case 'F':
                return getCoordinates().moveForward();
            case 'B':
                return getCoordinates().moveBackward();
            case 'L':
                getCoordinates().changeDirectionLeft();
                return true;
            case 'R':
                getCoordinates().changeDirectionRight();
                return true;
            default:
                throw new Exception("Command " + command + " is unknown.");
        }
    }

    public String getPosition() {
        return getCoordinates().toString();
    }
}

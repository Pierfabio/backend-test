package rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoverDirection {

    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W');

    private int value;
    private char name;

    public RoverDirection getBackwardDirection() {
        return values()[(this.getValue() + 2) % 4];
    }

}

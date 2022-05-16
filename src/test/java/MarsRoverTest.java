import coordinates.BasicCoordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverTest {

    private MarsRover rover;
    private RoverCoordinates roverCoordinates;
    private final RoverDirection direction = RoverDirection.NORTH;
    private MapPoint y;

    @BeforeEach
    public void beforeRoverTest() {
        MapPoint x = new MapPoint(new BasicCoordinates(1, 9));
        y = new MapPoint(new BasicCoordinates(2, 9));
        List<RoverObstacle> obstacles = new ArrayList<>();
        roverCoordinates = new RoverCoordinates(x, y,direction, obstacles);
        rover = new MarsRover(roverCoordinates);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        Assertions.assertEquals(rover.getCoordinates(), roverCoordinates);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        rover.receiveCommand('F');
        Assertions.assertEquals(y.getMapCoordinates().getY(), rover.getCoordinates().getY().getMapCoordinates().getY());
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        rover.receiveCommand('B');
        Assertions.assertEquals(y.getMapCoordinates().getY(), rover.getCoordinates().getY().getMapCoordinates().getY());

    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        rover.receiveCommand('L');
        Assertions.assertEquals(RoverDirection.WEST, rover.getCoordinates().getDirection());

    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        rover.receiveCommand('R');
        Assertions.assertEquals(RoverDirection.EAST, rover.getCoordinates().getDirection());
    }

    @Test
    public void receiveSingleCommandShouldIgnoreCase() throws Exception {
        rover.receiveCommand('r');
        Assertions.assertEquals(RoverDirection.EAST, rover.getCoordinates().getDirection());
    }

    @Test
    public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() {
        Assertions.assertThrows(Exception.class, () -> rover.receiveCommand('X'));
    }

}

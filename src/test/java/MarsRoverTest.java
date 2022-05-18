import coordinates.BasicCoordinates;
import coordinates.RoverCoordinates;
import map.MapPlanet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import rover.MarsRover;
import rover.RoverDirection;
import rover.RoverObstacle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverTest {

    private MarsRover rover;
    private RoverCoordinates roverCoordinates;
    private final RoverDirection direction = RoverDirection.NORTH;
    private List<RoverObstacle> obstacles;
    private MapPlanet planet;
    private BasicCoordinates roverStartingCoordinate;

    @BeforeEach
    public void beforeRoverTest() {
        planet = new MapPlanet(2,9);
        roverStartingCoordinate = new BasicCoordinates(1, 2);
        List<RoverObstacle> obstacles = new ArrayList<>();
        roverCoordinates = new RoverCoordinates(planet, roverStartingCoordinate, direction, obstacles);
        rover = new MarsRover(roverCoordinates);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        Assertions.assertEquals(rover.getCoordinates(), roverCoordinates);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expectedMapPointYPosition = roverCoordinates.getMapPointY().getPosition() + 1;
        rover.receiveCommand('F');
        Assertions.assertEquals(expectedMapPointYPosition, rover.getCoordinates().getMapPointY().getPosition());
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expectedMapPointYPosition = roverCoordinates.getMapPointY().getPosition() - 1;
        rover.receiveCommand('B');
        Assertions.assertEquals(expectedMapPointYPosition, rover.getCoordinates().getMapPointY().getPosition());

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

    @Test
    public void positionReturnedYESWhenObstacleIsFound() throws Exception {
        rover.getCoordinates().setObstacles(Arrays.asList(
                new RoverObstacle(new BasicCoordinates(roverCoordinates.getMapPointX().getPosition() + 1, roverCoordinates.getMapPointY().getPosition()))));
        rover.getCoordinates().setDirection(RoverDirection.EAST);
        rover.receiveCommand('F');
        Assertions.assertTrue(rover.getPosition().contains("YES"));
    }

    @Test
    public void positionReturnedNOWhenNoObstacleIsFound() throws Exception {
        rover.getCoordinates().setObstacles(Arrays.asList(
                new RoverObstacle(new BasicCoordinates(roverCoordinates.getMapPointX().getPosition() + 1, roverCoordinates.getMapPointY().getPosition()))));
        rover.getCoordinates().setDirection(RoverDirection.NORTH);
        rover.receiveCommand('F');
        Assertions.assertTrue(rover.getPosition().contains("NO"));
    }

}

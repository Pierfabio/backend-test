import coordinates.BasicCoordinates;
import coordinates.RoverCoordinates;
import map.MapPoint;
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

    private MapPoint mapPointX;
    private MapPoint mapPointY;
    private MarsRover rover;
    private RoverCoordinates roverCoordinates;
    private final RoverDirection direction = RoverDirection.NORTH;
    private List<RoverObstacle> obstacles;

    @BeforeEach
    public void beforeRoverTest() {
        mapPointX = new MapPoint(1, 2);
        mapPointY = new MapPoint(2, 9);
        List<RoverObstacle> obstacles = new ArrayList<>();
        roverCoordinates = new RoverCoordinates(mapPointX, mapPointY, direction, obstacles);
        rover = new MarsRover(roverCoordinates);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        Assertions.assertEquals(rover.getCoordinates(), roverCoordinates);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expectedMapPointYPosition = mapPointY.getPosition() + 1;
        rover.receiveCommand('F');
        Assertions.assertEquals(expectedMapPointYPosition, rover.getCoordinates().getMapPointY().getPosition());
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expectedMapPointYPosition = mapPointY.getPosition() - 1;
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
        rover.getCoordinates().setObstacles(Arrays.asList(new RoverObstacle(new BasicCoordinates(mapPointX.getPosition() + 1, mapPointY.getPosition()))));
        rover.getCoordinates().setDirection(RoverDirection.EAST);
        rover.receiveCommand('F');
        Assertions.assertTrue(rover.getPosition().contains("YES"));
    }

    @Test
    public void positionReturnedNOWhenNoObstacleIsFound() throws Exception {
        rover.getCoordinates().setObstacles(Arrays.asList(new RoverObstacle(new BasicCoordinates(mapPointX.getPosition() + 1, mapPointY.getPosition()))));
        rover.getCoordinates().setDirection(RoverDirection.NORTH);
        rover.receiveCommand('F');
        Assertions.assertTrue(rover.getPosition().contains("NO"));
    }

}

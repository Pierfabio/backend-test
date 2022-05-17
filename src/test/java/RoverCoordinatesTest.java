import coordinates.BasicCoordinates;
import coordinates.RoverCoordinates;
import map.MapPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import rover.RoverDirection;
import rover.RoverObstacle;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoverCoordinatesTest {

    private RoverCoordinates coordinates;
    private MapPoint mapPointX;
    private MapPoint mapPointY;
    private List<RoverObstacle> obstacles;
    private final RoverDirection direction = RoverDirection.NORTH;

    @BeforeEach
    public void beforeCoordinatesTest() {
        mapPointX = new MapPoint(1, 99);
        mapPointY = new MapPoint(2, 99);
        obstacles = Arrays.asList(new RoverObstacle(new BasicCoordinates(20,20)), new RoverObstacle(new BasicCoordinates(30,30)));
        coordinates = new RoverCoordinates(mapPointX, mapPointY, direction, obstacles);
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        Assertions.assertEquals(mapPointX, coordinates.getMapPointX());
        Assertions.assertEquals(mapPointY, coordinates.getMapPointY());
    }

    @Test
    public void newInstanceShouldSetDirection() {
        Assertions.assertEquals(direction, coordinates.getDirection());
    }

    @Test
    public void newInstanceShouldSetObstacles() {
        Assertions.assertEquals(obstacles, coordinates.getObstacles());
    }

    @Test
    public void moveForwardShouldIncreaseYWhenDirectionIsNorth() {
        int expectedMapPointYPosition = mapPointY.getPosition() + 1;
        coordinates.setDirection(RoverDirection.NORTH);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointYPosition, coordinates.getMapPointY().getPosition());
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        int expectedMapPointXPosition = mapPointX.getPosition() + 1;
        coordinates.setDirection(RoverDirection.EAST);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointXPosition, coordinates.getMapPointX().getPosition());
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        int expectedMapPointYPosition = mapPointY.getPosition() - 1;
        coordinates.setDirection(RoverDirection.SOUTH);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointYPosition, coordinates.getMapPointY().getPosition());
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        int expectedMapPointXPosition = mapPointX.getPosition() - 1;
        coordinates.setDirection(RoverDirection.WEST);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointXPosition, coordinates.getMapPointX().getPosition());
    }

}

import coordinates.BasicCoordinates;
import coordinates.RoverCoordinates;
import map.MapPlanet;
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
    private List<RoverObstacle> obstacles;
    private final RoverDirection direction = RoverDirection.NORTH;
    private MapPlanet planet;
    private BasicCoordinates roverStartingCoordinate;

    @BeforeEach
    public void beforeCoordinatesTest() {
        obstacles = Arrays.asList(new RoverObstacle(new BasicCoordinates(20,20)), new RoverObstacle(new BasicCoordinates(30,30)));
        planet = new MapPlanet(99,99);
        roverStartingCoordinate = new BasicCoordinates(1, 2);
        coordinates = new RoverCoordinates(planet, roverStartingCoordinate, direction, obstacles);
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        Assertions.assertEquals(roverStartingCoordinate.getX(), coordinates.getMapPointX().getPosition());
        Assertions.assertEquals(roverStartingCoordinate.getY(), coordinates.getMapPointY().getPosition());
        Assertions.assertEquals(planet.getHorizontalSize(), coordinates.getMapPointX().getMaxPosition());
        Assertions.assertEquals(planet.getVerticalSize(), coordinates.getMapPointY().getMaxPosition());
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
        int expectedMapPointYPosition = coordinates.getMapPointY().getPosition() + 1;
        coordinates.setDirection(RoverDirection.NORTH);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointYPosition, coordinates.getMapPointY().getPosition());
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        int expectedMapPointXPosition = coordinates.getMapPointX().getPosition() + 1;
        coordinates.setDirection(RoverDirection.EAST);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointXPosition, coordinates.getMapPointX().getPosition());
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        int expectedMapPointYPosition = coordinates.getMapPointY().getPosition() - 1;
        coordinates.setDirection(RoverDirection.SOUTH);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointYPosition, coordinates.getMapPointY().getPosition());
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        int expectedMapPointXPosition = coordinates.getMapPointX().getPosition() - 1;
        coordinates.setDirection(RoverDirection.WEST);
        coordinates.moveForward();
        Assertions.assertEquals(expectedMapPointXPosition, coordinates.getMapPointX().getPosition());
    }

}

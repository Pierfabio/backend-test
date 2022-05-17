import coordinates.BasicCoordinates;
import map.MapPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapPointTest {

    MapPoint point;
    private int position = 5;
    private int maxPosition = 9;

    @BeforeEach
    public void beforePointTest() {
        point = new MapPoint(position, maxPosition);
    }

    @Test
    public void newInstanceShouldSetPositionAndMaxPosition() {
        Assertions.assertEquals(position, point.getPosition());
        Assertions.assertEquals(maxPosition, point.getMaxPosition());
    }

    @Test
    public void getForwardLocationShouldIncreasePositionValueByOne() {
        Assertions.assertEquals(point.getPosition() + 1, point.getForwardLocation());
    }

    @Test
    public void getBackwardLocationShouldDecreasePositionValueByOne() {
        Assertions.assertEquals(point.getPosition() - 1, point.getBackwardLocation());
    }

    @Test
    public void getForwardLocationShouldSetValueToZeroIfMaxLocationIsPassed() {
        point.setPosition(point.getMaxPosition());
        Assertions.assertEquals(0, point.getForwardLocation());
    }

    @Test
    public void getBackwardLocationShouldSetValueToMaxPositionIfZeroPositionIsPassed() {
        point.setPosition(0);
        Assertions.assertEquals(point.getMaxPosition(), point.getBackwardLocation());
    }
}

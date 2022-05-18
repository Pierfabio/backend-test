package coordinates;

import map.MapPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rover.RoverDirection;
import rover.RoverObstacle;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class RoverCoordinates {

    private MapPoint mapPointX;
    private MapPoint mapPointY;
    private RoverDirection direction;
    private List<RoverObstacle> obstacles;

    private boolean foundObstacle = false;

    public RoverCoordinates(MapPoint mapPointX, MapPoint mapPointY, RoverDirection direction, List<RoverObstacle> obstacles) {
        this.mapPointX = mapPointX;
        this.mapPointY = mapPointY;
        this.direction = direction;
        this.obstacles = obstacles;
    }

    public boolean move(RoverDirection roverDirection) {
        int xPosition = mapPointX.getPosition();
        int yPosition = mapPointY.getPosition();
        switch (roverDirection) {
            case NORTH:
                yPosition = mapPointY.getForwardLocation();
                break;
            case EAST:
                xPosition = mapPointX.getForwardLocation();
                break;
            case SOUTH:
                yPosition = mapPointY.getBackwardLocation();
                break;
            case WEST:
                xPosition = mapPointX.getBackwardLocation();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + roverDirection);
        }
        if (!hasObstacle(xPosition, yPosition)) {
            mapPointX.setPosition(xPosition);
            mapPointY.setPosition(yPosition);
            return true;
        } else {
            return false;
        }
    }

    private boolean hasObstacle(int xLocation, int yLocation) {
        for (RoverObstacle obstacle : obstacles) {
            if (obstacle.getObstacleCoordinates().getX() == xLocation && obstacle.getObstacleCoordinates().getY() == yLocation) {
                foundObstacle = true;
                return true;
            }
        }
        return false;
    }

    public boolean moveForward() {
        return move(direction);
    }

    public boolean moveBackward() {
        return move(direction.getBackwardDirection());
    }

    private void changeDirection(RoverDirection directionValue, int directionStep) {
        int directions = RoverDirection.values().length;
        int index = (directions + directionValue.getValue() + directionStep) % directions;
        direction = RoverDirection.values()[index];
    }

    public void changeDirectionLeft() {
        changeDirection(direction, -1);
    }

    public void changeDirectionRight() {
        changeDirection(direction, 1);
    }

    @Override
    public String toString() {
        String obstacleHit = "NO";
        if (foundObstacle) {
            obstacleHit = "YES";
        }

        return  String.format("Rover in coordinate (%s, %s) direction is %s, contacts with obstacles %s",
                getMapPointX().getPosition(), getMapPointY().getPosition(), getDirection().getName(), obstacleHit);
    }

}

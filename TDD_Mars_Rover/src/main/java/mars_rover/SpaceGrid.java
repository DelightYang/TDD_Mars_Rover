package mars_rover;

import java.util.Collections;
import java.util.List;

import static mars_rover.RoverParameters.*;

class SpaceGrid {

    private List<Coordinate> obstacles = Collections.emptyList();

    SpaceGrid() {
        super();
    }

    SpaceGrid(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }

    Coordinate nextCoordinate(Coordinate coordinate, Direction direction) {
        int y = coordinate.y(), x = coordinate.x();
        if (direction == Direction.NORTH) y = (y + 1) % Y_RANGE;
        if (direction == Direction.EAST) x = (x + 1) % X_RANGE;
        if (direction == Direction.WEST) x = (x > 0) ? x - 1 : X_RANGE - 1;
        if (direction == Direction.SOUTH) y = (y > 0) ? y - 1 : Y_RANGE - 1;
        return processObstacle(coordinate, y, x);
    }


    Coordinate lastCoordinate(Coordinate coordinate, Direction direction) {
        int y = coordinate.y(), x = coordinate.x();
        if (direction == Direction.NORTH) y = (y <= 0) ? Y_RANGE - 1 : y - 1;
        if (direction == Direction.EAST) x = (x <= 0) ? X_RANGE - 1 : x - 1;
        if (direction == Direction.WEST) x = (x > 0) ? X_RANGE - 1 : x + 1;
        if (direction == Direction.SOUTH) y = (y <= 0) ? Y_RANGE - 1 : y - 1;
        return processObstacle(coordinate, y, x);
    }

    private Coordinate processObstacle(Coordinate coordinate, int y, int x) {
        Coordinate newCoordinate = new Coordinate(x, y);
        return obstacles.contains(newCoordinate) ? coordinate : newCoordinate;
    }
}

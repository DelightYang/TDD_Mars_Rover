package mars_rover;

import static mars_rover.RoverParameters.*;

class Rover {

    private SpaceGrid spaceGrid;
    private Direction direction = Direction.NORTH;
    private Coordinate coordinate = new Coordinate(INIT_X, INIT_Y);

    Rover(SpaceGrid spaceGrid) {
        this.spaceGrid = spaceGrid;
    }

    String execute(String commands) {
        executeCommands(commands);
        return coordinate.x() + ":" + coordinate.y() + ":" + direction.value();
    }

    String showInit(String commands) {
        return "Initial Coordinate is x=" + coordinate.x() + " y=" + coordinate.y()
                + " the Range is x=" + X_RANGE + " y=" + Y_RANGE;
    }

    private void executeCommands(String commands) {
        for (char aChar : commands.toCharArray()) {
            if (aChar == 'R') direction = direction.right();
            if (aChar == 'L') direction = direction.left();
            if (aChar == 'M') coordinate = spaceGrid.nextCoordinate(coordinate, direction);
            if (aChar == 'B') coordinate = spaceGrid.lastCoordinate(coordinate, direction);
        }
    }

}

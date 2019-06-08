package mars_rover;

enum Direction {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N");

    private final String value, left, right;

    Direction(String value, String left, String right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Direction right() {
        return directionMatching(right);
    }

    public Direction left() {
        return directionMatching(left);
    }

    public String value() {
        return value;
    }

    private Direction directionMatching(String value) {
        for (Direction direction : values()) {
            if (direction.value.equals(value)) return direction;
        }
        return null;
    }

}

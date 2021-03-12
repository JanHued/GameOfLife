package environment;

public enum Direction {
    NORTH(Position.createPositionAt(0, -1)),
    NORTHEAST(Position.createPositionAt(1, -1)),
    EAST(Position.createPositionAt(1, 0)),
    SOUTHEAST(Position.createPositionAt(1, 1)),
    SOUTH(Position.createPositionAt(0, 1)),
    SOUTHWEST(Position.createPositionAt(-1, 1)),
    WEST(Position.createPositionAt(-1, 0)),
    NORTHWEST(Position.createPositionAt(-1, -1));

    private Position positionalDelta;

    Direction(Position positionalDelta) {
        this.positionalDelta = positionalDelta;
    }

    protected Position getPositionalDelta() {
        return positionalDelta;
    }
}

package environment;

import java.util.Random;

public class Position {
    private final Integer horizontalPosition;
    private final Integer verticalPosition;

    private final static Random rand = new Random();

    private Position(Integer horizontalPosition, Integer verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    protected static Position createPositionAt(Integer horizontalPosition, Integer verticalPosition) {
        return new Position(horizontalPosition, verticalPosition);
    }

    protected Integer getHorizontalPosition() {
        return horizontalPosition;
    }

    protected Integer getVerticalPosition() {
        return verticalPosition;
    }

    protected static Position fetchRandomPosition() {
        return createPositionAt(rand.nextInt(Simulation.simulationSize - 1), rand.nextInt(Simulation.simulationSize - 1));
    }

    protected static Position fetchPositionFromPositionInDirection(Position position, Direction direction) {
        return Position.createPositionAt(position.horizontalPosition + direction.getPositionalDelta().horizontalPosition,
                position.verticalPosition + direction.getPositionalDelta().verticalPosition);
    }
}

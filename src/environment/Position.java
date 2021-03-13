package environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Position {
    private final Integer horizontalPosition;
    private final Integer verticalPosition;

    private final static Random rand = new Random();

    private Position(Integer horizontalPosition, Integer verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    protected static Position createPositionAt(Integer horizontalPosition, Integer verticalPosition) {
        return new Position(mapToPeriodicBoundaries(horizontalPosition), mapToPeriodicBoundaries(verticalPosition));
    }

    private static Integer mapToPeriodicBoundaries(Integer position) {
        if (position < 0) {
            position += Simulation.simulationSize;
        }
        if (position >= Simulation.simulationSize) {
            position -= Simulation.simulationSize;
        }
        return position;
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

    protected static List<Position> fetchAllPositions() {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < Simulation.simulationSize-1; i++) {
            for (int j = 0; j < Simulation.simulationSize-1; j++) {
                result.add(Position.createPositionAt(i, j));
            }
        }
        return result;
    }
}
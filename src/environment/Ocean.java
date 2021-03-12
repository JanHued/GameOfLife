package environment;

import oceanicobjects.Lifeform;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Singleton
public class Ocean {
    private final Integer size;
    private final List<List<Cell>> cells;

    public List<List<Cell>> getCells() {
        return cells;
    }

    private static Ocean instance;

    private Ocean() {
        size = Simulation.simulationSize;
        this.cells = new ArrayList<>();
        IntStream.of(size).forEach(j -> cells.add(
                IntStream.of(size)
                        .mapToObj(i -> Cell.createCellAtPosition(Position.createPositionAt(j, i)))
                        .collect(Collectors.toList()))
        );
        instance = this;
    }

    private static Ocean createOcean() {
        return new Ocean();
    }

    public static Ocean retrieveInstance() {
        return instance == null ? createOcean() : instance;
    }

    public Cell fetchTargetCellOfPositionInDirection(Position position, Direction direction) {
        return fetchCellAtPosition(Position.fetchPositionFromPositionInDirection(position, direction));
    }

    public List<Cell> fetchNeighbourCellsOfPosition(Position position) {
        return Arrays.stream(Direction.values())
                .map(dir -> fetchCellAtPosition(Position.fetchPositionFromPositionInDirection(position, dir)))
                .collect(Collectors.toList());
    }

    public Optional<Cell> fetchEmptyNeighbourCellOfPosition(Position position) {
        return Arrays.stream(Direction.values())
                .map(dir -> fetchCellAtPosition(Position.fetchPositionFromPositionInDirection(position, dir)))
                .filter(Cell::isEmpty)
                .findFirst();
    }

    public Cell fetchCellAtPosition(Position position) {
        if (outOfBounds(position)) {
            return null;
        } else
            return retrieveInstance().cells.get(position.getHorizontalPosition()).get(position.getVerticalPosition());
    }

    private boolean outOfBounds(Position position) {
        if (position.getHorizontalPosition() < 0 || position.getVerticalPosition() < 0) {
            return true;
        }
        return position.getHorizontalPosition() >= size || position.getVerticalPosition() >= size;
    }

    protected void randomlySpreadPlanktonUnits(Integer planktonUnits) {
        IntStream.of(planktonUnits)
                .mapToObj(i -> Position.fetchRandomPosition())
                .forEach(pos -> cells.get(pos.getHorizontalPosition()).get(pos.getVerticalPosition())
                        .addPlanktonUnit());
    }

    public long getNumberOfLifeformsInOcean(Ocean ocean) {
        return ocean.getCells()
                .stream()
                .map(cells -> cells.stream()
                        .map(Cell::getCellContent)
                        .filter(cellContent -> cellContent instanceof Lifeform)
                        .count())
                .reduce(0L, Long::sum);
    }
}

package oceanicobjects;

import environment.Cell;
import environment.Ocean;
import environment.Position;
import environment.Simulation;

import java.util.List;

public class Shark extends Lifeform {
    private Shark(Integer weight, Position position) {
        super(weight, position);
    }

    public static Shark createSharkWeighingAtPosition(Integer weight, Position position) {
        return new Shark(weight, position);
    }

    protected void moveAndEat() {
        Ocean instanceOfOcean = Ocean.retrieveInstance();
        Position currentPosition = this.getPosition();
        List<Cell> neighbourCells = instanceOfOcean.fetchNeighbourCellsOfPosition(position);
        Cell targetCell = choosePreferedCellFromList(neighbourCells);
        if (targetCell == null) {
            targetCell = instanceOfOcean.fetchTargetCellOfPositionInDirection(currentPosition, fetchRandomlyChosenDirection());
        }
        if (possibleToMoveTo(targetCell)) {
            moveFromCellToCellAndEat(instanceOfOcean.fetchCellAtPosition(currentPosition), targetCell);
        }
    }

    private Cell choosePreferedCellFromList(List<Cell> cells) {
        return cells.stream()
                .filter(cell -> cell.getCellContent() instanceof Fish)
                .findFirst()
                .orElse(null);
    }

    protected Boolean possibleToMoveTo(Cell cell) {
        return cell != null && (cell.isEmpty() || cell.getCellContent() instanceof Fish);
    }

    protected void moveFromCellToCellAndEat(Cell currentCell, Cell targetCell) {
        eatFishInCell(targetCell);
        targetCell.enter(this);
        currentCell.leave();
    }

    private void eatFishInCell(Cell cell) {
        CellContent cellContent = cell.getCellContent();
        if (cellContent instanceof Fish) {
            weight += ((Fish) cellContent).weight;
        }
    }

    protected boolean isMature() {
        return weight >= Simulation.breedingWeightOfShark;
    }
}

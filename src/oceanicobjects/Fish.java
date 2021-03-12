package oceanicobjects;

import environment.*;

import java.math.RoundingMode;

public class Fish extends Lifeform {

    private Fish(Integer weight, Position position) {
        super(weight, position);
    }

    public static Fish createFishWeighingAtPosition(Integer weight, Position position) {
        return new Fish(weight, position);
    }

    protected void moveAndEat() {
        Ocean instanceOfOcean = Ocean.retrieveInstance();
        Position currentPosition = this.getPosition();
        Cell targetCell = instanceOfOcean.fetchTargetCellOfPositionInDirection(currentPosition, randomlyChosenDirection());
        if (possibleToMoveTo(targetCell)) {
            moveFromCellToCellAndEat(instanceOfOcean.fetchCellAtPosition(currentPosition), targetCell);
        }
    }

    protected void moveFromCellToCellAndEat(Cell currentCell, Cell targetCell) {
        targetCell.enter(this);
        eatPlanktonUnitsOf(targetCell);
        currentCell.leave();
    }

    private void eatPlanktonUnitsOf(Cell cell) {
        weight += cell.getPlanktonUnits();
        cell.removePlankton();
    }

    protected boolean isMature() {
        return age >= Simulation.breedingTimeOfFish;
    }
}

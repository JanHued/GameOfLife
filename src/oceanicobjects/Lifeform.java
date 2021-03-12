package oceanicobjects;

import environment.*;

import java.util.Random;

public abstract class Lifeform extends CellContent {
    Integer weight;
    Position position;
    boolean timestepDone;

    public boolean timestepDone() {
        return timestepDone;
    }

    public void setTimestepDone(boolean timestepDone) {
        this.timestepDone = timestepDone;
    }

    Random rand = new Random();

    protected Position getPosition() {
        return position;
    }

    protected Lifeform(Integer weight, Position position) {
        this.weight = weight;
        this.position = position;
        timestepDone = true;
    }

    public void doTimestep() {
        if (isMature()) breed();
        if (weight == 0) die();
        moveAndEat();
        timestepDone = true;
        age();
    }

    protected abstract void moveAndEat();

    protected abstract boolean isMature();

    private void breed() {
        Integer weightOfDescendants = Integer.divideUnsigned(weight, 2);
        Ocean oceanInstance = Ocean.retrieveInstance();
        oceanInstance.fetchEmptyNeighbourCellOfPosition(position)
                .ifPresent(cell -> cell.enter(createDescendant(weightOfDescendants, position)));
        oceanInstance.fetchCellAtPosition(position).enter(createDescendant(weightOfDescendants, position));
    }

    private Lifeform createDescendant(Integer weight, Position position) {
        if (this instanceof Fish) {
            return Fish.createFishWeighingAtPosition(weight, position);
        } else {
            return Shark.createSharkWeighingAtPosition(weight, position);
        }
    }

    protected final void age() {
        weight--;
    }

    private void die() {
        Ocean.retrieveInstance().fetchCellAtPosition(position).leave();
    }

    protected abstract void moveFromCellToCellAndEat(Cell currentCell, Cell targetCell);

    Direction fetchRandomlyChosenDirection() {
        return Direction.values()[rand.nextInt(8)];
    }

    protected Boolean possibleToMoveTo(Cell cell) {
        return cell != null && cell.isEmpty();
    }
}

package environment;

import oceanicobjects.CellContent;
import oceanicobjects.Fish;
import oceanicobjects.Rock;
import oceanicobjects.Shark;

public class OceanBuilder {

    private final Ocean ocean;

    private OceanBuilder(Ocean ocean) {
        this.ocean = ocean;
    }

    protected static OceanBuilder buildOcean() {
        return new OceanBuilder(Ocean.retrieveInstance());
    }

    protected OceanBuilder setFish(Integer numberOfFishToDistribute) {
        do {
            Position randomPosition = Position.fetchRandomPosition();
            if (setOceanicObjectToPosition(Fish.createFishWeighingAtPosition(Simulation.initialWeightOfFish, randomPosition), randomPosition)) {
                numberOfFishToDistribute--;
            }
        } while (numberOfFishToDistribute >= 0);
        return this;
    }

    protected OceanBuilder setSharks(Integer numberOfSharksToDistribute) {
        do {
            Position randomPosition = Position.fetchRandomPosition();
            if (setOceanicObjectToPosition(Shark.createSharkWeighingAtPosition(Simulation.initialWeightOfShark, randomPosition), randomPosition)) {
                numberOfSharksToDistribute--;
            }
        } while (numberOfSharksToDistribute >= 0);
        return this;
    }

    protected OceanBuilder setRocks(Integer numberOfRocksToDistribute) {
        do {
            if (setOceanicObjectToPosition(Rock.createRock(), Position.fetchRandomPosition())) {
                numberOfRocksToDistribute--;
            }
        } while (numberOfRocksToDistribute >= 0);
        return this;
    }

    private boolean setOceanicObjectToPosition(CellContent oceanicObject, Position position) {
        Cell cellToFill = ocean.fetchCellAtPosition(position);
        if (cellToFill.isEmpty()) {
            cellToFill.setCellContent(oceanicObject);
            return true;
        }
        return false;
    }

    protected Ocean build(){
        return ocean;
    }
}

package environment;

import oceanicobjects.Lifeform;

import javax.naming.event.ObjectChangeListener;

public class Simulation {
    public final static Integer simulationSize = 100;
    public final static Integer breedingTimeOfFish = 20;
    public final static Integer breedingTimeOfShark = 40;
    public final static Integer initialWeightOfFish = 10;
    public final static Integer initialWeightOfShark = 100;
    public final static Integer maximumNumberOfTimesteps = 1000;

    private final Integer initialNumberOfFish;
    private final Integer initialNumberOfSharks;
    private final Integer initialNumberOfRocks;
    private final Integer planktonUnitsPerTimestep;

    private Integer currentTimestep;


    public Simulation(Integer initialNumberOfFish, Integer initialNumberOfSharks, Integer initialNumberOfRocks,
                      Integer planktonUnitsPerTimestep) {
        this.initialNumberOfFish = initialNumberOfFish;
        this.initialNumberOfSharks = initialNumberOfSharks;
        this.initialNumberOfRocks = initialNumberOfRocks;
        this.planktonUnitsPerTimestep = planktonUnitsPerTimestep;
        this.currentTimestep = 0;
    }

    public void simulate() {
        Ocean ocean = OceanBuilder.buildOcean()
                .setFish(initialNumberOfFish)
                .setSharks(initialNumberOfSharks)
                .setRocks(initialNumberOfRocks)
                .build();
        while (!stopSimulationForOcean(ocean)) {
            currentTimestep++;
            doTimestep(ocean);
        }
    }

    private void doTimestep(Ocean ocean) {
        ocean.randomlySpreadPlanktonUnits(planktonUnitsPerTimestep);
        ocean.getCells().forEach(
                cells -> cells.stream()
                        .map(Cell::getCellContent)
                        .filter(cellContent -> cellContent instanceof Lifeform)
                        .filter(cellContent -> !((Lifeform) cellContent).timestepDone())
                        .forEach(cellContent -> ((Lifeform) cellContent).doTimestep()));

        resetLifeforms(ocean);
    }

    private void resetLifeforms(Ocean ocean) {
        ocean.getCells().forEach(
                cells -> cells.stream()
                        .map(Cell::getCellContent)
                        .filter(cellContent -> cellContent instanceof Lifeform)
                        .forEach(cellContent -> ((Lifeform) cellContent).setTimestepDone(false))
        );
    }

    private boolean stopSimulationForOcean(Ocean ocean) {
        return currentTimestep > maximumNumberOfTimesteps || allLifeEndedInOcean(ocean);
    }

    private boolean allLifeEndedInOcean(Ocean ocean) {
        return ocean.getNumberOfLifeformsInOcean(ocean).equals(0);
    }
}

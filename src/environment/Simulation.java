package environment;

import oceanicobjects.CellContent;
import oceanicobjects.Lifeform;

public class Simulation {
    public final static Integer simulationSize = 20;
    public final static Integer breedingWeightOfFish = 20;
    public final static Integer breedingWeightOfShark = 200;
    public final static Integer initialWeightOfFish = 10;
    public final static Integer initialWeightOfShark = 100;

    private final Integer maximumNumberOfTimesteps;
    private final Integer initialNumberOfFish;
    private final Integer initialNumberOfSharks;
    private final Integer initialNumberOfRocks;
    private final Integer planktonUnitsPerTimestep;

    private Integer currentTimestep;

    public Simulation(SimulationInput simulationInput) {
        this.maximumNumberOfTimesteps = simulationInput.getMaximumNumberOfTimesteps();
        this.initialNumberOfFish = simulationInput.getInitialNumberOfFish();
        this.initialNumberOfSharks = simulationInput.getInitialNumberOfSharks();
        this.initialNumberOfRocks = simulationInput.getInitialNumberOfRocks();
        this.planktonUnitsPerTimestep = simulationInput.getPlanktonUnitsPerTimestep();
        this.currentTimestep = 0;
    }

    public void simulate() {
        Ocean ocean = OceanBuilder.buildOcean()
                .setFish(initialNumberOfFish)
                .setSharks(initialNumberOfSharks)
                .setRocks(initialNumberOfRocks)
                .floodRemainingCells()
                .build();
        while (!stopSimulationForOcean(ocean)) {
            print(ocean);
            currentTimestep++;
            doTimestep(ocean);
        }
    }

    private void print(Ocean ocean) {
        for (int i = 0; i < Simulation.simulationSize - 1; i++) {
            for (int j = 0; j < Simulation.simulationSize - 1; j++) {
                ocean.getCells().get(i).get(j).getCellContent().print();
                System.out.print(" ");
            }
            System.out.println("");
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
        return ocean.getNumberOfLifeforms() == 0;
    }
}

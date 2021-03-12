package environment;

public class SimulationInput {
    private Integer maximumNumberOfTimesteps;
    private Integer initialNumberOfFish;
    private Integer initialNumberOfSharks;
    private Integer initialNumberOfRocks;
    private Integer planktonUnitsPerTimestep;

    public SimulationInput(String args[]) {
        this.maximumNumberOfTimesteps = Integer.parseInt(args[0]);
        this.initialNumberOfFish = Integer.parseInt(args[1]);
        this.initialNumberOfSharks = Integer.parseInt(args[2]);
        this.initialNumberOfRocks = Integer.parseInt(args[3]);
        this.planktonUnitsPerTimestep = Integer.parseInt(args[4]);
    }

    public Integer getMaximumNumberOfTimesteps() {
        return maximumNumberOfTimesteps;
    }

    public void setMaximumNumberOfTimesteps(Integer maximumNumberOfTimesteps) {
        this.maximumNumberOfTimesteps = maximumNumberOfTimesteps;
    }

    public Integer getInitialNumberOfFish() {
        return initialNumberOfFish;
    }

    public void setInitialNumberOfFish(Integer initialNumberOfFish) {
        this.initialNumberOfFish = initialNumberOfFish;
    }

    public Integer getInitialNumberOfSharks() {
        return initialNumberOfSharks;
    }

    public void setInitialNumberOfSharks(Integer initialNumberOfSharks) {
        this.initialNumberOfSharks = initialNumberOfSharks;
    }

    public Integer getInitialNumberOfRocks() {
        return initialNumberOfRocks;
    }

    public void setInitialNumberOfRocks(Integer initialNumberOfRocks) {
        this.initialNumberOfRocks = initialNumberOfRocks;
    }

    public Integer getPlanktonUnitsPerTimestep() {
        return planktonUnitsPerTimestep;
    }

    public void setPlanktonUnitsPerTimestep(Integer planktonUnitsPerTimestep) {
        this.planktonUnitsPerTimestep = planktonUnitsPerTimestep;
    }
}

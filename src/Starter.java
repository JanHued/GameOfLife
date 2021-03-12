import environment.Simulation;
import environment.SimulationInput;

// args =   maximumNumberOfTimesteps;
//          initialNumberOfFish;
//          initialNumberOfSharks;
//          initialNumberOfRocks;
//          planktonUnitsPerTimestep;
public class Starter {
    public static void main(String[] args) {
        args = new String[]{"1", "200", "40", "500", "500"};
        Simulation simulation = new Simulation(new SimulationInput(args));
        simulation.simulate();
    }
}

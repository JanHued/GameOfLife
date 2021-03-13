import environment.Simulation;
import environment.SimulationInput;

// args =   maximumNumberOfTimesteps;
//          initialNumberOfFish;
//          initialNumberOfSharks;
//          initialNumberOfRocks;
//          planktonUnitsPerTimestep;
public class Starter {
    public static void main(String[] args) {
        args = new String[]{"5", "20", "5", "30", "10"};
        Simulation simulation = new Simulation(new SimulationInput(args));
        simulation.simulate();
    }
}

package edu.colorado.locality.routeb.simulation;

import edu.colorado.locality.routeb.behavior.FeedingBehavior;
import edu.colorado.locality.routeb.behavior.MovementBehavior;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;

public class Simulation {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addCreature(new Wolf("Alpha"));
        ecosystem.addCreature(new Rabbit("Bunny"));
        ecosystem.addCreature(new Grass("Patch-1"));

        ecosystem.addBehaviorStep(new MovementBehavior());
        ecosystem.addBehaviorStep(new FeedingBehavior());
        ecosystem.runBehaviorSteps();
        ecosystem.printState();
    }
}

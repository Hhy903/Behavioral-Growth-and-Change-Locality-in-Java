package edu.colorado.locality.simulation;

import edu.colorado.locality.entity.Grass;
import edu.colorado.locality.entity.Rabbit;
import edu.colorado.locality.entity.Wolf;

public class Simulation {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addCreature(new Wolf("Alpha"));
        ecosystem.addCreature(new Rabbit("Bunny"));
        ecosystem.addCreature(new Grass("Patch-1"));

        ecosystem.simulateMovement();
        ecosystem.simulateFeeding();
        ecosystem.simulateReproduction();
        ecosystem.simulateDiseaseSpread();
        ecosystem.simulateAgingAndDeath();
        ecosystem.applySeasonalResourcePolicy();
        ecosystem.advanceSeason();
        ecosystem.printState();
    }
}

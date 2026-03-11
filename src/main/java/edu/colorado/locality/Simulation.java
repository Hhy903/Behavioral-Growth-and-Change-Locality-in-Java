package edu.colorado.locality;

public class Simulation {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        Creature wolf = new Creature("Alpha") {
            @Override
            public String getType() {
                return "Wolf";
            }
        };

        Creature rabbit = new Creature("Bunny") {
            @Override
            public String getType() {
                return "Rabbit";
            }
        };

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        ecosystem.printState();
    }
}
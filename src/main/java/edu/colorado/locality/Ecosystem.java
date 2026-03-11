package edu.colorado.locality;

import java.util.ArrayList;
import java.util.List;

public class Ecosystem {
    private final List<Creature> creatures = new ArrayList<>();

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void printState() {
        for (Creature creature : creatures) {
            System.out.println(creature.getType() + " " + creature.getName()
                    + " alive=" + creature.isAlive());
        }
    }
}
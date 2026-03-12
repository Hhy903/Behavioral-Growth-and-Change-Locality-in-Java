package edu.colorado.locality.simulation;

import edu.colorado.locality.core.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ecosystem {
    private final List<Creature> creatures = new ArrayList<>();

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public List<Creature> getCreatures() {
        return Collections.unmodifiableList(creatures);
    }

    public void simulateFeeding() {
        for (Creature hunter : creatures) {
            if (!hunter.isAlive()) {
                continue;
            }

            for (Creature prey : creatures) {
                if (hunter.feedOn(prey)) {
                    break;
                }
            }
        }
    }

    public void simulateMovement() {
        for (Creature creature : creatures) {
            if (!creature.isAlive()) {
                continue;
            }

            creature.move();
        }
    }

    public void printState() {
        for (Creature creature : creatures) {
            System.out.println(creature.getType() + " " + creature.getName()
                    + " alive=" + creature.isAlive()
                    + " position=(" + creature.getX() + "," + creature.getY() + ")");
        }
    }
}

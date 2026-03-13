package edu.colorado.locality.simulation;

import edu.colorado.locality.core.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ecosystem {
    private final List<Creature> creatures = new ArrayList<>();
    private int offspringCounter = 1;

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

    public void simulateReproduction() {
        List<Creature> newborns = new ArrayList<>();
        boolean[] reproduced = new boolean[creatures.size()];

        for (int i = 0; i < creatures.size(); i++) {
            if (reproduced[i]) {
                continue;
            }

            Creature first = creatures.get(i);
            if (!first.isAlive()) {
                continue;
            }

            for (int j = i + 1; j < creatures.size(); j++) {
                if (reproduced[j]) {
                    continue;
                }

                Creature second = creatures.get(j);
                if (!second.isAlive()) {
                    continue;
                }

                if (!first.canReproduceWith(second)) {
                    continue;
                }

                Creature offspring = first.reproduceWith(second, nextOffspringName(first.getType()));
                if (offspring != null) {
                    newborns.add(offspring);
                    reproduced[i] = true;
                    reproduced[j] = true;
                    break;
                }
            }
        }

        creatures.addAll(newborns);
    }

    public void simulateAgingAndDeath() {
        for (Creature creature : creatures) {
            creature.ageOneStep();
        }
    }

    public void simulateDiseaseSpread() {
        List<Creature> newlyInfected = new ArrayList<>();
        List<Creature> activeCarriers = new ArrayList<>();

        for (Creature creature : creatures) {
            if (creature.isAlive() && creature.isInfected()) {
                activeCarriers.add(creature);
            }
        }

        for (Creature carrier : activeCarriers) {
            for (Creature target : creatures) {
                if (carrier.canInfect(target) && !newlyInfected.contains(target)) {
                    newlyInfected.add(target);
                }
            }
        }

        for (Creature carrier : activeCarriers) {
            carrier.progressDisease();
        }

        for (Creature target : newlyInfected) {
            target.infect();
        }
    }

    public void printState() {
        for (Creature creature : creatures) {
            System.out.println(creature.getType() + " " + creature.getName()
                    + " alive=" + creature.isAlive()
                    + " age=" + creature.getAge()
                    + " infected=" + creature.isInfected()
                    + " position=(" + creature.getX() + "," + creature.getY() + ")");
        }
    }

    private String nextOffspringName(String type) {
        String name = type + "-offspring-" + offspringCounter;
        offspringCounter++;
        return name;
    }
}

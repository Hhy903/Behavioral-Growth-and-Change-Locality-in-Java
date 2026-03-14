package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;

import java.util.ArrayList;
import java.util.List;

public class ReproductionBehavior implements BehaviorStep {
    private int offspringCounter = 1;

    @Override
    public void apply(Ecosystem ecosystem) {
        List<Creature> creatures = ecosystem.getCreatures();
        boolean[] reproduced = new boolean[creatures.size()];
        List<Creature> newborns = new ArrayList<>();

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
                if (!second.isAlive() || !canReproduce(first, second)) {
                    continue;
                }

                newborns.add(createOffspring(first));
                reproduced[i] = true;
                reproduced[j] = true;
                break;
            }
        }

        for (Creature newborn : newborns) {
            ecosystem.addCreature(newborn);
        }
    }

    private boolean canReproduce(Creature first, Creature second) {
        return sameSpecies(first, second) && first.getY() == second.getY()
                && Math.abs(first.getX() - second.getX()) <= 1;
    }

    private boolean sameSpecies(Creature first, Creature second) {
        return first.getClass().equals(second.getClass())
                && (first instanceof Rabbit || first instanceof Wolf);
    }

    private Creature createOffspring(Creature parent) {
        String childName = parent.getType() + "-offspring-" + offspringCounter;
        offspringCounter++;

        if (parent instanceof Rabbit) {
            return new Rabbit(childName, parent.getX(), parent.getY());
        }

        if (parent instanceof Wolf) {
            return new Wolf(childName, parent.getX(), parent.getY());
        }

        throw new IllegalArgumentException("Unsupported parent type: " + parent.getType());
    }
}

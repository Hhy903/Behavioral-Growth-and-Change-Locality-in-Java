package edu.colorado.locality.routeb.simulation;

import edu.colorado.locality.routeb.behavior.BehaviorStep;
import edu.colorado.locality.routeb.core.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ecosystem {
    private final List<Creature> creatures = new ArrayList<>();
    private final List<BehaviorStep> behaviorSteps = new ArrayList<>();

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public List<Creature> getCreatures() {
        return Collections.unmodifiableList(creatures);
    }

    public void addBehaviorStep(BehaviorStep behaviorStep) {
        behaviorSteps.add(behaviorStep);
    }

    public List<BehaviorStep> getBehaviorSteps() {
        return Collections.unmodifiableList(behaviorSteps);
    }

    public void runBehaviorSteps() {
        for (BehaviorStep behaviorStep : behaviorSteps) {
            behaviorStep.apply(this);
        }
    }

    public void printState() {
        for (Creature creature : creatures) {
            System.out.println(creature.getType() + " " + creature.getName()
                    + " alive=" + creature.isAlive());
        }
    }
}

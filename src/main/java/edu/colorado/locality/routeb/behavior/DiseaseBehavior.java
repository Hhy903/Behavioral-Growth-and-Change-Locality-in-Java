package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.simulation.Ecosystem;

import java.util.ArrayList;
import java.util.List;

public class DiseaseBehavior implements BehaviorStep {
    @Override
    public void apply(Ecosystem ecosystem) {
        List<Creature> activeCarriers = new ArrayList<>();
        List<Creature> newlyInfected = new ArrayList<>();

        for (Creature creature : ecosystem.getCreatures()) {
            if (creature.isAlive() && creature.isInfected()) {
                activeCarriers.add(creature);
            }
        }

        for (Creature carrier : activeCarriers) {
            for (Creature target : ecosystem.getCreatures()) {
                if (canInfect(carrier, target) && !newlyInfected.contains(target)) {
                    newlyInfected.add(target);
                }
            }
        }

        for (Creature carrier : activeCarriers) {
            carrier.incrementInfectionAge();
            if (carrier.getInfectionAge() >= carrier.getInfectionDuration()) {
                carrier.die();
            }
        }

        for (Creature target : newlyInfected) {
            target.infect();
        }
    }

    private boolean canInfect(Creature carrier, Creature target) {
        return carrier != target
                && target != null
                && carrier.isAlive()
                && carrier.isInfected()
                && target.isAlive()
                && !target.isInfected()
                && carrier.getY() == target.getY()
                && Math.abs(carrier.getX() - target.getX()) <= 1;
    }
}

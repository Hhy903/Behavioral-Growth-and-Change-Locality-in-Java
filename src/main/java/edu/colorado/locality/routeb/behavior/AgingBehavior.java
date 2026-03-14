package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.simulation.Ecosystem;

public class AgingBehavior implements BehaviorStep {
    @Override
    public void apply(Ecosystem ecosystem) {
        for (Creature creature : ecosystem.getCreatures()) {
            if (!creature.isAlive()) {
                continue;
            }

            creature.incrementAge();
            if (creature.getAge() >= creature.getMaxAge()) {
                creature.die();
            }
        }
    }
}

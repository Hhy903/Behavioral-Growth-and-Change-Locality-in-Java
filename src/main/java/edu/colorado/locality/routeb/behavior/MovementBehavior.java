package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;

public class MovementBehavior implements BehaviorStep {
    @Override
    public void apply(Ecosystem ecosystem) {
        for (Creature creature : ecosystem.getCreatures()) {
            if (!creature.isAlive()) {
                continue;
            }

            if (creature instanceof Wolf) {
                creature.translate(2, 0);
            } else if (creature instanceof Rabbit) {
                creature.translate(1, 0);
            }
        }
    }
}

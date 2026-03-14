package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;

import java.util.List;

public class FeedingBehavior implements BehaviorStep {
    @Override
    public void apply(Ecosystem ecosystem) {
        List<Creature> creatures = ecosystem.getCreatures();

        for (Creature hunter : creatures) {
            if (!hunter.isAlive()) {
                continue;
            }

            for (Creature prey : creatures) {
                if (canFeed(hunter, prey)) {
                    prey.die();
                    break;
                }
            }
        }
    }

    private boolean canFeed(Creature hunter, Creature prey) {
        if (hunter == prey || prey == null || !prey.isAlive()) {
            return false;
        }

        return (hunter instanceof Wolf && prey instanceof Rabbit)
                || (hunter instanceof Rabbit && prey instanceof Grass);
    }
}

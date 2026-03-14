package edu.colorado.locality.routeb.simulation;

import edu.colorado.locality.routeb.behavior.FeedingBehavior;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EcosystemTest {

    @Test
    void ecosystemStoresCreatures() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addCreature(new Wolf("Alpha"));
        ecosystem.addCreature(new Rabbit("Bunny"));
        ecosystem.addCreature(new Grass("Patch-1"));

        assertEquals(3, ecosystem.getCreatures().size());
    }

    @Test
    void ecosystemStoresBehaviorSteps() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addBehaviorStep(eco -> { });

        assertEquals(1, ecosystem.getBehaviorSteps().size());
    }

    @Test
    void ecosystemRunsRegisteredBehaviorSteps() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addBehaviorStep(new FeedingBehavior());

        ecosystem.runBehaviorSteps();

        assertEquals(2, ecosystem.getCreatures().size());
        assertFalse(rabbit.isAlive());
    }
}

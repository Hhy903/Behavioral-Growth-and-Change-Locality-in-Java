package edu.colorado.locality.routeb.simulation;

import edu.colorado.locality.routeb.behavior.FeedingBehavior;
import edu.colorado.locality.routeb.behavior.MovementBehavior;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void ecosystemRunsMovementBeforeFeedingWhenRegisteredInThatOrder() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 5, 0);
        Grass grass = new Grass("Patch-1", 10, 0);

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);
        ecosystem.addBehaviorStep(new MovementBehavior());
        ecosystem.addBehaviorStep(new FeedingBehavior());

        ecosystem.runBehaviorSteps();

        assertEquals(2, wolf.getX());
        assertEquals(6, rabbit.getX());
        assertFalse(rabbit.isAlive());
        assertTrue(grass.isAlive());
    }
}

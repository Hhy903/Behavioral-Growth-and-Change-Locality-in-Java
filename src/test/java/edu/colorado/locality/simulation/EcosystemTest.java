package edu.colorado.locality.simulation;

import edu.colorado.locality.entity.Grass;
import edu.colorado.locality.entity.Rabbit;
import edu.colorado.locality.entity.Wolf;
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

        assertEquals(2, ecosystem.getCreatures().size());
    }

    @Test
    void feedingStepUsesCurrentStateInInsertionOrder() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(wolf.isAlive());
        assertFalse(rabbit.isAlive());
        assertTrue(grass.isAlive());
    }

    @Test
    void feedingStepLetsRabbitEatGrassWhenPredatorIsAbsent() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(rabbit.isAlive());
        assertFalse(grass.isAlive());
    }

    @Test
    void feedingStepDoesNotAffectUnsupportedPrey() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(wolf.isAlive());
        assertTrue(grass.isAlive());
    }
}

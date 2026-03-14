package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedingBehaviorTest {

    @Test
    void feedingBehaviorLetsWolfEatRabbit() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        new FeedingBehavior().apply(ecosystem);

        assertTrue(wolf.isAlive());
        assertFalse(rabbit.isAlive());
    }

    @Test
    void feedingBehaviorLetsRabbitEatGrassWhenPredatorIsAbsent() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        new FeedingBehavior().apply(ecosystem);

        assertTrue(rabbit.isAlive());
        assertFalse(grass.isAlive());
    }

    @Test
    void feedingBehaviorDoesNotAffectUnsupportedPrey() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(grass);

        new FeedingBehavior().apply(ecosystem);

        assertTrue(wolf.isAlive());
        assertTrue(grass.isAlive());
    }

    @Test
    void feedingBehaviorUsesCurrentStateInInsertionOrder() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        new FeedingBehavior().apply(ecosystem);

        assertTrue(wolf.isAlive());
        assertFalse(rabbit.isAlive());
        assertTrue(grass.isAlive());
    }

    @Test
    void feedingBehaviorLeavesDeadCreaturesUntouched() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        rabbit.die();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        new FeedingBehavior().apply(ecosystem);

        assertFalse(rabbit.isAlive());
    }
}

package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgingBehaviorTest {

    @Test
    void agingBehaviorAdvancesAgeForLivingCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        new AgingBehavior().apply(ecosystem);

        assertEquals(1, wolf.getAge());
        assertEquals(1, rabbit.getAge());
        assertEquals(1, grass.getAge());
    }

    @Test
    void agingBehaviorKillsCreatureThatReachesMaxAge() {
        Ecosystem ecosystem = new Ecosystem();
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(grass);

        new AgingBehavior().apply(ecosystem);
        new AgingBehavior().apply(ecosystem);
        new AgingBehavior().apply(ecosystem);

        assertEquals(3, grass.getAge());
        assertFalse(grass.isAlive());
    }

    @Test
    void agingBehaviorSkipsDeadCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny");
        rabbit.die();

        ecosystem.addCreature(rabbit);

        new AgingBehavior().apply(ecosystem);

        assertEquals(0, rabbit.getAge());
        assertFalse(rabbit.isAlive());
    }

    @Test
    void routeBEntitiesUseSpeciesSpecificMaxAge() {
        assertEquals(3, new Grass("Patch-1").getMaxAge());
        assertEquals(5, new Rabbit("Bunny").getMaxAge());
        assertEquals(8, new Wolf("Alpha").getMaxAge());
    }
}

package edu.colorado.locality.simulation;

import edu.colorado.locality.entity.Rabbit;
import edu.colorado.locality.entity.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcosystemTest {

    @Test
    void ecosystemStoresCreatures() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addCreature(new Wolf("Alpha"));
        ecosystem.addCreature(new Rabbit("Bunny"));

        assertEquals(2, ecosystem.getCreatures().size());
    }
}
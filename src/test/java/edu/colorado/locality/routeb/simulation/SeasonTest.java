package edu.colorado.locality.routeb.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasonTest {

    @Test
    void seasonCyclesInFixedOrder() {
        assertEquals(Season.SUMMER, Season.SPRING.next());
        assertEquals(Season.AUTUMN, Season.SUMMER.next());
        assertEquals(Season.WINTER, Season.AUTUMN.next());
        assertEquals(Season.SPRING, Season.WINTER.next());
    }
}

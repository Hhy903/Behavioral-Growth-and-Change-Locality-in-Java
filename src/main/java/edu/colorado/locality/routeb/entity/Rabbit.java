package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Rabbit extends Creature {
    private static final int MAX_AGE = 5;

    public Rabbit(String name) {
        super(name, 0, 0, MAX_AGE);
    }

    public Rabbit(String name, int x, int y) {
        super(name, x, y, MAX_AGE);
    }

    @Override
    public String getType() {
        return "Rabbit";
    }
}

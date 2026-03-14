package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Wolf extends Creature {
    private static final int MAX_AGE = 8;

    public Wolf(String name) {
        super(name, 0, 0, MAX_AGE);
    }

    public Wolf(String name, int x, int y) {
        super(name, x, y, MAX_AGE);
    }

    @Override
    public String getType() {
        return "Wolf";
    }
}

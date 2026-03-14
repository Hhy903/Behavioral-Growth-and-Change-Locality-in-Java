package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Wolf extends Creature {
    public Wolf(String name) {
        super(name);
    }

    public Wolf(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public String getType() {
        return "Wolf";
    }
}

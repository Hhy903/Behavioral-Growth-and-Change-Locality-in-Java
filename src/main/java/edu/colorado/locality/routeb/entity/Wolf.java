package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Wolf extends Creature {
    public Wolf(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Wolf";
    }
}

package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

public class Wolf extends Creature {
    public Wolf(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Wolf";
    }

    @Override
    public boolean canEat(Creature other) {
        return other instanceof Rabbit;
    }
}

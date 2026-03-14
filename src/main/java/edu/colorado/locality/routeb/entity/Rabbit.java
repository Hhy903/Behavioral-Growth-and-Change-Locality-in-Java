package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Rabbit extends Creature {
    public Rabbit(String name) {
        super(name);
    }

    public Rabbit(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public String getType() {
        return "Rabbit";
    }
}

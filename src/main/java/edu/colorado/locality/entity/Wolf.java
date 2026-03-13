package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

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

    @Override
    public void move() {
        moveBy(2, 0);
    }

    @Override
    public boolean canEat(Creature other) {
        return other instanceof Rabbit;
    }

    @Override
    public boolean canReproduceWith(Creature other) {
        return other instanceof Wolf && isAdjacentTo(other);
    }

    @Override
    public Creature reproduceWith(Creature other, String childName) {
        if (!canReproduceWith(other) || !isAlive() || !other.isAlive()) {
            return null;
        }

        return new Wolf(childName, getX(), getY());
    }
}

package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

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

    @Override
    public void move() {
        moveBy(1, 0);
    }

    @Override
    public boolean canEat(Creature other) {
        return other instanceof Grass;
    }

    @Override
    public boolean canReproduceWith(Creature other) {
        return other instanceof Rabbit && isAdjacentTo(other);
    }

    @Override
    public Creature reproduceWith(Creature other, String childName) {
        if (!canReproduceWith(other) || !isAlive() || !other.isAlive()) {
            return null;
        }

        return new Rabbit(childName, getX(), getY());
    }
}

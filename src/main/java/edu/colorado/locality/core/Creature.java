package edu.colorado.locality.core;

public abstract class Creature {
    protected String name;
    protected boolean alive;
    protected int x;
    protected int y;

    public Creature(String name) {
        this(name, 0, 0);
    }

    public Creature(String name, int x, int y) {
        this.name = name;
        this.alive = true;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void die() {
        this.alive = false;
    }

    public void move() {
        // Default creatures do not move.
    }

    public boolean canEat(Creature other) {
        return false;
    }

    public boolean feedOn(Creature other) {
        if (!alive || other == null || other == this || !other.isAlive() || !canEat(other)) {
            return false;
        }

        other.die();
        return true;
    }

    public boolean canReproduceWith(Creature other) {
        return false;
    }

    public Creature reproduceWith(Creature other, String childName) {
        if (!alive || other == null || other == this || !other.isAlive() || !canReproduceWith(other)) {
            return null;
        }

        return null;
    }

    protected void moveBy(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    protected boolean isAdjacentTo(Creature other) {
        return this.y == other.y && Math.abs(this.x - other.x) <= 1;
    }

    public abstract String getType();
}

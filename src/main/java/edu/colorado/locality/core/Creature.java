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

    protected void moveBy(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public abstract String getType();
}

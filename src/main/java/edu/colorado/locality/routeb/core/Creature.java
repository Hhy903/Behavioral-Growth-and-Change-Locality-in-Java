package edu.colorado.locality.routeb.core;

public abstract class Creature {
    private final String name;
    private boolean alive;
    private int x;
    private int y;
    private int age;
    private int maxAge;
    private boolean infected;
    private int infectionAge;
    private int infectionDuration;

    private static final int DEFAULT_INFECTION_DURATION = 2;

    protected Creature(String name) {
        this(name, 0, 0, Integer.MAX_VALUE);
    }

    protected Creature(String name, int x, int y) {
        this(name, x, y, Integer.MAX_VALUE);
    }

    protected Creature(String name, int x, int y, int maxAge) {
        this.name = name;
        this.alive = true;
        this.x = x;
        this.y = y;
        this.age = 0;
        this.maxAge = maxAge;
        this.infected = false;
        this.infectionAge = 0;
        this.infectionDuration = DEFAULT_INFECTION_DURATION;
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

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public boolean isInfected() {
        return infected;
    }

    public int getInfectionAge() {
        return infectionAge;
    }

    public int getInfectionDuration() {
        return infectionDuration;
    }

    public void die() {
        this.alive = false;
    }

    public void translate(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void incrementAge() {
        this.age++;
    }

    public boolean infect() {
        if (!alive || infected) {
            return false;
        }

        this.infected = true;
        this.infectionAge = 0;
        return true;
    }

    public void incrementInfectionAge() {
        this.infectionAge++;
    }

    public abstract String getType();
}

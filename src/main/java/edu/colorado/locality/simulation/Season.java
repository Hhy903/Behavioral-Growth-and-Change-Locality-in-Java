package edu.colorado.locality.simulation;

public enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    public Season next() {
        return switch (this) {
            case SPRING -> SUMMER;
            case SUMMER -> AUTUMN;
            case AUTUMN -> WINTER;
            case WINTER -> SPRING;
        };
    }
}

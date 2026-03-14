package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.simulation.Ecosystem;

public interface BehaviorStep {
    void apply(Ecosystem ecosystem);
}

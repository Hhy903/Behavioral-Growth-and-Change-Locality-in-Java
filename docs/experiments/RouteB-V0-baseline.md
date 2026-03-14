# Route B V0 Baseline

## Scope

This document establishes the baseline architecture for Route B before reintroducing the behavioral growth sequence from V1 to V6.

- Architectural route: Route B
- Baseline version: V0-B
- Goal: behavior-modularized growth with package-level separation from Route A

## Package Structure

- `edu.colorado.locality.routeb.core`
- `edu.colorado.locality.routeb.entity`
- `edu.colorado.locality.routeb.behavior`
- `edu.colorado.locality.routeb.simulation`

## Baseline Design

- `Creature` remains a minimal state holder with name and liveness
- Concrete species remain simple identity-bearing entities
- `BehaviorStep` defines the extension point for future behavioral modules
- `Ecosystem` stores creatures and an ordered list of behavior steps
- `Simulation` runs the registered behavior steps and prints state

## Rationale

Route B starts from the same conceptual baseline as Route A, but introduces an explicit behavior boundary before behavioral growth begins. This is the central architectural difference to be evaluated in later phases.

## Expected Experimental Benefit

- Future behaviors can be added as modules rather than being embedded directly into entity classes
- Round-level bookkeeping can be localized inside behavior implementations
- `Ecosystem` can remain a stable coordinator rather than absorbing all policy logic

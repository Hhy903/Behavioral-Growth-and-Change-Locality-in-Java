# Route B V2 Movement

## Scope

This document records the second behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V2-B
- Behavior introduced: Movement

## Modified Source Files

Count: 6

- `src/main/java/edu/colorado/locality/routeb/core/Creature.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Grass.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Rabbit.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Wolf.java`
- `src/main/java/edu/colorado/locality/routeb/behavior/MovementBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 3

- `src/test/java/edu/colorado/locality/routeb/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/routeb/behavior/MovementBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 6

- `Creature`
- `Grass`
- `Rabbit`
- `Wolf`
- `MovementBehavior`
- `Simulation`

Test classes changed: 3

- `CreatureTest`
- `MovementBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 9

## Layers Affected

Count: 4

- Core layer
- Entity layer
- Behavior layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: shared core state coupling
- Secondary coupling: behavior-module update coupling

Movement still requires introducing shared spatial state into the base creature model, but species-specific movement rules remain concentrated in a behavior module rather than being distributed across entity classes.

## New Coordination Rules

Count: 3

- Only living creatures participate in movement
- Movement executes once per creature per round
- Species-specific deltas are dispatched from `MovementBehavior`

## Control-Flow Complexity

- Classification: per-entity deterministic update
- Shape: single pass over living creatures inside a dedicated behavior module

Compared to Route A V2, the control-flow pattern is similar, but the movement policy remains localized in the behavior layer instead of being split between core, entity, and simulation behavior methods.

## State Mutation Scope

- Shared `x/y` state is added to all Route B creatures
- `MovementBehavior` mutates coordinates of living wolves and rabbits
- `Grass` retains only state, without movement logic
- No lifecycle or population-size mutations are introduced at this phase

## Temporary Round-Level Bookkeeping

- None

Movement requires no temporary round data structures in Route B, and the ecosystem remains a generic dispatcher.

## Route B Locality Observation

Route B V2 still needs to touch the core/entity model because movement depends on shared spatial state, but it avoids spreading species movement rules into the entity layer. This makes the state expansion visible while containing behavior growth inside a dedicated module.

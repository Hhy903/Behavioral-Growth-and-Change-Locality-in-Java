# Route B V4 Aging and Death

## Scope

This document records the fourth behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V4-B
- Behavior introduced: Aging and death

## Modified Source Files

Count: 6

- `src/main/java/edu/colorado/locality/routeb/core/Creature.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Grass.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Rabbit.java`
- `src/main/java/edu/colorado/locality/routeb/entity/Wolf.java`
- `src/main/java/edu/colorado/locality/routeb/behavior/AgingBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 3

- `src/test/java/edu/colorado/locality/routeb/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/routeb/behavior/AgingBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 6

- `Creature`
- `Grass`
- `Rabbit`
- `Wolf`
- `AgingBehavior`
- `Simulation`

Test classes changed: 3

- `CreatureTest`
- `AgingBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 9

## Layers Affected

Count: 4

- Core layer
- Entity layer
- Behavior layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: shared lifecycle-state coupling
- Secondary coupling: behavior-module mortality coupling

Like Route A V4, Route B must introduce lifecycle state into the shared core model. The difference is that the death threshold policy remains localized in `AgingBehavior` instead of becoming a direct responsibility of the ecosystem or entity behavior API.

## New Coordination Rules

Count: 5

- Every living creature ages once per aging round
- Creatures die automatically when `age >= maxAge`
- Dead creatures no longer age further
- Species-specific lifespans are enforced through entity constructors
- Aging and death are coordinated by a dedicated behavior module

## Control-Flow Complexity

- Classification: global lifecycle sweep
- Shape: full-pass age update with threshold-triggered death inside a behavior module

Compared to Route A V4, the control-flow pattern is similar, but the ecosystem remains a generic dispatcher rather than absorbing the aging policy directly.

## State Mutation Scope

- Shared `age` and `maxAge` state is introduced into all Route B creatures
- `AgingBehavior` mutates age for living creatures
- `alive` state can now change due to lifespan exhaustion
- Entity classes only encode species-specific lifespan constants

## Temporary Round-Level Bookkeeping

- None

Aging requires no temporary round data structures in Route B, and the lifecycle sweep stays localized in the behavior layer.

## Route B Locality Observation

Route B V4 shows a mixed result: shared lifecycle state still forces broad model changes, but mortality policy does not spread into Route B entities or the Route B ecosystem coordinator. This phase is useful because it separates unavoidable state coupling from avoidable behavior diffusion.

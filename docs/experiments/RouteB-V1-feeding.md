# Route B V1 Feeding

## Scope

This document records the first behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V1-B
- Behavior introduced: Feeding

## Modified Source Files

Count: 2

- `src/main/java/edu/colorado/locality/routeb/behavior/FeedingBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 2

- `src/test/java/edu/colorado/locality/routeb/behavior/FeedingBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 2

- `FeedingBehavior`
- `Simulation`

Test classes changed: 2

- `FeedingBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 4

## Layers Affected

Count: 3

- Behavior layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: behavior-module interaction coupling
- Secondary coupling: simulation-to-behavior registration coupling

The feeding rules are no longer embedded in entity classes. Coupling is concentrated inside a single behavior module, while entities remain simple state holders.

## New Coordination Rules

Count: 4

- Only living creatures may act as hunters
- A creature cannot feed on itself
- Only compatible prey types can be consumed
- A hunter stops after its first successful feeding in a round

## Control-Flow Complexity

- Classification: single-action interaction
- Shape: nested iteration with early exit on successful consumption inside a behavior module

Compared to Route A V1, the control flow remains similar, but its implementation is localized inside `FeedingBehavior` rather than split across entity and simulation layers.

## State Mutation Scope

- Prey `alive` state changes from `true` to `false`
- No shared spatial or lifecycle state is introduced at this phase
- Entities themselves do not gain new feeding methods

## Temporary Round-Level Bookkeeping

- None

The behavior executes directly over the ecosystem creature list without temporary round data structures.

## Route B Locality Observation

Relative to Route A V1, the same feeding behavior is introduced by modifying fewer architectural areas. The entity layer remains unchanged, which is the first concrete signal that Route B can preserve locality by concentrating behavior changes inside dedicated modules.

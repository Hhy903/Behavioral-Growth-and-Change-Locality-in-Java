# Route B V5 Disease

## Scope

This document records the fifth behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V5-B
- Behavior introduced: Disease spread

## Modified Source Files

Count: 3

- `src/main/java/edu/colorado/locality/routeb/core/Creature.java`
- `src/main/java/edu/colorado/locality/routeb/behavior/DiseaseBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 3

- `src/test/java/edu/colorado/locality/routeb/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/routeb/behavior/DiseaseBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 3

- `Creature`
- `DiseaseBehavior`
- `Simulation`

Test classes changed: 3

- `CreatureTest`
- `DiseaseBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 6

## Layers Affected

Count: 4

- Core layer
- Behavior layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: cross-entity propagation coupling
- Secondary coupling: lifecycle-state and behavior-module orchestration coupling

Route B still needs shared infection state in the core model, but cross-species contagion, carrier snapshots, and deferred application are concentrated inside `DiseaseBehavior` instead of being promoted to ecosystem-level permanent logic.

## New Coordination Rules

Count: 6

- Only living infected creatures can act as carriers
- Infection can spread across species boundaries
- Infection requires adjacency
- Newly infected creatures are applied after the spread scan completes
- Only carriers active at round start progress disease during that round
- Infection can kill creatures when disease duration is exhausted

## Control-Flow Complexity

- Classification: propagation plus deferred infection application
- Shape: carrier snapshot, nested contact scan, deferred state application, then disease progression inside a behavior module

Compared to Route A V5, the complexity is similar, but Route B keeps the propagation logic inside a dedicated module rather than expanding the generic ecosystem coordinator.

## State Mutation Scope

- Shared infection state is introduced into all Route B creatures
- `DiseaseBehavior` mutates infection age for active carriers
- `alive` state can now change due to disease
- Infection status can spread across multiple existing objects in one round
- Entities themselves do not gain disease propagation methods

## Temporary Round-Level Bookkeeping

- `List<Creature> activeCarriers` snapshots infected carriers at round start
- `List<Creature> newlyInfected` defers infection application until after the spread scan

Route B still needs explicit round-level bookkeeping for disease spread, but the bookkeeping is localized in `DiseaseBehavior` rather than being added as generic ecosystem state.

## Route B Locality Observation

Route B V5 demonstrates the same pattern seen in V3: structural complexity continues to rise, but the diffusion path is narrower. Shared infection state still forces a core-model change, yet the contagious interaction policy remains encapsulated in one behavior module.

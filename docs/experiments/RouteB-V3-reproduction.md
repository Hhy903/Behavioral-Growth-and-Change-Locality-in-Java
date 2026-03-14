# Route B V3 Reproduction

## Scope

This document records the third behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V3-B
- Behavior introduced: Reproduction

## Modified Source Files

Count: 2

- `src/main/java/edu/colorado/locality/routeb/behavior/ReproductionBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 2

- `src/test/java/edu/colorado/locality/routeb/behavior/ReproductionBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 2

- `ReproductionBehavior`
- `Simulation`

Test classes changed: 2

- `ReproductionBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 4

## Layers Affected

Count: 3

- Behavior layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: lifecycle and orchestration coupling
- Secondary coupling: spatial precondition coupling

Reproduction still requires pair matching and deferred newborn insertion, but in Route B those coordination rules are localized inside a dedicated behavior rather than being merged into the ecosystem core or entity APIs.

## New Coordination Rules

Count: 6

- Both participants must be alive
- Both participants must be of the same species
- Reproduction requires adjacency on the same `y` coordinate
- Each creature can reproduce at most once per round
- Newborn creatures are delayed until the end of the round
- Pair matching follows insertion-order scanning

## Control-Flow Complexity

- Classification: pair matching plus deferred insertion
- Shape: nested pair search with participation guards and post-round append inside a behavior module

Compared to Route A V3, the control-flow pattern is similar, but the bookkeeping is isolated inside `ReproductionBehavior` instead of expanding the permanent responsibilities of `Ecosystem`.

## State Mutation Scope

- New creature objects are created
- Ecosystem population size changes when newborns are appended
- Offspring naming state is localized inside the behavior module
- Existing creature positions are reused as reproduction preconditions
- Entities themselves do not gain reproduction methods

## Temporary Round-Level Bookkeeping

- `boolean[] reproduced` tracks which creatures have already participated in the current round
- `List<Creature> newborns` accumulates offspring for deferred insertion

The critical difference from Route A is not the absence of bookkeeping, but the fact that bookkeeping is encapsulated inside a dedicated behavior rather than pushed into the generic ecosystem coordinator.

## Route B Locality Observation

Route B V3 is the strongest early demonstration of locality preservation: the same reproduction semantics can be introduced without modifying Route B entities or the Route B ecosystem abstraction. Behavioral growth still increases complexity, but the complexity stays largely inside a single module.

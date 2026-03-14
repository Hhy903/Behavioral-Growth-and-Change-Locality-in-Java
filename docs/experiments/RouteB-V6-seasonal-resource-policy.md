# Route B V6 Seasonal Resource Policy

## Scope

This document records the sixth behavioral-growth step for Route B.

- Architectural route: Route B
- Version: V6-B
- Behavior introduced: Seasonal resource policy

## Modified Source Files

Count: 4

- `src/main/java/edu/colorado/locality/routeb/simulation/Season.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Ecosystem.java`
- `src/main/java/edu/colorado/locality/routeb/behavior/SeasonalResourceBehavior.java`
- `src/main/java/edu/colorado/locality/routeb/simulation/Simulation.java`

## Modified Test Files

Count: 3

- `src/test/java/edu/colorado/locality/routeb/simulation/SeasonTest.java`
- `src/test/java/edu/colorado/locality/routeb/behavior/SeasonalResourceBehaviorTest.java`
- `src/test/java/edu/colorado/locality/routeb/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 4

- `Season`
- `Ecosystem`
- `SeasonalResourceBehavior`
- `Simulation`

Test classes changed: 3

- `SeasonTest`
- `SeasonalResourceBehaviorTest`
- `EcosystemTest`

Total classes touched in this phase: 7

## Layers Affected

Count: 3

- Simulation layer
- Behavior layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: system-level policy coupling
- Secondary coupling: behavior-module resource policy coupling

Route B also needs an explicit global seasonal mode, but the season-dependent resource rules are placed in `SeasonalResourceBehavior` rather than being hard-coded into the ecosystem coordinator itself.

## New Coordination Rules

Count: 6

- The ecosystem maintains a global current season
- Seasons advance in a fixed cycle
- Spring adds two grass resources
- Summer adds one grass resource
- Autumn adds no new grass resources
- Winter ages living grass as a scarcity policy

## Control-Flow Complexity

- Classification: global mode-dependent policy dispatch
- Shape: season switch with resource creation, winter aging, and explicit season advancement inside a behavior module

Compared to Route A V6, Route B still centralizes global mode state in the simulation layer, but it prevents the policy rules themselves from becoming permanent logic inside the ecosystem coordinator.

## State Mutation Scope

- Ecosystem-level `currentSeason` state is introduced
- Seasonal behavior can create new grass objects
- Winter policy mutates lifecycle state of existing grass
- Seasonal behavior advances the global season after applying policy

## Temporary Round-Level Bookkeeping

- None

Seasonal policy does not require temporary round data structures in Route B, but it does add durable global control state in the simulation layer.

## Route B Locality Observation

Route B V6 shows the practical limit of modularization in this experiment: global policy state still requires simulation-level support. Even so, the actual season-dependent rules remain localized inside `SeasonalResourceBehavior`, which keeps the ecosystem abstraction leaner than in Route A.

# V5 Disease Spread Experimental Record (Route A)

## Scope

This record captures the fifth behavioral-growth step after the V4 aging and death version:

- Version: V5
- Behavior introduced: Disease spread
- Architectural route: Route A, naive object-oriented growth

## Modified Source Files

Count: 3

- `src/main/java/edu/colorado/locality/core/Creature.java`
- `src/main/java/edu/colorado/locality/simulation/Ecosystem.java`
- `src/main/java/edu/colorado/locality/simulation/Simulation.java`

## Modified Test Files

Count: 3

- `src/test/java/edu/colorado/locality/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/entity/GrassTest.java`
- `src/test/java/edu/colorado/locality/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 3

- `Creature`
- `Ecosystem`
- `Simulation`

Test classes changed: 3

- `CreatureTest`
- `GrassTest`
- `EcosystemTest`

Total classes touched in this phase: 6

## Layers Affected

Count: 3

- Core layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: cross-entity propagation coupling
- Secondary coupling: lifecycle-state and orchestration coupling

Disease introduces contagious state that can propagate across species boundaries through spatial adjacency. It also couples infection progression to mortality, making the lifecycle model more entangled with round-level simulation policy.

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
- Shape: carrier snapshot, nested contact scan, deferred state application, then disease progression

Compared to V4, disease spread reintroduces round-level coordination complexity similar to reproduction, but with broader cross-entity interaction semantics.

## State Mutation Scope

- Shared infection state is introduced into every creature
- Infection age progresses over time for active carriers
- `alive` state can now change due to disease in addition to predation and aging
- Infection status can spread across multiple existing objects in one round
- Simulation output now reflects infection status

## Temporary Round-Level Bookkeeping

- `List<Creature> activeCarriers` snapshots infected carriers at round start
- `List<Creature> newlyInfected` defers infection application until after the spread scan

The disease round requires explicit temporary coordination state to separate current carriers from newly infected creatures and prevent same-round chain propagation.

## Disease Rules Implemented

- All creature types are susceptible to infection
- Infection spreads to adjacent living creatures regardless of species
- Infection duration is `2` rounds
- Infected creatures die when `infectionAge >= infectionDuration`
- Dead creatures cannot infect others or become infected
- Newly infected creatures start with `infectionAge = 0`

## Execution Semantics

The current `Ecosystem.simulateDiseaseSpread()` implementation first snapshots infected living carriers, then collects adjacent susceptible targets, progresses disease on the original carriers, and finally applies infection to the deferred target list. This preserves deterministic round behavior while increasing cross-entity interaction complexity in the naive Route A design.

## Behavioral Impact Summary

Disease introduces a cross-cutting propagation behavior that depends on previously added lifecycle and spatial state.  
Unlike aging, which performs an independent full-pass lifecycle update, disease couples creatures through proximity relationships and delayed infection application.

This makes the simulation layer responsible not only for executing behavior rounds, but also for preserving propagation semantics across shared state dimensions.

## Locality Observation

Compared to previous phases, disease is expected to be one of the strongest cross-cutting growth steps in Route A.  
Its implementation reuses and couples multiple existing concerns, including liveness, position, and per-round execution ordering.

This phase therefore provides a stronger test of whether naive object-oriented growth can preserve change locality once behaviors begin interacting across previously introduced dimensions.
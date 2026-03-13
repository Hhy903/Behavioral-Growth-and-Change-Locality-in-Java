# V1 Feeding Experimental Record (Route A)

## Scope

This record captures the first behavioral-growth step after the V0 baseline:

- Version: V1
- Behavior introduced: Feeding
- Architectural route: Route A, naive object-oriented growth

## Modified Source Files

Count: 5

- `src/main/java/edu/colorado/locality/core/Creature.java`
- `src/main/java/edu/colorado/locality/entity/Rabbit.java`
- `src/main/java/edu/colorado/locality/entity/Wolf.java`
- `src/main/java/edu/colorado/locality/simulation/Ecosystem.java`
- `src/main/java/edu/colorado/locality/simulation/Simulation.java`

## Modified Test Files

Count: 4

- `src/test/java/edu/colorado/locality/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/entity/RabbitTest.java`
- `src/test/java/edu/colorado/locality/entity/WolfTest.java`
- `src/test/java/edu/colorado/locality/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 5

- `Creature`
- `Rabbit`
- `Wolf`
- `Ecosystem`
- `Simulation`

Test classes changed or added: 4

- `CreatureTest`
- `RabbitTest`
- `WolfTest`
- `EcosystemTest`

Total classes touched in this phase: 9

## Layers Affected

Count: 4

- Core layer
- Entity layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: entity-to-entity interaction
- Secondary coupling: simulation-to-entity orchestration

The feeding behavior couples one creature's action to another creature's liveness state. The simulation layer coordinates turn execution but does not yet require additional round-specific policy state.

## New Coordination Rules

Count: 4

- Only living creatures may act as hunters
- A creature cannot feed on itself
- Only compatible prey types can be consumed
- A hunter stops after its first successful feeding in a round

## Control-Flow Complexity

- Classification: single-action interaction
- Shape: nested iteration with early exit on successful consumption

The feeding round is still structurally simple: each creature attempts one local interaction, and control returns immediately after a successful feed.

## State Mutation Scope

- Prey `alive` state changes from `true` to `false`
- No spatial state changes
- No population-size changes
- No round-level bookkeeping structures are introduced

## Temporary Round-Level Bookkeeping

- None

The simulation layer executes the feeding round directly over the creature list without temporary coordination state.

## Feeding Rules Implemented

- `Rabbit` can feed on `Grass`
- `Wolf` can feed on `Rabbit`
- Unsupported prey remains alive
- Dead creatures cannot feed

## Execution Semantics

The current `Ecosystem.simulateFeeding()` implementation executes a sequential feeding pass in insertion order. Each creature acts against the current ecosystem state rather than a simultaneous turn snapshot.

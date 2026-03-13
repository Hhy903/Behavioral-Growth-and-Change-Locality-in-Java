# V4 Aging and Death Experimental Record (Route A)

## Scope

This record captures the fourth behavioral-growth step after the V3 reproduction version:

- Version: V4
- Behavior introduced: Aging and death
- Architectural route: Route A, naive object-oriented growth

## Modified Source Files

Count: 6

- `src/main/java/edu/colorado/locality/core/Creature.java`
- `src/main/java/edu/colorado/locality/entity/Grass.java`
- `src/main/java/edu/colorado/locality/entity/Rabbit.java`
- `src/main/java/edu/colorado/locality/entity/Wolf.java`
- `src/main/java/edu/colorado/locality/simulation/Ecosystem.java`
- `src/main/java/edu/colorado/locality/simulation/Simulation.java`

## Modified Test Files

Count: 5

- `src/test/java/edu/colorado/locality/core/CreatureTest.java`
- `src/test/java/edu/colorado/locality/entity/GrassTest.java`
- `src/test/java/edu/colorado/locality/entity/RabbitTest.java`
- `src/test/java/edu/colorado/locality/entity/WolfTest.java`
- `src/test/java/edu/colorado/locality/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 6

- `Creature`
- `Grass`
- `Rabbit`
- `Wolf`
- `Ecosystem`
- `Simulation`

Test classes changed: 5

- `CreatureTest`
- `GrassTest`
- `RabbitTest`
- `WolfTest`
- `EcosystemTest`

Total classes touched in this phase: 11

## Layers Affected

Count: 4

- Core layer
- Entity layer
- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: shared lifecycle-state coupling
- Secondary coupling: system-wide mortality orchestration

Aging introduces lifecycle state into the shared core abstraction and couples every entity to species-specific lifespan policies. The simulation layer now coordinates a global mortality pass in addition to movement, feeding, and reproduction.

## New Coordination Rules

Count: 5

- Every living creature ages once per aging round
- Creatures die automatically when `age >= maxAge`
- Dead creatures no longer age further
- Species-specific lifespans are enforced through entity constructors
- Aging and death are coordinated as a dedicated ecosystem step

## Control-Flow Complexity

- Classification: global lifecycle sweep
- Shape: full-pass state update with threshold-triggered death

Compared to V3 reproduction, the aging round uses simpler control flow, but it broadens the amount of shared lifecycle state that must remain consistent across all entities.

## State Mutation Scope

- Shared `age` and `maxAge` state is introduced into every creature
- Living creatures mutate age each round
- `alive` state can now change due to lifespan exhaustion, not only predation
- Species constructors encode lifespan policy
- Simulation output now reflects age in addition to liveness and position

## Temporary Round-Level Bookkeeping

- None

The aging round does not require temporary per-round tracking structures, but it does increase the amount of persistent lifecycle state carried by every entity.

## Aging and Death Rules Implemented

- `Grass` maximum age is `3`
- `Rabbit` maximum age is `5`
- `Wolf` maximum age is `8`
- Aging increments once per ecosystem aging round
- Creatures die when they reach their lifespan limit
- Dead creatures remain in the ecosystem but stop aging further

## Execution Semantics

The current `Ecosystem.simulateAgingAndDeath()` implementation performs a sequential full pass over the creature list and delegates lifecycle updates to each creature. Mortality is represented by flipping the existing `alive` flag rather than removing dead creatures from the ecosystem.

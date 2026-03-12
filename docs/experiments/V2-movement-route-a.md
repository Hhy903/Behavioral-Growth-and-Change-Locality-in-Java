# V2 Movement Experimental Record (Route A)

## Scope

This record captures the second behavioral-growth step after the V1 feeding version:

- Version: V2
- Behavior introduced: Movement
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

Test classes changed: 4

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

## Movement Rules Implemented

- Every creature now has spatial coordinates `(x, y)`
- `Rabbit` moves by `(1, 0)` in each movement step
- `Wolf` moves by `(2, 0)` in each movement step
- `Grass` uses the default non-moving behavior
- Dead creatures are skipped during ecosystem movement

## Execution Semantics

The current `Ecosystem.simulateMovement()` implementation executes a sequential movement pass over living creatures in insertion order. For this version, movement is deterministic and species-specific, with no collision or boundary rules yet.

## Behavioral Impact Summary

The introduction of movement behavior required extending the shared core abstraction with spatial state `(x, y)` and a new `move()` method. Unlike the feeding behavior introduced in V1, which primarily affected entity interaction logic, movement modifies the underlying representation of all creatures.

This change propagates through multiple architectural layers: the core abstraction was extended, entity classes implemented species-specific movement rules, and the simulation layer introduced a movement round to coordinate state updates.

## Locality Observation

Compared to the feeding behavior introduced in V1, movement exhibits a different structural impact pattern. While the number of modified files remains similar, the type of change is more invasive: the core abstraction now includes spatial state shared by all entities.

This indicates that behavior additions affecting shared state may propagate through the architecture differently than purely interaction-based behaviors.
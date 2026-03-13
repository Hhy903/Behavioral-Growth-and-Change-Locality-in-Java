# V3 Reproduction Experimental Record (Route A)

## Scope

This record captures the third behavioral-growth step after the V2 movement version:

- Version: V3
- Behavior introduced: Reproduction
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

## Reproduction Rules Implemented

- `Rabbit` can reproduce with another adjacent `Rabbit`
- `Wolf` can reproduce with another adjacent `Wolf`
- Reproduction requires both creatures to be alive
- Reproduction requires the same `y` coordinate and an `x` distance of at most 1
- Each creature can participate in at most one reproduction event per round
- Newborn creatures are appended after the current reproduction round completes

## Execution Semantics

The current `Ecosystem.simulateReproduction()` implementation scans the existing creature list in insertion order, matches the first valid mate for each unused living creature, and appends newborns after the scan. This keeps the behavior deterministic while preserving the intentionally naive Route A design.

## Behavioral Impact Summary

Compared to earlier growth steps, reproduction introduces a more coordination-heavy form of behavior.  
Unlike feeding, which applies a direct interaction rule, and movement, which updates individual spatial state, reproduction requires pair matching, participation constraints, and deferred offspring insertion.

As a result, the simulation layer now carries additional round-level policy responsibilities beyond simple sequential behavior execution.

## Locality Observation

Although the number of modified files remains similar to V1 and V2, the structural role of those modifications has become more invasive.  
Reproduction does not merely extend entity logic; it also introduces lifecycle management and orchestration policy into the ecosystem layer.

This suggests that coarse file-count metrics alone may understate the increasing complexity of behavioral growth in the naive object-oriented design.
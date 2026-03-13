# V6 Seasonal Resource Policy Experimental Record (Route A)

## Scope

This record captures the sixth behavioral-growth step after the V5 disease spread version:

- Version: V6
- Behavior introduced: Seasonal resource policy
- Architectural route: Route A, naive object-oriented growth

## Modified Source Files

Count: 3

- `src/main/java/edu/colorado/locality/simulation/Season.java`
- `src/main/java/edu/colorado/locality/simulation/Ecosystem.java`
- `src/main/java/edu/colorado/locality/simulation/Simulation.java`

## Modified Test Files

Count: 2

- `src/test/java/edu/colorado/locality/simulation/SeasonTest.java`
- `src/test/java/edu/colorado/locality/simulation/EcosystemTest.java`

## Modified Classes

Production classes changed: 3

- `Season`
- `Ecosystem`
- `Simulation`

Test classes changed: 2

- `SeasonTest`
- `EcosystemTest`

Total classes touched in this phase: 5

## Layers Affected

Count: 2

- Simulation layer
- Test layer

## Behavioral Coupling Type

- Primary coupling: system-level policy coupling
- Secondary coupling: simulation-to-resource lifecycle coupling

Seasonal policy is the first phase where behavior is driven by an explicit global mode rather than by local entity state or local interactions. Resource availability now depends on ecosystem-wide policy state, not just entity rules.

## New Coordination Rules

Count: 6

- The ecosystem maintains a global current season
- Seasons advance in a fixed cycle: spring, summer, autumn, winter
- Spring adds two new grass resources
- Summer adds one new grass resource
- Autumn adds no new grass resources
- Winter ages all living grass as a scarcity policy

## Control-Flow Complexity

- Classification: global mode-dependent policy dispatch
- Shape: season switch with resource creation and season-specific side effects

Compared to V5 disease spread, the control flow is simpler within one round, but the system now depends on a persistent global mode that alters future behavior across rounds.

## State Mutation Scope

- Ecosystem-level `currentSeason` state is introduced
- Seasonal policy can create new grass objects
- Winter policy mutates the lifecycle state of existing grass
- Simulation output now reflects seasonal context
- Resource availability becomes dependent on global policy, not just local interactions

## Temporary Round-Level Bookkeeping

- None

The seasonal policy step does not require temporary round data structures, but it introduces durable system-level control state that affects how future rounds behave.

## Seasonal Resource Rules Implemented

- Initial season is `SPRING`
- `SPRING` adds two grass resources
- `SUMMER` adds one grass resource
- `AUTUMN` adds no grass resources
- `WINTER` ages all living grass by one extra step
- Season progression wraps from `WINTER` back to `SPRING`

## Execution Semantics

The current implementation stores season in `Ecosystem`, applies resource policy through a season-dependent switch, and advances season explicitly through `advanceSeason()`. This makes policy variation a centralized simulation concern and further reduces locality in the naive Route A design.

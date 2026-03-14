# Route B Summary

## Scope

This document summarizes the full Route B sequence from `V0-B` through `V6-B`.

## Architectural Intention

Route B was designed around a single principle:

- Entities keep state
- Behavior modules own behavioral rules
- The ecosystem acts primarily as a generic coordinator

This architecture does not eliminate complexity. Instead, it attempts to localize behavioral growth so that new behavior modifies fewer architectural areas.

## Phase Progression

### V0-B Baseline

- Introduced a parallel package structure for Route B
- Established `BehaviorStep` as the extension point for future behaviors
- Kept entities minimal and state-oriented

### V1-B Feeding

- Added `FeedingBehavior`
- Kept feeding rules out of entity classes
- First evidence that behavior could grow without modifying the entity layer

### V2-B Movement

- Added shared spatial state to the core/entity model
- Added `MovementBehavior` for species-specific movement rules
- Showed that unavoidable state coupling can coexist with localized behavior rules

### V3-B Reproduction

- Added `ReproductionBehavior`
- Encapsulated pair matching, deferred insertion, and round-level bookkeeping in one behavior module
- Provided the clearest contrast with Route A, where the same complexity expanded ecosystem responsibilities

### V4-B Aging and Death

- Added shared lifecycle state to the core/entity model
- Added `AgingBehavior` for mortality policy
- Demonstrated that policy diffusion can still be limited even when shared state expansion is unavoidable

### V5-B Disease

- Added shared infection state to the core model
- Added `DiseaseBehavior` with carrier snapshots and deferred infection application
- Showed that even highly coordination-heavy behavior can remain behavior-localized

### V6-B Seasonal Resource Policy

- Added seasonal global state in the simulation layer
- Added `SeasonalResourceBehavior` for season-dependent resource rules
- Revealed the limit case of modularization: some global control state still belongs in the simulation layer

## Main Structural Findings

- Route B does not prevent shared state expansion when a behavior truly needs common state
- Route B does reduce the spread of behavior-specific rules across core, entity, and simulation layers
- Round-level bookkeeping still appears in complex phases, but it is localized inside behavior modules instead of turning into permanent ecosystem responsibilities
- Global policy phases still require simulation-layer support, but policy rules remain more localized than in Route A

## Overall Interpretation

Route B preserves locality most effectively when the new behavior is primarily a rule system rather than a new shared data model.  
For movement, aging, and disease, some shared state growth is unavoidable.  
For feeding, reproduction, and disease propagation policy, Route B still offers clear locality benefits because the behavioral logic remains concentrated in dedicated modules.

## Comparative Thesis Direction

The combined Route A and Route B results suggest a stronger thesis than simple file-count reduction:

- Behavioral growth increases structural complexity in both routes
- The key difference is where that complexity accumulates
- Route A tends to push complexity into entity APIs and ecosystem coordination code
- Route B tends to concentrate complexity inside explicit behavior modules

That distinction is likely to be more analytically valuable than raw file counts alone.

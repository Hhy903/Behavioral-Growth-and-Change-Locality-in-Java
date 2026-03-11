# Experimental Design Document

## Behavioral Growth and Change Locality in Java Systems

---

## 1. Research Objective

This study investigates how **behavioral growth** affects **change locality** in object-oriented Java systems. As software systems evolve, new behaviors are continuously introduced. These additions may require modifications across multiple modules, potentially reducing maintainability and increasing structural coupling.

The primary objective is to experimentally evaluate:

> How does incremental behavioral growth influence the locality of code changes in Java object-oriented systems?

A secondary objective is to assess whether alternative architectural strategies can mitigate change diffusion.

---

## 2. Research Questions

### RQ1 — Growth Effect

How does incremental behavioral growth impact change locality in a baseline object-oriented architecture?

### RQ2 — Architectural Influence

Does modularizing behavior improve change locality under sustained behavioral growth?

### RQ3 — Behavior Type Sensitivity

Which types of behaviors are most likely to cause cross-module change diffusion?

---

## 3. Experimental Subjects

The experimental platform is a custom-built ecological simulation framework implemented in Java. The system is intentionally small and controlled to isolate structural effects from domain complexity.

### 3.1 Baseline Architecture (V0)

The baseline system consists of three architectural layers:

**Core Layer**

* Fundamental abstractions
* Example: `Creature`

**Entity Layer**

* Concrete domain entities
* Examples: `Wolf`, `Rabbit`, `Grass`

**Simulation Layer**

* System orchestration and state management
* Examples: `Ecosystem`, `Simulation`

This architecture represents a natural, minimally engineered object-oriented design without advanced modularization strategies.

---

## 4. Experimental Variables

### 4.1 Independent Variables

#### A. Behavioral Growth Stage

System behavior is incrementally expanded across multiple versions.

#### B. Architectural Style

Two architectural strategies are evaluated:

**Route A — Naive Object-Oriented Growth**

* Behaviors implemented directly within entity classes
* Minimal abstraction for behavioral logic

**Route B — Behavior-Modularized Growth**

* Behavioral logic separated via abstractions
* Use of behavior interfaces and strategy components

---

### 4.2 Dependent Variables (Locality Metrics)

Change locality is quantified using the following metrics:

1. **Files Modified**
   Number of source files changed when introducing a new behavior.

2. **Classes Modified**
   Number of classes requiring modification.

3. **Lines Changed (LOC Delta)**
   Total lines added, removed, or modified.

4. **Layer Spread**
   Number of architectural layers affected (core/entity/simulation/behavior).

5. **Test Impact**
   Number of test files modified or newly added.

---

## 5. Behavioral Growth Plan

Behavioral complexity is introduced incrementally to simulate realistic software evolution.

| Version | Behavior Introduced      | Behavioral Scope           | Expected Locality Impact |
| ------- | ------------------------ | -------------------------- | ------------------------ |
| V0      | State-only baseline      | Object state only          | Minimal                  |
| V1      | Feeding                  | Local interactions         | Low                      |
| V2      | Movement                 | Spatial state updates      | Moderate                 |
| V3      | Reproduction             | Object lifecycle changes   | Moderate–High            |
| V4      | Aging & Death            | Lifecycle + state policies | High                     |
| V5      | Disease Spread           | Cross-entity propagation   | Very High                |
| V6      | Seasonal Resource Policy | System-wide rule variation | System-Level             |

---

## 6. Experimental Procedure

### Step 1 — Baseline Establishment

* Implement V0 minimal system
* Tag version as baseline
* Record baseline structural metrics

### Step 2 — Incremental Growth (Route A)

For each behavioral stage:

1. Implement behavior using naive OO approach
2. Run test suite
3. Record locality metrics
4. Commit changes as separate version

### Step 3 — Refactored Growth (Route B)

1. Refactor system to modularize behavior
2. Reintroduce the same behavioral stages
3. Record identical locality metrics
4. Compare results with Route A

---

## 7. Measurement Method

Metrics will be collected using:

* Git commit statistics
* Diff-based line change counts
* Package-level modification analysis
* Test file change tracking

Each behavioral addition corresponds to one controlled commit to isolate structural impact.

---

## 8. Data Analysis Plan

The collected metrics will be analyzed to identify:

1. Growth trends in change diffusion
2. Architectural sensitivity to behavioral expansion
3. Behavioral categories that cause maximal structural disruption
4. Comparative benefits of modular behavior separation

---

## 9. Validity Considerations

### Internal Validity

* Controlled incremental behavior introduction
* Identical growth sequence across architectural variants

### External Validity

* Small system size limits generalizability
* Focus on structural patterns rather than domain realism

### Construct Validity

* Locality approximated via modification spread metrics
* May not fully capture cognitive complexity

---

## 10. Reproducibility Plan

* Each version tagged in Git
* Behavioral stages documented
* Source code publicly available
* Metrics derivable from commit history

---

## 11. Expected Contributions

This study aims to:

1. Provide empirical evidence linking behavioral growth to structural change diffusion
2. Demonstrate the influence of architectural design on locality preservation
3. Offer a reproducible experimental framework for studying software evolution locality

---

## 12. Current Status

* Phase 0 Complete: Baseline system established
* Next Phase: Implementation of Feeding Behavior (V1)

---

**End of Experimental Design Document**

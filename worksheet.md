# A2 Worksheet: Designing the Mothership

**Name: Sean Chang**
**Onyen: sechang**

Three questions, 15 points, about fifteen minutes. Do this before you write any
code; everything you need is in `README.md` and restated below. You should not
need to open a single `.java` file to answer these. Write your answers directly
under each prompt.

---

## Question 1: Vocabulary of the hierarchy (5 points)

**1a.** For each pair, write **IS-A** or **HAS-A**, plus a half-sentence saying
how you know. (Remember: IS-A means one class extends the other; HAS-A means one
class stores the other in a field.)

| Pair | IS-A or HAS-A? | How you know                                              |
|---|----------------|-----------------------------------------------------------|
| `FuelGenerator` → `APowerGenerator` | IS-A           | A fuel generator is a type of power generator.            |
| `Mothership` → `ThrusterModule` | HAS-A          | The ship is the overarching object                        |
| `SolarGenerator` → `AModule` | IS_A           | A solar generator is one of the modules                   |
| `ExperimentModule` → `double[] parameters` | HAS_A          | The experiment module takes in parameters to build itself |

**1b.** Both `AModule` and `APowerGenerator` are declared `abstract`, but only
`APowerGenerator` declares an **abstract method** (`generatePower()`). These are
two different design decisions doing two different jobs.

- Marking the *class* abstract stops a programmer from doing what?

```
Instantiating an empty base object.
```

- Marking the *method* abstract forces a programmer to do what?

```
Define an implementation in the child that uses the method.
```

**1c.** `ThrusterModule`, `SolarGenerator`, and `ExperimentModule` each override
`statusReport(...)`, print one line of their own, and then call
`super.statusReport(...)`. Suppose a classmate writes their override, prints their
own line, and forgets the `super` call. Will the compiler complain? What is
actually lost, and how would the student find out?

```
The compiler will not complain, since that is valid code.
Instead you lose the overarching status output, which the 
student discovers when they test and don't see it.
```

---

## Question 2: One mission, by hand (6 points)

You do not need the code for this. Here are the only rules that matter, copied
from the spec:

- `FuelGenerator.generatePower()`: if `fuel >= 10`, return `10` and subtract 10
  from `fuel`. Otherwise, return whatever `fuel` is left and set `fuel` to 0.
- `SolarGenerator.generatePower()`: always returns `10`. It tracks no state.
- `ThrusterModule.thrust(int availablePower)`: a thrust succeeds only if the
  thruster has **at least 5 fuel** *and* `availablePower` is **at least 5**. On
  success, subtract 5 from the thruster's fuel, set `lastFired` to `true`, and
  return `true`. Otherwise set `lastFired` to `false` and return `false`. A
  failed thrust burns no fuel.
- `ThrusterModule` always starts with **100 fuel** and `lastFired = false`.

**2a.** `Main` builds a `FuelGenerator` with **22 fuel** and hands it to the
mothership. The mission then runs four rounds; each round is one
`requestPower()` immediately followed by one `fireThruster(power)` using the
value that was just returned. Fill in the table.

| Round | Power returned | Generator fuel after | Thruster fuel after | Thrust succeeded? | `lastFired` |
|---|----------------|----------------------|---------------------|-------------------|-------------|
| start | —              | 22                   | 100                 | —                 | false       |
| 1 | 10             | 12                   | 95                  | yes               | true        |
| 2 | 10             | 2                    | 90                  | yes               | true        |
| 3 | 2              | 0                    | 90                  | no                | false       |
| 4 | 0              | 0                    | 90                  | no                | false       |

**2b.** Now change **one line in `Main`** so the ship launches with a
`SolarGenerator` instead. Nothing inside `Mothership` changes. Redo rounds 3 and
4 only.

| Round | Power returned | Thruster fuel after | Thrust succeeded? |
|---|----------------|---------------------|-------------------|
| 3 | 10             | 85                  | yes               |
| 4 | 10             | 80                  | yes               |

Which line in `Main` changed, and what is it about the **declared type** of the
mothership's generator field that made that one line enough?

```
The line that instantiated the type of power generator changed it from a FuelGenerator 
to a SolarGenerator. Since the SolarGenerator has it's own generatePower method that 
always returns 10 and it extends the APowerGenerator class' abstract method, only one 
line needed to change. 
```

**2c.** On the solar ship, the thruster will eventually stop firing anyway.
Which round is the first failed thrust, and why? Show the arithmetic.

```
100-x(5) = 20. After 20 rounds the Thruster fuel will hit 0, and subsequent rounds will fail.
So first failure is 21. 
```

---

## Question 3: Design pressure (4 points)

**3a.** Part 4 has you add an overloaded `Mothership` constructor whose fourth
parameter is typed `AModule`, not the name of the specific module class you
invented. A classmate says "that's silly, I know mine is a `ShieldModule`, I
should just say `ShieldModule`." Give them one concrete thing that breaks —
something the ship could do with the `AModule` version that it could not do with
theirs.

```
With AModule, the ship can add any kind of module in that
slot, but if it's defined as just ShieldModule, the class
loses its generalizability. If I wanted to use a BlasterModule instead, 
I wouldn't be able to use that constructor.
```

**3b.** You ask an AI assistant to help wire up the mothership and it proposes
this design, in words:

> "Give `Mothership` two fields, `private SolarGenerator solar;` and
> `private FuelGenerator fuel;`, plus a `private boolean usingSolar;`. Then
> `requestPower()` checks the flag with an `if` and calls `generatePower()` on
> whichever one is active. This is clearer than the abstract class because you
> can see exactly which generator you're using."

The code would compile and the tests for a two-generator ship would pass. Say
what is wrong with it anyway. Name specifically what the team has to do when a
third generator (say, `ReactorGenerator`) is added later, and contrast that with
what the spec's design requires.

```
This essentially negates the point of making the modules extensions of a base object class, 
forcing the user to define a bunch of "if" cases for each module type. The spec requires
you want to share code and a common interface between related subclasses, which is violated
if you add a 3rd generator and have to define another "if" case.
```

---

## Submitting

Turn this in with your answers, as a .md on Gradescope. The code goes to
Gradescope separately; see `README.md`.

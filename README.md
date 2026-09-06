# A02 - Mothership Mayhem
![iss056e201248~large](https://github.com/user-attachments/assets/66cc6b28-eb04-4e6f-b82f-5b7070028cce)

Welcome aboard captain! Your team at UNC Deep Space Systems wants to build 
a modular mothership capable of swapping power sources on the fly, firing thrusters, and
running science experiments. You'll build tiny modules that communicate cleanly with proper 
encapsulation. When you're done, you'll be able to refuel or change your power source mid-mission
without touching a single line of mothership code! 🚀

> ## Start here: do `worksheet.md` first
>
> Before you write any code, complete **`worksheet.md`** (15 points, about
> fifteen minutes). It is a design-and-reasoning warm-up, not a coding exercise,
> and it is meant to make the code go faster once you start.
>
> **What you need to complete it:**
> - **This `README.md`** — read it all the way through first, especially
>   *How the Classes Work Together*, *Inheritance and Abstract Classes*, and
>   *Building the Ship from Parts*. Those three sections answer most of the
>   worksheet on their own.
> - **`worksheet.md` itself** — every rule you need for the hand-trace in
>   Question 2 is restated inside the question. You do not have to hunt for it.
> - **Your COMP210 notes on inheritance, abstract classes, `super`, and
>   declared vs. actual type** — useful for the vocabulary in Question 1.
>
> **What you do not need:** any `.java` file. The worksheet is answerable
> without opening the starter code, and the starter methods are empty anyway.
> If you find yourself reading source to answer a question, re-read the README
> section that covers it instead.
>
> The worksheet is submitted to Gradescope as a `.md`, separately from your code.

# AI Rung: Tier 1

AI as explainer
You may use UNC's LearnWithAI system to help you with your work to explain syntax and semantics, walk through worked examples, answer conceptual questions, and critique your code by pointing out bugs and suggesting improvements is allowed. LLMs outside of LearnWithAI should not be used.  


# How the Classes Work Together

```text
  ├── AModule.java           # Abstract base class for all modules
  ├── APowerGenerator.java   # Abstract base class for any power generator
  ├── ExperimentModule.java  # Runs and reports science experiments
  ├── FuelGenerator.java     # Fuel-powered generator
  ├── Main.java              # The entry point for all java programs.
  ├── Mothership.java        # Orchestrates modules (power, thrusterModule, experiments)
  ├── SolarGenerator.java    # Solar-powered generator
  └── ThrusterModule.java    # Consumes power to provide thrust
```


The **Mothership** coordinates a collection of **AModule**s—most notably a **ThrusterModule**, an **ExperimentModule**, and one **APowerGenerator**. The power generator can be either a **SolarGenerator** or a **FuelGenerator**, both of which *extend* `APowerGenerator` so the mothership can call `generatePower()` without caring how the power is produced. The generator is built in `Main` and passed into the mothership's constructor, which means you can choose which one to use by handing the mothership a different generator object—no changes to mothership code required.

The mothership is composed of its thrusterModule and experiment modules and may maintain a list of all `AModule` objects to broadcast `statusReport(...)` calls for consistent health reporting. The **Main** class acts as the mission script: it builds everything, hands the chosen power generator to the mothership, and triggers high-level actions such as `requestPower()`, `fireThruster(...)`, `runExperiment()`, and reporting routines.



## Inheritance and Abstract Classes

- **AModule** is an **abstract** base class. It defines shared behavior (e.g., a standardized `statusReport(...)`) that all concrete modules inherit, but it should not be instantiated by itself—there’s no such thing as a generic, fully functional module. Marking it *abstract* prevents accidental construction of incomplete objects and communicates design intent.
- **APowerGenerator** is also **abstract**, and it goes one step further: it declares an **abstract method**, `generatePower()`. An abstract method has no body—it says *what* a power source must do without saying *how*. Because it has no body, every concrete subclass is **required** to write its own version. Concrete classes like **SolarGenerator** and **FuelGenerator** extend `APowerGenerator` and fill in `generatePower()` with their own strategies.



## Building the Ship from Parts

The mothership does **not** create its own power generator. It never calls `new SolarGenerator()` anywhere. Instead, `Main` builds the generator it wants (e.g., `new SolarGenerator()` or `new FuelGenerator(fuel)`) and **passes it in** as a constructor parameter.

The trick that makes this work is the *type* of the mothership's field. If the field were declared as `SolarGenerator`, the mothership could only ever hold a solar panel. By declaring it with the **parent** type `APowerGenerator`, any subclass fits—solar today, fuel tomorrow—and the mothership code never changes. This is the same polymorphism you've been practicing, just applied to how objects are wired together. It also makes the ship easier to test and easier to upgrade later.


---


## Part 1: Modules and the Thruster

This stage is where we lay the **foundation**: base class and associated modules.  In this section, you will work with the following files:

- AModule.java        # Abstract base class for all modules
- ThrusterModule.java          # Consumes power to provide thrust

Every module on this ship needs two things in common: it needs a **name**, and it needs to be able to
**report its status**. Rather than write those two things over and over in every module class, we
will write them once in a parent class and let everything else inherit them.

### Why Mark a Class Abstract?

Use an abstract class when:
1. You want to **share code and a common API** among related subclasses (e.g., one consistent `statusReport(...)`), and
2. It **doesn’t make sense** to create a base instance on its own (a generic AModule isn’t operational).

This keeps your codebase safe (no half-baked base objects), clear (intent is explicit), and DRY (common logic lives in one place).

## AModule (`AModule.java`)

The stub is there for you, but you must fill in the details.

Create an **abstract** class called `AModule`.  
This class will be your base class that all other modules will inherit from.  

### Fields and Constructors
- A `String` representing the `name` of the module.

The value for the name should be passed to the constructor as a parameter and set in the body.

### Methods

Remember that abstract classes can contain both abstract and concrete methods. In this case, we don't need any abstract methods, _even though_ we have an abstract class.
This class will have two  **concrete** (non-abstract) methods:

- `String getName()`: a standard getter for the name Variable.  

  `statusReport` with the following parameters:

- `String moduleStatus`
- `boolean isSuccessful`

Your method should first print the name of the module, and the current status, simulating the start of a particular task (initializing, processing, finished, etc.):

Example output:

`<moduleName> is Initializing...`

Then it should check to see whether the action was successful by checking the boolean.  

If the action is successful, it should print:
`Action Successful.`

Otherwise, it should print the status as the following string:
`<moduleName> needs immediate attention!`


## ThrusterModule (`ThrusterModule.java`)

Create a subclass of `AModule` called `ThrusterModule`.

### Fields:

-  `int fuel` - which will initially have a value of `100`
- `boolean lastFired` - which will initially be `false`

Note: initialize your fields in your constructor.  These have default values and therefore do not need to be taken in as parameters.

### Methods:

1. Modify `statusReport` to make it print the amount of fuel remaining in thrusterModule as well as whether it  
last fired. Then, call your base implementation to print the remaining information. 

Example output: 

`ThrusterModule: 80 units of fuel remaining. Last fired: Yes`

2. Create a method called `thrust` which takes `int availablePower` as a parameter and returns a boolean.
```java
public boolean thrust(int availablePower);
```
- This method performs a thrust each time it is called. 
- For a successful thrust, you need to have at least 5 units of fuel and 5 units of available power.
- If you have enough fuel and power, use 5 units of fuel and set lastFired to true.
- Each time you thrust, you need to decrement your fuel value
- Print the following line `"5 fuel used for propulsion maneuver."`
- Return `true`
- Otherwise, set lastFired to false, print `"ThrusterModule: Not enough power or fuel to fire."` and return false.

--- 

## Part 2: Power Generators

In this stage of the assignment, you will create your generators! It is important that we specify *what* a power source must do (e.g., `generatePower()`), without specifying *how*.  Therefore, we are going to introduce the `APowerGenerator` class, while classes like **SolarGenerator** and **FuelGenerator** extend it with their own strategies.

Fill in `APowerGenerator.java` so that it looks like this:

```java
public abstract class APowerGenerator extends AModule {

    public APowerGenerator(String name) {
        super(name);
    }

    public abstract int generatePower();
}
```

Two things to notice here:

1. `APowerGenerator` **extends** `AModule`.  That means that all generators have an **IS-A** relationship with `AModule`—every generator is a module, so it already has a `getName()` and a `statusReport(...)` for free. Its constructor simply passes the name up to `AModule` with `super(name)`.
2. `generatePower()` is declared **abstract**: it has a semicolon instead of a body. `APowerGenerator` doesn't know how power gets made—only that every generator must make some. Any concrete class that extends `APowerGenerator` is required to write its own `generatePower()`, or it won't compile.


Now, you'll create two classes that both extend `APowerGenerator`.

## SolarGenerator (`SolarGenerator.java`)
Change the class header to use the `APowerGenerator` parent class.

### Methods:
_hint: remember your super keyword.  You'll use it more than once_

1. Constructor: SolarGenerator extends APowerGenerator, and you will have to make a constructor to initialize the object.
2. `statusReport`:  As is, SolarGenerator inherits statusReport from AModule.  However, we need additional information for our generator classes. Keeping in mind the DRY principle, print out the following solar generator specific line:

`"Solar Generators will never die"`

Then, print the lines from your base implementation.

3. Implement `generatePower` to return a power of 10. 


## FuelGenerator (`FuelGenerator.java`)

### Fields and Constructor:
-  `int fuel` - initialize as a constructor parameter

### Methods:
1. Modify `statusReport` to print the following fuel
   generator specific line before printing the lines from `AModule`'s implementation.

`"FuelGenerator: <fuel> units of fuel remaining."`


2. Implement `generatePower` as follows:

- If `fuel >= 10`, return ***10*** and decrement fuel by that amount. 
- Otherwise, return the remaining fuel and set fuel to 0. 

Simple reference:

| Class          | generatePower()                           | Tracks State | statusReport adds…               |
| -------------- | ----------------------------------------- | ------------ | -------------------------------- |
| SolarGenerator | Constant output (e.g., 10)                | No           | Solar note, then base report     |
| FuelGenerator  | Consumes fuel in chunks (10 or remainder) | Yes (fuel)   | Fuel remaining, then base report |

---

## Part 3: Experiments and the Mothership

Now, for the fun part where everything comes together!  We are going to make an experiment module that has a little more complexity.

## ExperimentModule (`ExperimentModule.java`)

### Fields and Constructor:

- `String experimentName`
- `double[] parameters` 
- `double result`
- `boolean hasRun`

The constructor should take in the experimentName and parameters as arguments and then set `hasRun` to false, and `result` to 0;

### Methods:

1. `void runExperiment()` - Iterate through the list of parameters, and increment result by each multiplied by a random number. (use `Math.random()`).  Set hasRun to `true`.
2. `String getSummary()` - This method should return one of the two following lines depending
on the status of `hasRun`: 
```java
return "Experiment not run yet.";
```
```java 
return "Experiment '" + experimentName + "' result: " + result;
```
3. `statusReport` - override the existing implementation to print the following:
`ExperimentModule: {experimentName} completed.` OR `ExperimentModule: {experimentName} pending.`
depending on the status of hasRun. Then, print the lines from your base implementation.

## Mothership (`Mothership.java`)

### Fields:
- Create private instances of `APowerGenerator`, `ThrusterModule`, `ExperimentModule`, and an ArrayList
of `AModule`. 

Write a constructor that takes all three modules as parameters, saves them into your fields, and adds them to your list of AModules:

```java
public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule, ExperimentModule experimentModule);
```

Thought experiment: Why is the ArrayList declared as `ArrayList<AModule>` instead of, say, `ArrayList<ThrusterModule>`?  Java generics let us hold every object that has an **IS-A** relationship with `AModule`—which is exactly what we want, since a thruster, an experiment, and a generator are all modules. Declaring the list with the parent type is what lets one loop print a status report for all of them.

### Methods:
1. `int requestPower()` - this method requests power from your power generator
2. `boolean fireThruster(int availablePower)` - this method uses your thrusterModule to thrust forward
3. `void runExperiment()` - delegate to your experiment module
4. `String getExperimentSummary()` - return the summary from your experiment module
5. `void printStatusReports()` - iterate modules and call statusReport("Normal", true)


# Mission Control (Main.java)

This is your **launch sequence**: wire the ship together, exercise its core capabilities, and print a few simple readouts so we can verify behavior end-to-end.

### What to build

1. **Choose a power source**  
   Create an `APowerGenerator` variable. Start with a `SolarGenerator`; later, verify swappability by replacing it with a `FuelGenerator` (e.g., initialized with a reasonable fuel amount). Notice that nothing inside `Mothership` has to change.

   ```java
   APowerGenerator generator = new SolarGenerator();
   // To swap use: APowerGenerator generator = new FuelGenerator(50);
   ```

2. **Assemble the modules**  
   Construct a `ThrusterModule` (it should initialize with its default fuel and flags) and an `ExperimentModule` (give it a short name and a small array of `double` parameters).

3. **Launch the mothership**  
   Pass the generator, thruster, and experiment into the `Mothership` constructor. After construction, ensure the mothership internally registers these in its modules list.

4. **Request power and report it**  
   Call `requestPower()` on the mothership, store the returned value, and print a one-line message confirming the requested power so the output is human-readable.

5. **Fire the thruster**  
   Call `fireThruster(...)` using the power you just requested. Capture the boolean result and print whether the thruster fired. (If the attempt fails, your output should still clearly explain why.)

6. **Run the experiment**  
   Delegate to the `ExperimentModule` through the mothership, then print the experiment’s summary line with `getExperimentSummary()`.

7. **Print status reports**  
   Finally, call `printStatusReports()` on the mothership to output the health and activity of all modules.

Once you finish Part 4, you'll come back and update steps 2, 3, and 7 to include your own module.


## Part 4: Build Your Own Module

Now, it's time to get creative!

You've demonstrated inheritance, polymorphism, and encapsulation on modules we designed for you. For this last part, you'll design one yourself and plug it into the ship.

Your module must do the following things:

1. **Create a new class that inherits from the `AModule` abstract class.** Pick your own name and theme—a shield generator, a cargo bay, a coffee machine, whatever fits your ship. Give it at least one private field of its own.
2. **Override `statusReport`** so that it prints out something new before calling your base implementation with `super`. Feel free to be creative on the theme of your module.
3. **Add an overloaded constructor to `Mothership`** that takes another module as a 4th parameter, and adds it to the list of `shipModules` along with the other three. Remember that an overloaded constructor is just a second constructor with a different parameter list—you can reuse the work of the first one with `this(...)` instead of copying its body:

   ```java
   public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule,
                     ExperimentModule experimentModule, AModule extraModule) {
       this(powerGenerator, thrusterModule, experimentModule);
       // now add extraModule to your list
   }
   ```

   Notice the 4th parameter is typed `AModule`, not the name of your specific class. That's the same idea as Part 2's generator: because your new module **IS-A** `AModule`, it fits—and so would anyone else's module.
4. **Use that overloaded constructor in `Main`**, so that your new module shows up when `printStatusReports()` runs.

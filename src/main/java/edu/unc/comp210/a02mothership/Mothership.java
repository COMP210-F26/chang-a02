package edu.unc.comp210.a02mothership;

import java.util.ArrayList;

public class Mothership {
    private APowerGenerator powerGenerator;
    private ThrusterModule thrusterModule;
    private ExperimentModule experimentModule;
    private ArrayList<AModule> shipModules = new ArrayList<>();

    public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule,
                      ExperimentModule experimentModule) {
        this.powerGenerator = powerGenerator;
        this.thrusterModule = thrusterModule;
        this.experimentModule = experimentModule;
        this.shipModules.add(powerGenerator);
        this.shipModules.add(thrusterModule);
        this.shipModules.add(experimentModule);
    }

    public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule,
                      ExperimentModule experimentModule, AModule extraModule) {
        this(powerGenerator, thrusterModule, experimentModule);
        this.shipModules.add(extraModule);
    }

    public int requestPower() {
        return this.powerGenerator.generatePower();
    }

    public boolean fireThruster(int availablePower) {
        return this.thrusterModule.thrust(availablePower);
    }

    public void runExperiment() {
        this.experimentModule.runExperiment();
    }

    public String getExperimentSummary() {
        return this.experimentModule.getSummary();
    }

    public void printStatusReports() {
        for (AModule m : this.shipModules) {
            m.statusReport("Normal", true);
        }
    }
}

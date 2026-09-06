package edu.unc.comp210.a02mothership;

public class ExperimentModule extends AModule {
    private String experimentName;
    private double[] parameters;
    private double result;
    private boolean hasRun;

    public ExperimentModule(String experimentName, double[] parameters) {
        super("ExperimentModule");
        this.experimentName = experimentName;
        this.parameters = parameters;
        this.result = 0;
        this.hasRun = false;
    }

    public void runExperiment() {
        for (double p : this.parameters) {
            this.result += p * Math.random();
        }
        this.hasRun = true;
    }

    public String getSummary() {
        if (this.hasRun) {
            return "Experiment '" + this.experimentName + "' result: " + this.result;
        } else {
            return "Experiment not run yet.";
        }
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        if (this.hasRun) {
            System.out.println("ExperimentModule: " + this.experimentName + " completed.");
        } else {
            System.out.println("ExperimentModule: " + this.experimentName + " pending.");
        }
        super.statusReport(moduleStatus, isSuccessful);
    }
}

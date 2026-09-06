package edu.unc.comp210.a02mothership;

public class Main {
    public static void main(String[] args) {
        APowerGenerator generator = new SolarGenerator();
        // To swap use: APowerGenerator generator = new FuelGenerator(50);

        ThrusterModule thruster = new ThrusterModule();
        ExperimentModule experiment = new ExperimentModule("Water Detector",
                new double[]{1, 2, 3, 4, 5});
        BlasterModule blaster = new BlasterModule();

        Mothership ship = new Mothership(generator, thruster, experiment, blaster);

        int power = ship.requestPower();
        System.out.println("Power received: " + power);

        boolean fired = ship.fireThruster(power);
        if (fired) {
            System.out.println("Thruster fired.");
        } else {
            System.out.println("Thruster did not fire: not enough power or fuel.");
        }

        ship.runExperiment();
        System.out.println(ship.getExperimentSummary());

        ship.printStatusReports();
    }
}

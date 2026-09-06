package edu.unc.comp210.a02mothership;

public class ThrusterModule extends AModule {
    private int fuel;
    private boolean lastFired;

    public ThrusterModule() {
        super("ThrusterModule");
        this.fuel = 100;
        this.lastFired = false;
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        System.out.println("ThrusterModule: " + this.fuel + " units of fuel remaining. Last fired: "
                + (this.lastFired ? "Yes" : "No"));
        super.statusReport(moduleStatus, isSuccessful);
    }

    public boolean thrust(int availablePower) {
        if (this.fuel >= 5 && availablePower >= 5) {
            this.fuel -= 5;
            this.lastFired = true;
            System.out.println("5 fuel used for propulsion maneuver.");
            return true;
        } else {
            this.lastFired = false;
            System.out.println("ThrusterModule: Not enough power or fuel to fire.");
            return false;
        }
    }
}

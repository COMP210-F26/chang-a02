package edu.unc.comp210.a02mothership;

public class BlasterModule extends AModule {
    private int ammo;
    private boolean operational;

    public BlasterModule() {
        super("BlasterModule");
        this.ammo = 100;
        this.operational = true;
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        System.out.println("BlasterModule: " + this.ammo + " rounds of ammo remaining. Operational: "
                + (this.operational ? "Yes" : "No"));
        super.statusReport(moduleStatus, isSuccessful);
    }
}

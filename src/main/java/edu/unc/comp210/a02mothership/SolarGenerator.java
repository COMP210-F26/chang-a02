package edu.unc.comp210.a02mothership;

public class SolarGenerator extends APowerGenerator {
    public SolarGenerator() {
        super("SolarGenerator");
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        System.out.println("Solar Generators will never die");
        super.statusReport(moduleStatus, isSuccessful);
    }

    @Override
    public int generatePower() {
        return 10;
    }
}

package edu.unc.comp210.a02mothership;


public abstract class APowerGenerator extends AModule {

    public APowerGenerator(String name) {
        super(name);
    }

    public abstract int generatePower();
}

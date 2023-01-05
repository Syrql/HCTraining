package fr.syrql.hctraining.selector;

import org.bukkit.Location;

public class Selector {

    private Location locationOne;
    private Location locationTwo;

    public Selector(Location locationOne, Location locationTwo) {
        this.locationOne = locationOne;
        this.locationTwo = locationTwo;
    }

    public Location getLocationOne() {
        return locationOne;
    }

    public void setLocationOne(Location locationOne) {
        this.locationOne = locationOne;
    }

    public Location getLocationTwo() {
        return locationTwo;
    }

    public void setLocationTwo(Location locationTwo) {
        this.locationTwo = locationTwo;
    }
}

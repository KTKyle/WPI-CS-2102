package HW2;

/**
 * Represents a Boat with a battery and distance-based travel capability.
 */
public class Boat implements Vehicle {

    /** The name of the boat */
    public String name;

    /** Battery that powers the boat */
    public Battery battery;

    /** Speed of the boat in meters per second */
    public double speed;

    /** Battery usage per meter traveled */
    public double fuelEfficiency;

    /** Distance to destination in meters */
    public double distanceToDestination;

    /**
     * Constructs a Boat with the following parameters:
     * @param name Name of the boat
     * @param battery Battery that powers the boat
     * @param speed Speed of the boat in meters per second
     * @param fuelEfficiency Battery charge used per meter
     * @param distanceToDestination Distance to destination in meters
     */
    public Boat(String name, Battery battery, double speed, double fuelEfficiency, double distanceToDestination) {
        this.name = name;
        this.battery = battery;
        this.speed = speed;
        this.fuelEfficiency = fuelEfficiency;
        this.distanceToDestination = distanceToDestination;
    }

    /** The name of the vehicle */
    @Override
    public String identifier() {
        return this.name;
    }

    /**
     * The amount of progress towards the vehicles destination on its current power
     source
     * @return a percentage represented as numbers between 0.0 and 1.0
     */
    @Override
    public double progressUntilRecharge() {
        double requiredCharge = distanceToDestination * fuelEfficiency;
        return battery.amountLeft >= requiredCharge ? 1.0 : battery.amountLeft / requiredCharge;
    }

    /**
     * whether the vehicle can reach its destination using its current power source
     * @return true if it can
     */
    @Override
    public boolean canReachDest() {
        return progressUntilRecharge() == 1.0;
    }

    /**
     * The amount of distance this vehicle can go on a full charge
     * @return the amount of distance in meters
     */
    @Override
    public double distancePossible() {
        return battery.capacity / fuelEfficiency;
    }

    /**
     * Travel for some number of seconds
     * @param seconds number of seconds the boat is travelling for
     */
    @Override
    public void travelFor(double seconds) {
        double distanceTravelled = speed * seconds;
        double batteryUsed = distanceTravelled * fuelEfficiency;

        distanceToDestination = Math.max(0.0, distanceToDestination - distanceTravelled);
        battery.amountLeft = Math.max(0.0, battery.amountLeft - batteryUsed);
    }

    /**
     * Overrides the equals function to compare if two boats are equal or not
     * @param o object we are comparing to
     * @return true if both boats are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Boat otherBoat) {
            return name.equals(otherBoat.name) &&
                    battery.equals(otherBoat.battery) &&
                    Math.abs(speed - otherBoat.speed) < 0.01 &&
                    Math.abs(fuelEfficiency - otherBoat.fuelEfficiency) < 0.01 &&
                    Math.abs(distanceToDestination - otherBoat.distanceToDestination) < 0.01;
        }
        return false;
    }

    /**
     * Overrides the toString function to return more meaningful information
     * @return String that represents the boat and its elements
     */
    @Override
    public String toString() {
        return "Boat: name = " + name +
                ", battery = " + battery +
                ", speed = " + speed +
                " m/s, fuelEfficiency = " + fuelEfficiency +
                " charge/meter, distanceToDestination = " + distanceToDestination;
    }
}

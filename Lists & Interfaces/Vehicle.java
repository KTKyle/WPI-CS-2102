package HW2;

/** An interface for a simulated vehicle */
public interface Vehicle {
    /** The name of the vehicle */
    public String identifier();

    /**
     * The amount of progress towards the vehicles destination on its current power
     source
     * @return a percentage represented as numbers between 0.0 and 1.0
     */
    public double progressUntilRecharge();

    /**
     * whether the vehicle can reach its destination using its current power source
     * @return true if it can
     */
    public boolean canReachDest();

    /**
     * The amount of distance this vehicle can go on a full charge
     * @return the amount of distance in meters
     */
    public double distancePossible();

    /**
     * Travel for some number of seconds
     * @param seconds
     */
    public void travelFor(double seconds);
}


package HW1;

/**
 * Represents the battery of a copter
 */
public class Battery {

    /** Battery's total capacity in milliAmp-seconds */
    public double capacity;
    /** Amount of total charge left in battery in milliAmp-seconds */
    public double amountLeft;

    /**
     * Constructs a battery with given capacity and initial amount of charge
     * @param capacity: the full capacity in milliAmp-seconds
     * @param amountLeft: the current charge left in milliAmp-seconds
     */
    public Battery(double capacity, double amountLeft) {
        this.capacity = capacity;
        this.amountLeft = amountLeft;
    }
}

package HW2;

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

    /**
     * Overrides the equals function to compare if two batteries are equal or not
     * @param o object we are comparing the current one to
     * @return true if both batteries are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Battery other = (Battery) o;
        return Math.abs(this.capacity - other.capacity) < 0.01
                && Math.abs(this.amountLeft - other.amountLeft) < 0.01;
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the battery and its elements
     */
    @Override
    public String toString() {
        return "Battery: " + "capacity = " + capacity + ", amountLeft = " + amountLeft;
    }
}

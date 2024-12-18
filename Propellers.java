package HW1;

/**
 * Represents the propellers of a copter
 */
public class Propellers {

    /** The number of propellers. */
    public int amount;
    /** Speed of the propellers in meters per second. */
    public double speed;
    /** Current draw per propeller in milliAmps */
    public double currentDrawEach;

    /**
     * Constructs a propeller with given capacity and initial amount of charge
     * @param amount: the number of propellers
     * @param speed: the speed of each propeller in meters/second
     * @param currentDrawEach: the current draw per propeller in milliamp/prop
     */
    public Propellers(int amount, double speed, double currentDrawEach) {
        this.amount = amount;
        this.speed = speed;
        this.currentDrawEach = currentDrawEach;
    }

    /**
     * Calculates the total current draw for all propellers combined
     * @return the total current draw in milliAmps
     */
    public double totalCurrentDraw(){
        return amount * currentDrawEach;
    }

    /**
     * Calculates the distance the copter can travel over a specified time based on propeller speed
     * @param seconds: the time in seconds in which the distance is calculated
     * @return the distance in meters that the copter can travel
     */
    public double distanceTravelled(double seconds){
        return speed * seconds;
    }
}

package HW2;

/**
 * Represents a copter which includes its battery, propellers, and destination distance
 */
class Copter implements Vehicle {

    /** The name of the copter. */
    public String name;

    /** The propeller used by the copter. */
    Propellers propeller;

    /** The battery used by the copter. */
    Battery battery;

    /** Distance in meters to the copter's destination. */
    public double metersToDestination;


    /**
     * Constructs a Copter with specified propellers, battery, and destination distance
     * @param name the name of the copter
     * @param propeller the propellers of the copter
     * @param battery the battery of the copter
     * @param metersToDestination the initial distance to the destination in meters
     */
    public Copter(String name, Propellers propeller, Battery battery, double metersToDestination){
        this.name = name;
        this.propeller = propeller;
        this.battery = battery;
        this.metersToDestination = metersToDestination;
    }

    /** The name of the vehicle */
    @Override
    public String identifier() {
        return this.name;
    }

    /**
     * Calculates the ratio of remaining battery over distance until reaching the destination
     * @return a value between 0.0 and 1.0, where 1.0 means enough charge to reach the destination
     */
    public double rechargeIndex() {
        return (this.propeller.totalCurrentDraw() * this.battery.amountLeft) / this.metersToDestination;
    }

    /**
     * The amount of progress towards the vehicles destination on its current power
     source
     * @return a percentage represented as numbers between 0.0 and 1.0
     */
    public double progressUntilRecharge(){
        if(rechargeIndex() <= 1.0){
            return rechargeIndex();
        }
        else return 1.0;
    }

    /**
     * Checks if the copter can reach its destination without needing a recharge
     * using the progressUntilRecharge function
     * @return true if the copter can reach its destination, false otherwise
     */
    public boolean canReachDest(){
        return progressUntilRecharge() == 1.0;
    }

    /**
     * The amount of distance this vehicle can go on a full charge
     * @return the amount of distance in meters
     */
    @Override
    public double distancePossible() {
        return (battery.capacity / propeller.totalCurrentDraw()) * propeller.speed;
    }

    /**
     * Compares two copters to see which one can travel further distances assuming a full battery
     * returning the name of the one that travels further or both names conjoined by a "&"
     * if they can travel the same distance
     *
     * @param anotherCopter the copter to compare against
     * @return the name of the copter that can travel further, or both names if equal
     */
    public String whoGoesFurther(Copter anotherCopter){
        double currentCopterDist = distancePossible();
        double anotherCopterDist = anotherCopter.distancePossible();

        if(currentCopterDist < anotherCopterDist){
            return anotherCopter.identifier();
        }
        else if (currentCopterDist > anotherCopterDist){
            return identifier();
        }
        else return identifier() + "&" + anotherCopter.identifier();
    }


    /**
     * Simulates the copter traveling for some time, adjusting the remaining battery and distance
     * proportionate to the amount of time spent flying
     * If the battery or distance would go negative, they are set to 0.0.
     *
     * @param seconds the time in seconds the copter travels
     */
    public void travelFor(double seconds){
        double distanceTravelled = propeller.distanceTravelled(seconds);
        double batteryUsed =  propeller.totalCurrentDraw() * seconds;

        this.metersToDestination -= distanceTravelled;
        this.battery.amountLeft -= batteryUsed;

        if(battery.amountLeft < 0.0){
            battery.amountLeft = 0;
        }
        if(metersToDestination < 0.0){
            metersToDestination = 0.0;
        }
    }

    /**
     * Overrides the equals function to compare if two copters are equal or not
     * @param o object we are comparing the current one to
     * @return true if both copters are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Copter otherCopter) {
            return name.equals(otherCopter.name) &&
                    propeller.equals(otherCopter.propeller) &&
                    battery.equals(otherCopter.battery) &&
                    Math.abs(metersToDestination - otherCopter.metersToDestination) < 0.01;
        }
            return false;
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the copter and its elements
     */
    @Override
    public String toString() {
        return "Copter:" +
                "name='" + name +
                ", propeller = " + propeller.toString() +
                ", battery = " + battery.toString() +
                ", metersToDestination = " + metersToDestination;
    }
}

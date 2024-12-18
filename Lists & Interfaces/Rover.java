package HW2;

import java.util.ArrayList;
import java.util.List;

class Rover implements Vehicle {
    /** The acronym that represents the country this rover is from */
    public String countryCode;

    /** Name of the rover */
    public String name;

    /** The chassis used by the rover */
    public Chassis chassis;

    /** The battery used by the rover */
    public Battery battery;

    /** A list of doubles representing waypoints the rover needs to travel */
    public List<Double> waypoints;

    /**
     * Constructs a Rover with specified name, countryCode, chassis, battery, and waypoints
     * @param name the name of the Rover
     * @param countryCode the acronym representing the country the rover is from
     * @param chassis the chassis used by the rover
     * @param battery the battery used by the rover
     * @param waypoints a list of doubles representing distance points the rover needs to travel
     */
    public Rover(String name, String countryCode, Chassis chassis, Battery battery, List<Double> waypoints) {
        this.countryCode = countryCode;
        this.name = name;
        this.chassis = chassis;
        this.battery = battery;
        this.waypoints = waypoints;
    }

    /** The name of the vehicle */
    @Override
    public String identifier() {
        return this.countryCode + ":" + this.name;
    }

    /**
     * Calculates the sum of the rover's waypoints
     * @return the total distance the rover needs to travel
     */
    public double totalDistance(){
        double totalDistance = 0;
        for (double waypoint : waypoints) {
            totalDistance += waypoint;
        }
        return totalDistance;
    }

    /**
     * The amount of progress towards the vehicles destination on its current power
     source
     * @return a percentage represented as numbers between 0.0 and 1.0
     */
    @Override
    public double progressUntilRecharge(){
        return Math.min(distancePotential() / totalDistance(), 1.0);
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
        return (battery.capacity / chassis.totalPowerDraw()) * chassis.averageSpeed();
    }

    /**
     * The amount of distance this vehicle can go on at current battery charge
     * @return the amount of distance in meters
     */
    public double distancePotential(){
        return (battery.amountLeft / chassis.totalPowerDraw()) * chassis.averageSpeed();
    }

    /**
     * Updates the rover's waypoints according to the distance already travelled over the given time
     * @param seconds the number of seconds the rover is moving for
     */
    public void updateWaypoints(double seconds) {
        double distanceTraveled = chassis.averageSpeed() * seconds;
        List<Double> newWaypoints = new ArrayList<>();
        for (double waypoint : waypoints) {
            if (distanceTraveled >= waypoint) {
                distanceTraveled -= waypoint;
            }
            else {
                double updatedWaypoint = waypoint - distanceTraveled;
                if (updatedWaypoint > 0) {
                    newWaypoints.add(updatedWaypoint);
                }
                distanceTraveled = 0;
            }
        }
        waypoints = newWaypoints;
    }

    /**
     * Updates the amount of battery left after travelling for the given amount of time
     * @param seconds the amount of time the battery was used for
     */
    public void updateBattery(double seconds){
        double batteryUsed = chassis.totalPowerDraw() * seconds;
        this.battery.amountLeft -= batteryUsed;
        if(battery.amountLeft < 0.0){
            battery.amountLeft = 0;
        }
    }

    /**
     * Simulates the rover moving for some given seconds and mutates its fields accordingly
     * @param seconds the time simulated for the rover to move
     */
    @Override
    public void travelFor(double seconds) {
        updateWaypoints(seconds);
        updateBattery(seconds);
    }

    /**
     * Overrides the equals function to compare if two rovers are equal or not
     * @param o object we are comparing the current one to
     * @return true if both rovers are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Rover other) {
            boolean batteryEqual = Math.abs(this.battery.amountLeft - other.battery.amountLeft) < 0.01;
            boolean chassisEqual = this.chassis.equals(other.chassis);
            double thisTotalDistance = this.totalDistance();
            double otherTotalDistance = other.totalDistance();
            boolean waypointsEqual = Math.abs(thisTotalDistance - otherTotalDistance) < 0.01;

            return this.identifier().equals(other.identifier()) && batteryEqual && chassisEqual && waypointsEqual;
        }
        return false;
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the rover and its elements
     */
    @Override
    public String toString(){
        return identifier() + " with Chassis: " + this.chassis + " and Battery: " + this.battery + " and Wapoints: " + this.waypoints;
    }
}

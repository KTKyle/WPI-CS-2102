package HW2;

import java.util.ArrayList;
import java.util.List;

public class Competition {
    /** A list of vehicles */
    public List<Vehicle> vehicles;

    /**
     * Constructs a competition with specified list of vehicles
     * @param vehicles a list of vehicles
     */
    public Competition(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * returns the identifier of the vehicle who can go the furthest on their maximum battery capacity
     * In the case of a tie, produce the name who appears first in the list.
     * In the case the list of vehicles is empty, return "No Competitors"
     * @return the name of the vehicle that goes further at full battery capacity
     */
    public String whoGoesFurthestAtFullCharge() {
        if (vehicles.isEmpty()) {
            return "No Competitors";
        }
        Vehicle furthestVehicle = vehicles.get(0);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.distancePossible() > furthestVehicle.distancePossible()) {
                furthestVehicle = vehicle;
            }
        }
        return furthestVehicle.identifier();
    }

    /**
     * Makes each vehicle in the competition travel for some number
     * of seconds and mutates their state accordingly
     * @param seconds the amount of time each vehicle is simulated travelling for
     */
    public void travelFor(double seconds) {
        for (Vehicle vehicle : vehicles) {
            vehicle.travelFor(seconds);
        }
    }

    /**
     * Finds the progress of the vehicle that is closest to requiring a recharge
     * @return the highest progress towards the destination of any vehicle before needing a recharge
     */
    public double closestProgressUntilRecharge() {
        double closer = 0.0;
        for (Vehicle vehicle : vehicles) {
            closer = Math.max(closer, vehicle.progressUntilRecharge());
        }
        return closer;
    }

    /**
     * Retrieves the identifiers of all vehicles
     * that can reach their destinations with their current battery levels
     * @return a list of the identifiers who are able to reach their destination
     */
    public List<String> allWhoReachDest() {
        List<String> reachableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.canReachDest()) {
                reachableVehicles.add(vehicle.identifier());
            }
        }
        return reachableVehicles;
    }

    /**
     * Overrides the equals function to compare if two competitions are equal or not
     * @param o object we are comparing the current one to
     * @return true if both competitions are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition other)) return false;
        return this.vehicles.equals(other.vehicles);
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the competition and its vehicles
     */
    @Override
    public String toString() {
        return "Competition with vehicles: " + vehicles;
    }
}

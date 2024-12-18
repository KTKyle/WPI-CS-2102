package HW2;

import java.util.ArrayList;
import java.util.List;

public class Chassis {
    /** Weight of the chassis */
    public double weight;

    /** A list of the amount of wheels that are on the chassis */
    List<Wheel> wheels;

    /**
     * Constructs a chassis with the specified weight and list of wheels
     * @param weight the weight of the chassis
     * @param wheels a list of wheels that represents the amount on the chassis
     */
    public Chassis(double weight, List<Wheel> wheels) {
        this.weight = weight;
        this.wheels = wheels;
    }

    /**
     * Returns the average speed of the chassis by the ratio of total speed of the wheels to their size
     * @return the chassis' average speed
     */
    public double averageSpeed() {
        double totalSpeed = 0.0;
        if (wheels == null || wheels.isEmpty()) {
            return 0.0;
        }
        for (Wheel wheel : wheels) {
            totalSpeed += wheel.wheelSpeed();
        }
        return totalSpeed / wheels.size();
    }

    /**
     * Returns the total power draw of the chassis
     * using the combined wheel power draw times the weight of the chassis
     * @return the total power draw of the chassis
     */
    public double totalPowerDraw(){
        double totalWheelPowerDraw = 0.0;
        for (Wheel wheel : wheels) {
            totalWheelPowerDraw += wheel.wheelPowerDraw();
        }
        return totalWheelPowerDraw * weight;
    }

    /**
     * Overrides the equals function to compare if two chassis' are equal or not
     * @param o object we are comparing the current one to
     * @return true if both chassis' are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chassis other)) return false;
        if (Math.abs(this.weight - other.weight) >= 0.01) return false;
        if (this.wheels.size() != other.wheels.size()) return false;
        for (int i = 0; i < this.wheels.size(); i++) {
            if (!this.wheels.get(i).equals(other.wheels.get(i))) return false;
        }
        return true;
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the chassis and its elements
     */
    @Override
    public String toString() {
        return "Chassis:" + "weight = " + weight + ", wheels = " + wheels;
    }
}

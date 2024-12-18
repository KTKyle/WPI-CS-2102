package HW2;

public class Wheel {
    /** The size of the wheel */
    public double size;

    /**
     * Constructs a wheel with the specified size
     * @param size the size of the wheel
     */
    public Wheel(double size){
        this.size = size;
    }

    /**
     * Calculates the speed of this given wheel proportionate to a tenth its size
     * @return the speed of this given wheel
     */
    public double wheelSpeed() {
        return size / 10.0;
    }

    /**
     * Calculates the power draw of this given wheel proportionate to a fifth its size
     * @return the power draw of this given wheel
     */
    public double wheelPowerDraw() {
        return size / 5.0;
    }

    /**
     * Overrides the equals function to compare if two wheels are equal or not
     * @param o object we are comparing the current one to
     * @return true if both wheels are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel other)) return false;
        return Math.abs(this.size - other.size) < 0.01;
    }

    /**
     * Overrides the toString function to provide more useful information
     * @return String that represents the wheel and its elements
     */
    @Override
    public String toString() {
        return "Wheel: size = " + size + ", speed = " + wheelSpeed() + "m/s" + ", powerDraw = " + wheelPowerDraw() + "milliAmp";
    }
}

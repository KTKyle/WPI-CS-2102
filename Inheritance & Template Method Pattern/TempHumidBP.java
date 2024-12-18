package HW3;

public class TempHumidBP extends TempHumidTemplate{

    /**
     * @return the biggest temperature for this date
     * or -999.0 if empty
     */
    @Override
    public double maxTemperature() {
        processData();
        return super.maxTemperature();
    }

    /**
     * @return the smallest temperature for this date
     * or -999.0 if empty
     */
    @Override
    public double minHumidity() {
        processData();
        return super.minHumidity();
    }
}

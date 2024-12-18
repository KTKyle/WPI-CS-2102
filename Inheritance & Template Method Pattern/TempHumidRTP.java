package HW3;

import java.util.List;

public class TempHumidRTP extends TempHumidTemplate{

    /**
     * @param data
     * date is in yyyymmdd.0 format, temperature is in F, humidity is in % (0.0-
    100.0)
     * invariants:
     * - always starts with a valid date
     * - following a date is 0 or more pairs of temperature and humidity values
    OR a single error value -999
     * - A data input list may contain multiple dates, the dates may appear in
    any order
     * - intakeData may be called multiple times with data from other sensors for
    the same date
     */
    @Override
    public void intakeData(List<Double> data){
        super.intakeData(data);
        processData();
    }
}

package HW3;

import java.util.ArrayList;
import java.util.List;

public abstract class TempHumidTemplate implements TempHumid{

    /** list of data points of type double */
    public  List<Double> data;

    /** sub-list of data that only contains temperature values */
    public List<Double> temperatures;

    /** sub-list of data that only contains humidity values */
    public List<Double> humidities;

    /**
     * Constructs an instance of the TempHumidTemplate and initializes data, temmperature, and humidities
     * list to empty ones
     */
    public TempHumidTemplate(){
        data = new ArrayList<>();
        temperatures = new ArrayList<>();
        humidities = new ArrayList<>();
    }

    /**
     * @param inputData
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
    public void intakeData(List<Double> inputData) {
        data.addAll(inputData);
    }

    /**
     * Removed invalid sensor data (-999s) as well as data not pertaining to this
     objects date from the
     */
    @Override
    public void clean() {
        data.removeIf(d -> d == -999.0 || isDate(d));
    }

    /**
     * transforms the data from the intake form (List of doubles) into a form
     convenient for output queries
     * Removes data from the intake form to avoid redundantly parsing it
     */
    @Override
    public void parse() {
        for (int i = 0; i < data.size(); i += 2) {
            temperatures.add(data.get(i));
            humidities.add(data.get(i + 1));
        }
    }

    /**
     * Mutates the parsed form to make queries fast by sorting the
     * temperature and humidity values
     */
    @Override
    public void sort() {
        temperatures.sort(Double::compareTo);
        humidities.sort(Double::compareTo);
    }

    /**
     * @return the biggest temperature for this date
     * or -999.0 if empty
     */
    @Override
    public double maxTemperature() {
        if (temperatures.isEmpty()) {
            return -999.0;
        }
        return temperatures.get(temperatures.size()-1);
    }

    /**
     * @return the smallest temperature for this date
     * or -999.0 if empty
     */
    @Override
    public double minHumidity() {
        if(humidities.isEmpty()){
            return -999.0;
        }
        return humidities.get(0);
    }

    /**
     * Processes the data by:
     * Cleaning the list using clean()
     * Parsing the list into temperature and humidity lists respectively using parse()
     * Sorts the two lists from least to greatest respectively using sort()
     */
    public void processData(){
        clean();
        parse();
        sort();
    }
}
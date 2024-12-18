package HW3;

import java.util.ArrayList;
import java.util.List;

public class TempHumidRTPByDate extends TempHumidRTP {
    /** a double representing the date we are looking for */
    double date;

    /** Constructs an instance of TempHumidRTPByDate
     * initializes using the parent class constructor and date */
    public TempHumidRTPByDate(double date) {
        super();
        this.date = date;
    }

    /**
     * Cleans data by removing all unnecessary data unrelated to the date given and replaces the
     * old data with only the relevant data
     */
    @Override
    public void clean() {
        List<Double> cleanData = new ArrayList<>();
        boolean currDate = false;
        for (Double d : data) {
            if (isDate(d)) {
                currDate = (d == date);
            } else if (currDate) {
                cleanData.add(d);
            }
        }
        data = cleanData;
        super.clean();
    }
}
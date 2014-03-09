package fr.cma.aurora.util;

import fr.cma.aurora.model.Measure;
import org.joda.time.DateTime;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static org.joda.time.DateTime.*;
import static org.joda.time.format.DateTimeFormat.*;

/**
 * For data files previous to 2007.
 */
public class MeasureParserLegacy {
    /**
     * A7	NOAA POES Satellite number
     * A3	(S) or (N) - hemisphere
     * I3	Day of year
     * I4	UT hour and minute
     * F8.1	Estimated Hemispheric Power in gigawatts
     * I3	Hemispheric Power Index (activity level)
     * F8.3	Normalizing factor
     */
    public Measure parseLine(int year, String line) {
        Measure measure = new Measure();
        measure.setSatellite(line.substring(0, 7));
        measure.setHemisphere(line.substring(8, 9));
        measure.setDate(parseDate(year, line));
        measure.setPower(parseFloat(line.substring(17, 25)));
        measure.setActivityLevel(parseInt(line.substring(25, 28).trim()));
        measure.setNormalizingFactor(parseFloat(line.substring(28)));
        return measure;
    }

    private DateTime parseDate(int year, String line) {
        String dateToParse = year + line.substring(10, 17).replace(' ', '0');
        return parse(dateToParse, forPattern("YYYYDDDHHmm").withZoneUTC());
    }
}

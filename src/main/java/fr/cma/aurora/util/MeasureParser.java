package fr.cma.aurora.util;

import fr.cma.aurora.model.Measure;

import java.io.IOException;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static org.joda.time.DateTime.parse;
import static org.joda.time.format.DateTimeFormat.forPattern;

/**
 * For data files since 2007.
 */
public class MeasureParser {
    /**
     * A19 	Date and UT at the center of the polar pass as YYYY-MM-DD hh:mm:ss
     * 1X	(space)
     * A7	NOAA POES Satellite number
     * 1X	(space)
     * A3	(S) or (N) - hemisphere
     * I3	Hemispheric Power Index (activity level)
     * F7.2	Estimated Hemispheric Power in gigawatts
     * F7.2	Normalizing factor
     */
    public Measure parseLine(String line) throws IOException {
        Measure measure = new Measure();
        measure.setDate(parse(line.substring(0, 19), forPattern("Y-M-d H:m:s").withZoneUTC()));
        measure.setSatellite(line.substring(20, 27));
        measure.setHemisphere(line.substring(29, 30));
        measure.setActivityLevel(parseInt(line.substring(31, 34).trim()));
        measure.setPower(parseFloat(line.substring(34, 41)));
        measure.setNormalizingFactor(parseFloat(line.substring(41, 48)));
        return measure;
    }
}

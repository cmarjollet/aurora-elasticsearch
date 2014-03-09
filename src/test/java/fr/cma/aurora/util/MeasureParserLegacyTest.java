package fr.cma.aurora.util;

import fr.cma.aurora.model.Measure;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MeasureParserLegacyTest {
    private MeasureParserLegacy parser = new MeasureParserLegacy();

    @Test
    public void should_parse_legacy_data() {
        // Given
        int year = 1978;
        String line = "TIROS-N(S)3061524    40.1  8   1.041";

        // When
        Measure measure = parser.parseLine(year, line);

        // Then
        assertThat(measure).isNotNull();
        assertThat(measure.getSatellite()).isEqualTo("TIROS-N");
        assertThat(measure.getHemisphere()).isEqualTo("S");
        assertThat(measure.getDate().toString()).isEqualTo("1978-11-02T15:24:00.000Z");
        assertThat(measure.getPower()).isEqualTo(40.1f);
        assertThat(measure.getActivityLevel()).isEqualTo(8);
        assertThat(measure.getNormalizingFactor()).isEqualTo(1.041f);
    }

    @Test
    public void should_parse_legacy_data_with_blanks_in_date() {
        // Given
        int year = 1979;
        String line = "TIROS-N(S)  10324     4.9  3   1.573";

        // When
        Measure measure = parser.parseLine(year, line);

        // Then
        assertThat(measure).isNotNull();
        assertThat(measure.getSatellite()).isEqualTo("TIROS-N");
        assertThat(measure.getHemisphere()).isEqualTo("S");
        assertThat(measure.getDate().toString()).isEqualTo("1979-01-01T03:24:00.000Z");
        assertThat(measure.getPower()).isEqualTo(4.9f);
        assertThat(measure.getActivityLevel()).isEqualTo(3);
        assertThat(measure.getNormalizingFactor()).isEqualTo(1.573f);
    }
}

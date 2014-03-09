package fr.cma.aurora.util;

import fr.cma.aurora.model.Measure;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MeasureListIterator implements Iterator<Measure> {
    private final int year;
    private final BufferedReader reader;
    private String currentLine;
    private boolean finished = false;

    public MeasureListIterator(int year, InputStream inputStream) {
        this.year = year;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public boolean hasNext() {
        if (currentLine != null) {
            return true;
        } else if (finished) {
            return false;
        } else {
            return readNextLine();
        }
    }

    private boolean readNextLine() {
        try {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    close();
                    finished = true;
                    return false;
                } else if (isValidLine(line)) {
                    currentLine = line;
                    return true;
                }
            }
        } catch (IOException e) {
            close();
            throw new IllegalStateException(e);
        }
    }

    private boolean isValidLine(String line) {
        return !line.isEmpty()
            && !line.startsWith(":")
            && !line.startsWith("#")
            && !(line.length() == 4);
    }

    @Override
    public Measure next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String line = currentLine;
        currentLine = null;
        return parseLine(line);
    }

    private Measure parseLine(String line) {
        try {
            // Data format has changed in 2007
            if (year < 2007) {
                return new MeasureParserLegacy().parseLine(year, line);
            } else {
                return new MeasureParser().parseLine(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void close() {
        IOUtils.closeQuietly(reader);
    }
}

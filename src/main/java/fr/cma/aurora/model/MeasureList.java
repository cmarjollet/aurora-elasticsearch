package fr.cma.aurora.model;

import fr.cma.aurora.util.MeasureListIterator;

import java.io.InputStream;
import java.util.Iterator;

public class MeasureList implements Iterable<Measure> {
    private final int year;
    private final InputStream inputStream;

    public MeasureList(int year, InputStream inputStream) {
        this.year = year;
        this.inputStream = inputStream;
    }

    @Override
    public Iterator<Measure> iterator() {
        return new MeasureListIterator(year, inputStream);
    }
}

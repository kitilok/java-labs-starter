package edu.course.lab05;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class LoadResult {
    private final List<DataSample> samples;
    private final List<ValidationError> errors;

    public LoadResult(List<DataSample> samples, List<ValidationError> errors) {
        this.samples = new ArrayList<>(samples);
        this.errors = new ArrayList<>(errors);
    }

    public List<DataSample> getSamples() {
        return Collections.unmodifiableList(samples);
    }

    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}

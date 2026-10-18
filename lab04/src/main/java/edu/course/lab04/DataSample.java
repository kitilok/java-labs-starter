package edu.course.lab04;

import java.util.Objects;

public class DataSample {

    private final String label;

    public DataSample(String label){
        this.label = Objects.requireNonNull(label);
    }

    public String getLabel(){
        return label;
    }
}

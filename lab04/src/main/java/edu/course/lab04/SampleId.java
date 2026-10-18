package edu.course.lab04;

import java.util.Objects;

public class SampleId {

    private final String datasetname;
    private final int observationNumber;

    public SampleId(String datasetname, int observationNumber){
        this.datasetname = Objects.requireNonNull(datasetname);

        if (datasetname.isBlank() || observationNumber <=0 ){
            throw new IllegalArgumentException();
        }

        this.observationNumber = observationNumber;
    }

    public String getDatasetname(){
        return datasetname;
    }

    public int getObservationNumber() {
        return observationNumber;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (!(obj instanceof SampleId)){
            return false;
        }

        SampleId other = (SampleId) obj;

        return observationNumber == other.observationNumber
                && datasetname.equals(other.datasetname);
    }

    @Override
    public int hashCode(){
            return Objects.hash(datasetname, observationNumber);
    }
}

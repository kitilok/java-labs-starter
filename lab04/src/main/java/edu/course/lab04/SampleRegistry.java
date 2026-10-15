package edu.course.lab04;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SampleRegistry {

    private final Map<SampleId, DataSample> samples = new HashMap<>();

    public void addOrReplace(SampleId id, DataSample sample){
        samples.put(
                Objects.requireNonNull(id),
                Objects.requireNonNull(sample)
        );
    }

    public DataSample get(SampleId id){
        return samples.get(id);
    }

    public DataSample remove(SampleId id){
        return samples.remove(id);
    }

    public boolean contains(SampleId id){
        return samples.containsKey(id);
    }

    public int size(){
        return samples.size();
    }
}

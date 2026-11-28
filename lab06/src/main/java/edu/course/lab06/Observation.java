package edu.course.lab06;

public class Observation {

    private final String id;
    private final String source;
    private final int featureCount;
    private final ObservationStatus status;

    public Observation(String id, String source, int featureCount,
                       ObservationStatus status) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be empty");
        }
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("source must not be empty");
        }
        if (featureCount < 0) {
            throw new IllegalArgumentException("featureCount must not be negative");
        }
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }

        this.id = id;
        this.source = source;
        this.featureCount = featureCount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    public ObservationStatus getStatus() {
        return status;
    }
}

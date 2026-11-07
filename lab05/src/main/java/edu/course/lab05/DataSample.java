package edu.course.lab05;

public class DataSample {
    private final String id;
    private final String label;
    private final String source;
    private final int featureCount;
    private final SampleStatus status;

    public DataSample(
            String id,
            String label,
            String source,
            int featureCount,
            SampleStatus status
    ) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be empty");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label must not be empty");
        }
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("source must be positive");
        }
        if (featureCount <= 0) {
            throw new IllegalArgumentException("source must not be empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("source must not be null");
        }

        this.id = id;
        this.label = label;
        this.source = source;
        this.featureCount = featureCount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getSource() {
        return source;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    public SampleStatus getStatus() {
        return status;
    }
}

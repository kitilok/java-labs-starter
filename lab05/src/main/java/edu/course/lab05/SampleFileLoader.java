    package edu.course.lab05;

    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.ArrayList;
    import java.util.List;

    public class SampleFileLoader {

        private static final String HEADER = "id;label;source;featureCount;status";

        public LoadResult load(Path path) {
            List<String> lines;

            try {
                lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new DataLoadingException("cannot read file: " + path, e);
            }

            if (lines.isEmpty() || !lines.get(0).equals(HEADER)) {
                throw new DataLoadingException("invalid file header", new IllegalArgumentException("expected header: " + HEADER));
            }

            List<DataSample> samples = new ArrayList<>();
            List<ValidationError> errors = new ArrayList<>();

            String[] fieldNames = {
                    "id", "label", "source", "featureCount", "status"
            };

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.isBlank()) {
                    continue;
                }

                int lineNumber = i + 1;
                String[] fields = line.split(";", -1);

                if (fields.length != 5) {
                    errors.add(new ValidationError(lineNumber, "columns", "expected 5 columns, got " + fields.length));
                    continue;
                }

                boolean hasEmptyField = false;

                for (int j = 0; j < fields.length; j++) {
                    fields[j] = fields[j].trim();

                    if (fields[j].isEmpty()) {
                        errors.add(new ValidationError(lineNumber, fieldNames[j], "field must not be empty"));
                        hasEmptyField = true;
                    }
                }

                if (hasEmptyField) {
                    continue;
                }

                int featureCount;

                try {
                    featureCount = Integer.parseInt(fields[3]);
                } catch (NumberFormatException e) {
                    errors.add(new ValidationError(lineNumber, "featureCount", "must be an integer"));
                    continue;
                }

                SampleStatus status;

                try {
                    status = SampleStatus.valueOf(fields[4]);
                } catch (IllegalArgumentException e) {
                    errors.add(new ValidationError(lineNumber, "status", "unknown status " + fields[4]));
                    continue;
                }

                try {
                    DataSample sample = new DataSample(fields[0], fields[1], fields[2], featureCount, status);

                    samples.add(sample);
                } catch (IllegalArgumentException e) {
                    errors.add(new ValidationError(lineNumber, "record", e.getMessage()));
                }
            }

            return new LoadResult(samples, errors);
        }
    }

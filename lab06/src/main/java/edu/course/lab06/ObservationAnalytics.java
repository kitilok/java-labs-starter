package edu.course.lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObservationAnalytics {

    public Map<ObservationStatus, Integer> countByStatus(
            List<Observation> observations) {

        Map<ObservationStatus, Integer> result = new LinkedHashMap<>();

        for (Observation observation : observations) {
            ObservationStatus status = observation.getStatus();
            result.put(status, result.getOrDefault(status, 0) + 1);
        }

        return result;
    }

    public Set<String> uniqueSources(List<Observation> observations) {

        Set<String> result = new LinkedHashSet<>();

        for (Observation observation : observations) {
            result.add(observation.getSource());
        }

        return result;
    }

    public List<Observation> sortedByFeatureCountThenId(
            List<Observation> observations) {

        List<Observation> result = new ArrayList<>(observations);

        result.sort(
                Comparator.comparingInt(Observation::getFeatureCount)
                        .thenComparing(Observation::getId)
        );

        return result;
    }
}

package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ObservationAnalyticsTest {

    private final ObservationAnalytics analytics = new ObservationAnalytics();

    private Observation create(String id, String source, int featureCount,
                               ObservationStatus status) {
        return new Observation(id, source, featureCount, status);
    }

    @Test
    void countsObservationsByStatus() {
        List<Observation> observations = List.of(
                create("1", "sensor-A", 5, ObservationStatus.NEW),
                create("2", "sensor-B", 3, ObservationStatus.READY),
                create("3", "sensor-A", 7, ObservationStatus.NEW),
                create("4", "sensor-C", 2, ObservationStatus.PROCESSING)
        );

        Map<ObservationStatus, Integer> result =
                analytics.countByStatus(observations);

        assertEquals(2, result.get(ObservationStatus.NEW));
        assertEquals(1, result.get(ObservationStatus.READY));
        assertEquals(1, result.get(ObservationStatus.PROCESSING));
    }

    @Test
    void returnsUniqueSources() {
        List<Observation> observations = List.of(
                create("1", "sensor-A", 5, ObservationStatus.NEW),
                create("2", "sensor-B", 3, ObservationStatus.READY),
                create("3", "sensor-A", 7, ObservationStatus.NEW),
                create("4", "sensor-C", 2, ObservationStatus.PROCESSING),
                create("5", "sensor-B", 4, ObservationStatus.READY)
        );

        Set<String> result = analytics.uniqueSources(observations);

        assertEquals(3, result.size());
        assertEquals(
                Set.of("sensor-A", "sensor-B", "sensor-C"),
                result
        );
    }

    @Test
    void sortsByFeatureCountThenId() {
        List<Observation> observations = List.of(
                create("C", "sensor-A", 5, ObservationStatus.NEW),
                create("A", "sensor-B", 3, ObservationStatus.READY),
                create("B", "sensor-C", 3, ObservationStatus.PROCESSING),
                create("D", "sensor-D", 7, ObservationStatus.READY)
        );

        List<Observation> result =
                analytics.sortedByFeatureCountThenId(observations);

        assertEquals("A", result.get(0).getId());
        assertEquals("B", result.get(1).getId());
        assertEquals("C", result.get(2).getId());
        assertEquals("D", result.get(3).getId());
    }

    @Test
    void sortingDoesNotModifyOriginalList() {
        List<Observation> observations = new ArrayList<>(List.of(
                create("C", "sensor-A", 5, ObservationStatus.NEW),
                create("A", "sensor-B", 3, ObservationStatus.READY),
                create("B", "sensor-C", 3, ObservationStatus.PROCESSING)
        ));

        List<String> originalOrder = observations.stream()
                .map(Observation::getId)
                .toList();

        analytics.sortedByFeatureCountThenId(observations);

        List<String> currentOrder = observations.stream()
                .map(Observation::getId)
                .toList();

        assertEquals(originalOrder, currentOrder);
    }

    @Test
    void worksWithEmptyList() {
        List<Observation> observations = List.of();

        assertTrue(analytics.countByStatus(observations).isEmpty());
        assertTrue(analytics.uniqueSources(observations).isEmpty());
        assertTrue(analytics.sortedByFeatureCountThenId(observations).isEmpty());
    }

    @Test
    void sortedResultIsIndependentList() {
        List<Observation> observations = new ArrayList<>(List.of(
                create("B", "sensor-A", 5, ObservationStatus.NEW),
                create("A", "sensor-B", 3, ObservationStatus.READY)
        ));

        List<Observation> result =
                analytics.sortedByFeatureCountThenId(observations);

        assertNotSame(observations, result);
    }
}
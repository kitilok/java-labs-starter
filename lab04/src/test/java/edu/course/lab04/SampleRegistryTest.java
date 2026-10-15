package edu.course.lab04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleRegistryTest {

    @Test
    void equalKeyFindsSample() {
        SampleRegistry registry = new SampleRegistry();

        registry.addOrReplace(
                new SampleId("training", 10),
                new DataSample("cat")
        );

        DataSample result = registry.get(
                new SampleId("training", 10)
        );

        assertEquals("cat", result.getLabel());
    }

    @Test
    void addingEqualKeyReplacesSampleWithoutIncreasingSize() {
        SampleRegistry registry = new SampleRegistry();

        registry.addOrReplace(
                new SampleId("training", 10),
                new DataSample("cat")
        );

        registry.addOrReplace(
                new SampleId("training", 10),
                new DataSample("dog")
        );

        assertEquals(1, registry.size());
        assertEquals(
                "dog",
                registry.get(new SampleId("training", 10)).getLabel()
        );
    }

    @Test
    void differentKeysStoreDifferentSamples() {
        SampleRegistry registry = new SampleRegistry();

        registry.addOrReplace(
                new SampleId("training", 10),
                new DataSample("cat")
        );

        registry.addOrReplace(
                new SampleId("validation", 10),
                new DataSample("dog")
        );

        assertEquals(2, registry.size());
        assertEquals(
                "cat",
                registry.get(new SampleId("training", 10)).getLabel()
        );
        assertEquals(
                "dog",
                registry.get(new SampleId("validation", 10)).getLabel()
        );
    }

    @Test
    void registrySupportsContainsAndRemove() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("training", 10);

        registry.addOrReplace(id, new DataSample("cat"));

        assertTrue(registry.contains(id));
        assertEquals("cat", registry.remove(id).getLabel());
        assertFalse(registry.contains(id));
        assertNull(registry.get(id));
        assertEquals(0, registry.size());
    }

    @Test
    void equalsReturnsFalseForNullAndAnotherType() {
        SampleId id = new SampleId("training", 10);

        assertFalse(id.equals(null));
        assertFalse(id.equals("training-10"));
    }

    @Test
    void equalKeysHaveEqualHashCodes() {
        SampleId first = new SampleId("training", 10);
        SampleId second = new SampleId("training", 10);

        assertEquals(first.hashCode(), second.hashCode());
        assertEquals(first, second);
    }
}

package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class HashMapContractTest {

    @Test
    void unequalKeysWithSameHashCodeKeepDifferentValues() {
        CollisionKey first = new CollisionKey("first");
        CollisionKey second = new CollisionKey("second");

        Map<CollisionKey, String> map = new HashMap<>();
        map.put(first, "Value 1");
        map.put(second, "Value 2");

        assertNotEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals("Value 1", map.get(first));
        assertEquals("Value 2", map.get(second));
        assertEquals(2, map.size());
    }

    @Test
    void changingKeyCanBreakHashMapLookup() {
        MutableKey key = new MutableKey(1);

        Map<MutableKey, String> map = new HashMap<>();
        map.put(key, "Stored value");

        assertEquals("Stored value", map.get(key));

        key.setId(2);

        assertNull(map.get(key));
        assertEquals(1, map.size());
    }

    private static class CollisionKey {

        private final String value;

        CollisionKey(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof CollisionKey)) {
                return false;
            }

            CollisionKey other = (CollisionKey) obj;
            return Objects.equals(value, other.value);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    private static class MutableKey {

        private int id;

        MutableKey(int id) {
            this.id = id;
        }

        void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof MutableKey)) {
                return false;
            }

            MutableKey other = (MutableKey) obj;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }
}

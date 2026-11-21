package edu.course.lab06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void storesString() {
        Box<String> box = new Box<>("Hello");

        assertEquals("Hello", box.get());
    }

    @Test
    void changesString() {
        Box<String> box = new Box<>("Hello");

        box.set("World");

        assertEquals("World", box.get());
    }

    @Test
    void storesInteger() {
        Box<Integer> box = new Box<>(100);

        assertEquals(100, box.get());
    }

    @Test
    void changesInteger() {
        Box<Integer> box = new Box<>(100);

        box.set(200);

        assertEquals(200, box.get());
    }
}
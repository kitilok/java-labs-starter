package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushAddsElements() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    void peekReturnsTopWithoutRemoving() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popRemovesTopElement() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void stackWorksWithInteger() {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void popOnEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void peekOnEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void pushNullThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(NullPointerException.class, () -> stack.push(null));
    }
}
package edu.course.lab06;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public final class Stack<T> {

    private final ArrayList<T> elements = new ArrayList<>();

    public void push(T value) {
        if (value == null) {
            throw new NullPointerException("value must not be null");
        }
        elements.add(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        return elements.remove(elements.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        return elements.get(elements.size() - 1);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
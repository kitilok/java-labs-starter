package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void equalBooksFindValueInHashMap() {
        Book first = new Book("978-123", "Java");
        Book second = new Book("978-123", "Java for beginners");

        Map<Book, String> books = new HashMap<>();
        books.put(first, "Found");

        assertEquals("Found", books.get(second));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book first = new Book("978-123", "Java");
        Book second = new Book("978-456", "Java");

        assertNotEquals(first, second);
    }

    @Test
    void equalsReturnsFalseForNullAndAnotherType() {
        Book book = new Book("978-123", "Java");

        assertFalse(book.equals(null));
        assertFalse(book.equals("978-123"));
    }

    @Test
    void equalBooksHaveEqualHashCodes() {
        Book first = new Book("978-123", "Java");
        Book second = new Book("978-123", "Another title");

        assertEquals(first.hashCode(), second.hashCode());
    }
}

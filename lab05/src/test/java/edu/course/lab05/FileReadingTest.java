package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReadingTest {

    @TempDir
    Path tempDir;

    @Test
    void readsUtf8File() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        assertEquals(List.of("10", "bad", "20"), lines);
    }

    @Test
    void badNumberThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("bad"));
    }
}
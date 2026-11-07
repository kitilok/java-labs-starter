package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SampleFileLoaderTest {

    @TempDir
    Path tempDir;

    private final SampleFileLoader loader = new SampleFileLoader();

    private Path createFile(String... lines) throws IOException {
        Path file = tempDir.resolve("samples-" + System.nanoTime() + ".txt");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsValidFile() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;128;READY",
                "S-102;dog;camera-2;64;NEW"
        );

        LoadResult result = loader.load(file);

        assertEquals(2, result.getSamples().size());
        assertTrue(result.getErrors().isEmpty());
        assertEquals("S-101", result.getSamples().get(0).getId());
    }

    @Test
    void supportsUnicode() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;кошка;камера-1;128;READY"
        );

        LoadResult result = loader.load(file);

        assertEquals("кошка", result.getSamples().get(0).getLabel());
        assertEquals("камера-1", result.getSamples().get(0).getSource());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;;128;READY"
        );

        LoadResult result = loader.load(file);

        assertTrue(result.getSamples().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals("source", result.getErrors().get(0).getField());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;abc;READY"
        );

        LoadResult result = loader.load(file);

        assertEquals("featureCount", result.getErrors().get(0).getField());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;128;UNKNOWN"
        );

        LoadResult result = loader.load(file);

        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongNumberOfColumns() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;128"
        );

        LoadResult result = loader.load(file);

        assertEquals("columns", result.getErrors().get(0).getField());
    }

    @Test
    void continuesAfterInvalidRow() throws IOException {
        Path file = createFile(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;128;READY",
                "S-102;dog;camera-2;abc;READY",
                "S-103;bird;camera-3;32;NEW"
        );

        LoadResult result = loader.load(file);

        assertEquals(2, result.getSamples().size());
        assertEquals(1, result.getErrors().size());
        assertEquals("S-101", result.getSamples().get(0).getId());
        assertEquals("S-103", result.getSamples().get(1).getId());
    }

    @Test
    void missingFileThrowsDataLoadingException() {
        Path file = tempDir.resolve("missing.txt");

        DataLoadingException exception = assertThrows(
                DataLoadingException.class,
                () -> loader.load(file)
        );

        assertNotNull(exception.getCause());
        assertInstanceOf(IOException.class, exception.getCause());
    }
}

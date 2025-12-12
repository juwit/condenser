package io.codeka.condenser.renderers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleArticleRendererTest {

    @Test
    @DisplayName("render should print the generated article to standard output")
    void render_shouldPrintGeneratedArticleToStdout() {
        // Given
        String content = "Hello, world!";
        ConsoleArticleRenderer renderer = new ConsoleArticleRenderer();

        // Capture System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PrintStream capture = new PrintStream(baos)) {
            System.setOut(capture);

            // When
            renderer.render(content);

            // Then
            assertEquals(content, baos.toString());
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }
}

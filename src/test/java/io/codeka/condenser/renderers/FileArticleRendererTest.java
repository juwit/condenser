package io.codeka.condenser.renderers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileArticleRendererTest {

    @Test
    @DisplayName("render should write the generated article to the target file, creating directories if needed")
    void render_shouldWriteContentToFile(@TempDir Path tempDir) throws IOException {
        // Given
        String content = "Hello, file!";
        Path output = tempDir.resolve("nested/dir/article.md");
        FileArticleRenderer renderer = new FileArticleRenderer(output);

        // When
        renderer.render(content);

        // Then
        String actual = Files.readString(output);
        assertEquals(content, actual);
    }
}

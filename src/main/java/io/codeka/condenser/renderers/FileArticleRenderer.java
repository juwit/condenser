package io.codeka.condenser.renderers;

import io.codeka.condenser.domain.ArticleRenderer;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Renderer that writes the generated article content to a file on disk.
 */
public class FileArticleRenderer implements ArticleRenderer {

    private final Path outputPath;

    /**
     * Creates a renderer that writes to the provided {@code outputPath}.
     *
     * @param outputPath the path to the file that will receive the content
     */
    public FileArticleRenderer(Path outputPath) {
        if (outputPath == null) {
            throw new IllegalArgumentException("outputPath cannot be null");
        }
        this.outputPath = outputPath;
    }

    @Override
    public void render(@NonNull String generatedArticle) {
        try {
            Path parent = outputPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(outputPath, generatedArticle == null ? "" : generatedArticle, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write article to file: " + outputPath, e);
        }
    }
}

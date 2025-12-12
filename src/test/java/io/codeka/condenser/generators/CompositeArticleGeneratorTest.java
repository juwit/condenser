package io.codeka.condenser.generators;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.ArticleGenerator;
import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompositeArticleGeneratorTest {

    private Article sampleArticle() throws Exception {
        // Minimal valid article (introduction + at least one link)
        Link link = new Link(new URI("https://example.com"), "Example", Tag.JAVA, Optional.empty(), Optional.empty(), "Analysis");
        return new Article("Intro", List.of(link));
    }

    @Test
    @DisplayName("generate should concatenate delegate generators in order")
    void generate_shouldConcatenateInOrder() throws Exception {
        // Given
        ArticleGenerator genA = _ -> "A";
        ArticleGenerator genB = _ -> "B";
        CompositeArticleGenerator composite = new CompositeArticleGenerator(List.of(genA, genB));

        // When
        String result = composite.generate(sampleArticle());

        // Then
        assertEquals("AB", result);
    }

}

package io.codeka.condenser.markdown;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownArticleGeneratorTest {

    @Test
    @DisplayName("generate should produce expected markdown body")
    void generate_shouldProduceExpectedMarkdownBody() throws Exception {
        // Given
        Article article = new Article(
                "Intro",
                List.of(
                        new Link(new URI("https://example.com/java"), "Java article", Tag.JAVA, Optional.empty(), Optional.empty(), "analysis"),
                        new Link(new URI("https://example.com/linux"), "Linux article", Tag.LINUX, Optional.empty(), Optional.empty(), "analysis")
                )
        );

        String expected = """
                Intro

                <!--more-->

                ## ☕ Java
                * Java article

                ## 🐧 Linux
                * Linux article

                """;

        // When
        String actual = new MarkdownArticleGenerator().generate(article);

        // Then
        assertEquals(expected, actual);
    }
}

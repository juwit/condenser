package io.codeka.condenser.generators.markdown;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;
import io.codeka.condenser.domain.Source;
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
                        new Link(new URI("https://example.com/java"), "Java article", Tag.JAVA, Optional.empty(), Optional.empty(), "Java article analysis"),
                        new Link(new URI("https://example.com/linux"), "Linux article", Tag.LINUX, Optional.empty(), Optional.empty(), "Linux article analysis")
                )
        );

        String expected = """
                Intro

                <!--more-->

                ## ☕ Java
                * [Java article](https://example.com/java)

                > Java article analysis

                ## 🐧 Linux
                * [Linux article](https://example.com/linux)

                > Linux article analysis

                """;

        // When
        String actual = new MarkdownArticleGenerator().generate(article);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generate should include author link when present")
    void generate_shouldIncludeAuthorLinkWhenPresent() throws Exception {
        // Given
        Article article = new Article(
                "Intro",
                List.of(
                        new Link(
                                new URI("https://example.com/java"),
                                "Java article",
                                Tag.JAVA,
                                Optional.of(new Source("John Doe", new URI("https://john.doe.dev"))),
                                Optional.empty(),
                                "Java article analysis")
                )
        );

        String expected = """
                Intro

                <!--more-->

                ## ☕ Java
                * [Java article](https://example.com/java) par [John Doe](https://john.doe.dev)

                > Java article analysis

                """;

        // When
        String actual = new MarkdownArticleGenerator().generate(article);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generate should include source link when present")
    void generate_shouldIncludeSourceLinkWhenPresent() throws Exception {
        // Given
        Article article = new Article(
                "Intro",
                List.of(
                        new Link(
                                new URI("https://example.com/java"),
                                "Java article",
                                Tag.JAVA,
                                Optional.empty(),
                                Optional.of(new Source("Hacker News", new URI("https://news.ycombinator.com"))),
                                "Java article analysis")
                )
        );

        String expected = """
                Intro

                <!--more-->

                ## ☕ Java
                * [Java article](https://example.com/java) _via_ [Hacker News](https://news.ycombinator.com)

                > Java article analysis

                """;

        // When
        String actual = new MarkdownArticleGenerator().generate(article);

        // Then
        assertEquals(expected, actual);
    }
}

package io.codeka.condenser.generators.frontmatter;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.ArticleDateSupplier;
import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrontmatterArticleGeneratorTest {

    private Article sampleArticle() throws Exception {
        return new Article(
                "Introduction",
                List.of(
                        new Link(new URI("https://example.com/db"), "DB", Tag.DATABASES, Optional.empty(), Optional.empty(), "Analysis"),
                        new Link(new URI("https://example.com/ia"), "IA", Tag.IA, Optional.empty(), Optional.empty(), "Analysis"),
                        new Link(new URI("https://example.com/internet"), "Internet", Tag.INTERNET, Optional.empty(), Optional.empty(), "Analysis"),
                        new Link(new URI("https://example.com/java"), "Java", Tag.JAVA, Optional.empty(), Optional.empty(), "Analysis"),
                        new Link(new URI("https://example.com/linux"), "Linux", Tag.LINUX, Optional.empty(), Optional.empty(), "Analysis")
                )
        );
    }

    private String expected(LocalDate date, String title) {
        return String.format("""
                    ---
                    date: %s
                    language: fr
                    title: %s
                    series: La veille de Wittouck
                    tags:
                      - databases
                      - ia
                      - internet
                      - java
                      - linux
                    draft: true
                    ---
                    """, date.toString(), title);
    }

    @Test
    @DisplayName( "generate should produce expected frontmatter")
    void generate_shouldProduceExpectedFrontmatter() throws Exception {
        // Given an article with tags: ia, databases, internet, java, linux
        Article article = sampleArticle();

        String expected = """
                    ---
                    date: 2025-11-30
                    language: fr
                    title: La veille de Wittouck - Fin novembre 2025
                    series: La veille de Wittouck
                    tags:
                      - databases
                      - ia
                      - internet
                      - java
                      - linux
                    draft: true
                    ---
                    """;

        ArticleDateSupplier dateSupplier = () -> LocalDate.of(2025,11,30);

        // When
        String actual = new FrontmatterArticleGenerator(dateSupplier).generate(article);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("title should be 'Fin' for beginning of month (day 4)")
    void title_shouldBeFin_forBeginningBoundary() throws Exception {
        Article article = sampleArticle();
        LocalDate date = LocalDate.of(2025, 11, 4);
        String expected = expected(date, "La veille de Wittouck - Fin novembre 2025");

        ArticleDateSupplier dateSupplier = () -> date;
        String actual = new FrontmatterArticleGenerator(dateSupplier).generate(article);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("title should be 'Début' starting day 5")
    void title_shouldBeDebut_fromDay5() throws Exception {
        Article article = sampleArticle();
        LocalDate date = LocalDate.of(2025, 11, 5);
        String expected = expected(date, "La veille de Wittouck - Début novembre 2025");

        ArticleDateSupplier dateSupplier = () -> date;
        String actual = new FrontmatterArticleGenerator(dateSupplier).generate(article);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("title should remain 'Début' up to day 25")
    void title_shouldBeDebut_untilDay25() throws Exception {
        Article article = sampleArticle();
        LocalDate date = LocalDate.of(2025, 11, 25);
        String expected = expected(date, "La veille de Wittouck - Début novembre 2025");

        ArticleDateSupplier dateSupplier = () -> date;
        String actual = new FrontmatterArticleGenerator(dateSupplier).generate(article);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("title should switch to 'Fin' from day 26")
    void title_shouldBeFin_fromDay26() throws Exception {
        Article article = sampleArticle();
        LocalDate date = LocalDate.of(2025, 11, 26);
        String expected = expected(date, "La veille de Wittouck - Fin novembre 2025");

        ArticleDateSupplier dateSupplier = () -> date;
        String actual = new FrontmatterArticleGenerator(dateSupplier).generate(article);

        assertEquals(expected, actual);
    }
}

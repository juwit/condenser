package io.codeka.condenser.domain;

/**
 * Generates a article from the given domain.
 * Each implementation can generate a different article format.
 * The result is a String containing the generated article.
 */
public interface ArticleGenerator {

    String generate(Article article);

}

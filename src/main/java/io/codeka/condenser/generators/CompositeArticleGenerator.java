package io.codeka.condenser.generators;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.ArticleGenerator;

import java.util.List;
import java.util.Objects;

/**
 * An {@link ArticleGenerator} that delegates to multiple generators and
 * concatenates their outputs in order.
 */
public class CompositeArticleGenerator implements ArticleGenerator {

    private final List<ArticleGenerator> generators;

    public CompositeArticleGenerator(List<ArticleGenerator> generators) {
        Objects.requireNonNull(generators, "generators must not be null");
        if (generators.isEmpty()) {
            throw new IllegalArgumentException("generators must not be empty");
        }
        this.generators = generators;
    }

    @Override
    public String generate(Article article) {
        Objects.requireNonNull(article, "article must not be null");

        StringBuilder sb = new StringBuilder();
        for (ArticleGenerator generator : generators) {
            sb.append(generator.generate(article));
        }
        return sb.toString();
    }
}

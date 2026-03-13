package io.codeka.condenser.renderers;

import io.codeka.condenser.domain.ArticleRenderer;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

/**
 * An article renderer that delegates to multiple other renderers.
 */
public class CompositeArticleRenderer implements ArticleRenderer {

    private final Collection<ArticleRenderer> renderers;

    public CompositeArticleRenderer(@NonNull Collection<ArticleRenderer> renderers) {
        this.renderers = renderers;
    }

    @Override
    public void render(@NonNull String generatedArticle) {
        renderers.forEach(renderer -> renderer.render(generatedArticle));
    }
}

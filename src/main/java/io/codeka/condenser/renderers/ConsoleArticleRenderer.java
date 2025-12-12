package io.codeka.condenser.renderers;

import io.codeka.condenser.domain.ArticleRenderer;
import org.jspecify.annotations.NonNull;

/**
 * Simple renderer that writes the generated article to the standard output.
 */
public class ConsoleArticleRenderer implements ArticleRenderer {

    @Override
    public void render(@NonNull String generatedArticle) {
        System.out.print(generatedArticle);
    }
}

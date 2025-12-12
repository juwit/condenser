package io.codeka.condenser.renderers;

import io.codeka.condenser.domain.ArticleRenderer;

/**
 * Simple renderer that writes the generated article to the standard output.
 */
public class ConsoleArticleRenderer implements ArticleRenderer {

    @Override
    public void render(String generatedArticle) {
        System.out.print(generatedArticle);
    }
}

package io.codeka.condenser.domain;

import org.jspecify.annotations.NonNull;

/**
 * Renders a generated article.
 * The article could be written in a file (in the local file system), or as a commit to some repository.
 */
public interface ArticleRenderer {

    void render(@NonNull String generatedArticle);
}

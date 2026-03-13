package io.codeka.condenser.renderers;

import io.codeka.condenser.domain.ArticleRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verify;

class CompositeArticleRendererTest {

    @Test
    @DisplayName("render should delegate to all renderers")
    void render_shouldDelegateToAllRenderers() {
        // Given
        ArticleRenderer renderer1 = Mockito.mock(ArticleRenderer.class);
        ArticleRenderer renderer2 = Mockito.mock(ArticleRenderer.class);
        String content = "Hello, world!";
        CompositeArticleRenderer compositeRenderer = new CompositeArticleRenderer(List.of(renderer1, renderer2));

        // When
        compositeRenderer.render(content);

        // Then
        verify(renderer1).render(content);
        verify(renderer2).render(content);
    }
}

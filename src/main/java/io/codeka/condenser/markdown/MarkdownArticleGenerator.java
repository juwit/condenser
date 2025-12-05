package io.codeka.condenser.markdown;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.ArticleGenerator;
import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates the Markdown body of an {@link Article}.
 * <p>
 * Format:
 * <pre>
 * [introduction]
 *
 * <!--more-->
 *
 * ## tag
 * * link title
 * * link title
 *
 * ## tag
 * * link title
 * </pre>
 */
public class MarkdownArticleGenerator implements ArticleGenerator {

    @Override
    public String generate(Article article) {
        StringBuilder sb = new StringBuilder();

        // Introduction
        sb.append(article.introductionMarkdown().strip())
                .append('\n')
                .append('\n');

        // Read-more marker
        sb.append("<!--more-->")
                .append('\n')
                .append('\n');

        // Group links by tag, using deterministic tag ordering
        Map<Tag, List<Link>> linksByTag = article.links().stream()
                .collect(Collectors.groupingBy(Link::tag, () -> new EnumMap<>(Tag.class), Collectors.toList()));

        linksByTag.forEach((tag, links) -> {
            sb.append("## ").append(tag.markdownTitle()).append('\n');
            for (Link link : links) {
                sb.append("* ").append(link.title()).append('\n');
            }
            sb.append('\n');
        });

        return sb.toString();
    }
}

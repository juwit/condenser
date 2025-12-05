package io.codeka.condenser.domain;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A tech-watch article, with an introduction in Markdown and a list of links.
 * The article's tags are the aggregation of the tags of its links.
 */
public record Article(
        String introductionMarkdown,
        List<Link> links
) {

    public Article {
        Objects.requireNonNull(introductionMarkdown, "introductionMarkdown must not be null");
        Objects.requireNonNull(links, "links must not be null");

        introductionMarkdown = introductionMarkdown.strip();
        if (introductionMarkdown.isEmpty()) {
            throw new IllegalArgumentException("introductionMarkdown must not be blank");
        }

        if (links.isEmpty()) {
            throw new IllegalArgumentException("links must not be empty");
        }

        // Defensive copy to ensure immutability
        links = List.copyOf(links);
    }

    /**
     * Aggregated set of tags from all links in this article.
     */
    public Set<Tag> tags() {
        return links.stream()
                .map(Link::tag)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Tag.class)));
    }
}

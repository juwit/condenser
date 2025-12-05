package io.codeka.condenser.domain;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

/**
 * A single tech-watch link with metadata and analysis.
 */
public record Link(
        URI url,
        String title,
        Tag tag,
        Optional<Source> author,
        Optional<Source> source,
        String analysis
) {

    public Link {
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(tag, "tag must not be null");
        author = Objects.requireNonNullElse(author, Optional.empty());
        source = Objects.requireNonNullElse(source, Optional.empty());
        Objects.requireNonNull(analysis, "analysis must not be null");

        if (url.getScheme() == null || url.getHost() == null) {
            throw new IllegalArgumentException("url must be absolute (with scheme and host)");
        }

        title = title.trim();
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        analysis = analysis.trim();
        if (analysis.isEmpty()) {
            throw new IllegalArgumentException("analysis must not be blank");
        }
    }
}

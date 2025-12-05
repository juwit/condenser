package io.codeka.condenser.domain;

import java.net.URI;
import java.util.Objects;

/**
 * Optional source information for a tech-watch link.
 */
public record Source(String name, URI url) {

    public Source {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");

        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }

        // Normalize URI (ensures it has a scheme and host)
        if (url.getScheme() == null || url.getHost() == null) {
            throw new IllegalArgumentException("url must be absolute (with scheme and host)");
        }
    }
}

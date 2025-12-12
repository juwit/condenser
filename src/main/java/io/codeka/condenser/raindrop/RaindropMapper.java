package io.codeka.condenser.raindrop;

import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Source;
import io.codeka.condenser.domain.Tag;

import java.net.URI;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * Maps Raindrop API DTOs to domain objects.
 */
public final class RaindropMapper {

    /**
     * Convert a {@link RaindropDto} to a domain {@link Link}.
     */
    public Link toLink(RaindropDto dto) {
        Objects.requireNonNull(dto, "dto must not be null");

        URI url = URI.create(dto.link());
        String title = dto.title();

        Tag tag = mapTag(dto);

        Optional<Source> source = mapSource(dto);

        String analysis = pickAnalysis(dto);

        return new Link(url, title, tag, null, source, analysis);
    }

    private Tag mapTag(RaindropDto dto) {
        if (dto.tags() != null && !dto.tags().isEmpty()) {
            for (String raw : dto.tags()) {
                if (raw == null) continue;
                String normalized = normalizeTagString(raw);
                for (Tag t : Tag.values()) {
                    if (equalsIgnoreCase(normalized, t.displayName()) ||
                        equalsIgnoreCase(normalized, t.name())) {
                        return t;
                    }
                    // also allow substrings like "Kubernetes" inside "☸️ Kubernetes"
                    if (containsIgnoreCase(normalized, t.displayName())) {
                        return t;
                    }
                }
            }
        }
        // Fallback: general Internet tag
        return Tag.INTERNET;
    }

    private Optional<Source> mapSource(RaindropDto dto) {
        String domain = dto.domain();
        if (domain == null || domain.isBlank()) return Optional.empty();
        // Construct a canonical HTTPS URL for the domain
        URI srcUrl = URI.create("https://" + domain);
        return Optional.of(new Source(domain, srcUrl));
    }

    private String pickAnalysis(RaindropDto dto) {
        String note = dto.note() == null ? "" : dto.note().trim();
        if (!note.isEmpty()) return note;
        String excerpt = dto.excerpt() == null ? "" : dto.excerpt().trim();
        if (!excerpt.isEmpty()) return excerpt;
        return dto.title();
    }

    private String normalizeTagString(String s) {
        // Drop leading emoji and extra whitespace; keep alphanumerics for matching
        String trimmed = s.trim();
        // Many Raindrop tags include an emoji followed by a space; remove the first codepoint if it's not alphanumeric
        int first = trimmed.isEmpty() ? -1 : trimmed.codePointAt(0);
        if (first != -1 && !Character.isLetterOrDigit(first)) {
            trimmed = trimmed.substring(Character.charCount(first)).trim();
        }
        return trimmed;
    }

    private boolean equalsIgnoreCase(String a, String b) {
        return a.equalsIgnoreCase(b);
    }

    private boolean containsIgnoreCase(String haystack, String needle) {
        return haystack.toLowerCase(Locale.ROOT).contains(needle.toLowerCase(Locale.ROOT));
    }
}

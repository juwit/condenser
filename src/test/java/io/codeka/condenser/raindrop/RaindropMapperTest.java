package io.codeka.condenser.raindrop;

import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Source;
import io.codeka.condenser.domain.Tag;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RaindropMapperTest {

    @Test
    void toLink_maps_basic_fields_and_optionals() {
        RaindropDto dto = new RaindropDto(
                1484662501L,
                "https://cwe.mitre.org/top25/archive/2025/2025_cwe_top25.html",
                "CWE - 2025 CWE Top 25 Most Dangerous Software Weaknesses",
                "Common Weakness Enumeration (CWE) is a list of software and hardware weaknesses.",
                "",
                "link",
                new RaindropDto.UserRef("users", 4084166L),
                "https://rdl.ink/render/https%3A%2F%2Fcwe.mitre.org%2Ftop25%2Farchive%2F2025%2F2025_cwe_top25.html",
                List.of(),
                List.of("☕ Java"),
                false,
                new RaindropDto.Reminder(null),
                false,
                "2025-12-11T21:41:01.970Z",
                new RaindropDto.CollectionRef("collections", 64236151L, 64236151L),
                List.of(),
                "2025-12-12T09:33:45.385Z",
                "cwe.mitre.org",
                new RaindropDto.CreatorRef(4084166L, "avatar", "julien-wittouck", ""),
                1484662501L,
                64236151L
        );

        var raindropMapper = new RaindropMapper();
        Link link = raindropMapper.toLink(dto);

        assertEquals(URI.create(dto.link()), link.url());
        assertEquals(dto.title(), link.title());
        assertEquals(Tag.JAVA, link.tag());

        Optional<Source> source = link.source();
        assertTrue(source.isPresent());
        assertEquals("cwe.mitre.org", source.get().name());
        assertEquals(URI.create("https://cwe.mitre.org"), source.get().url());

        // note is empty, so analysis falls back to excerpt
        assertEquals(dto.excerpt(), link.analysis());
    }
}

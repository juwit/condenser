package io.codeka.condenser.raindrop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class RaindropDtoDeserializationTest {

    @Test
    @DisplayName("should deserialize sample JSON into RaindropDto")
    void shouldDeserializeSampleJsonIntoRaindropDto() throws Exception {
        String json = """
        {
          "_id": 1484662501,
          "link": "https://cwe.mitre.org/top25/archive/2025/2025_cwe_top25.html",
          "title": "CWE - 2025 CWE Top 25 Most Dangerous Software Weaknesses",
          "excerpt": "Common Weakness Enumeration (CWE) is a list of software and hardware weaknesses.",
          "note": "",
          "type": "link",
          "user": {
            "$ref": "users",
            "$id": 4084166
          },
          "cover": "https://rdl.ink/render/https%3A%2F%2Fcwe.mitre.org%2Ftop25%2Farchive%2F2025%2F2025_cwe_top25.html",
          "media": [],
          "tags": ["🔒 Security"],
          "important": false,
          "reminder": { "date": null },
          "removed": false,
          "created": "2025-12-11T21:41:01.970Z",
          "collection": {
            "$ref": "collections",
            "$id": 64236151,
            "oid": 64236151
          },
          "highlights": [],
          "lastUpdate": "2025-12-12T09:33:45.385Z",
          "domain": "cwe.mitre.org",
          "creatorRef": {
            "_id": 4084166,
            "avatar": "https://up.raindrop.io/user/avatars/408/416/6/1747834019723.jpeg",
            "name": "julien-wittouck",
            "email": ""
          },
          "sort": 1484662501,
          "collectionId": 64236151
        }
        """;

        ObjectMapper mapper = new ObjectMapper();
        RaindropDto dto = mapper.readValue(json, RaindropDto.class);

        assertNotNull(dto);
        assertEquals(1484662501L, dto._id());
        assertEquals("https://cwe.mitre.org/top25/archive/2025/2025_cwe_top25.html", dto.link());
        assertEquals("CWE - 2025 CWE Top 25 Most Dangerous Software Weaknesses", dto.title());
        assertEquals("", dto.note());
        assertEquals("link", dto.type());

        assertNotNull(dto.user());
        assertEquals("users", dto.user().ref());
        assertEquals(4084166L, dto.user().id());

        assertEquals("https://rdl.ink/render/https%3A%2F%2Fcwe.mitre.org%2Ftop25%2Farchive%2F2025%2F2025_cwe_top25.html", dto.cover());
        assertNotNull(dto.media());
        assertNotNull(dto.tags());
        assertEquals(1, dto.tags().size());
        assertEquals("🔒 Security", dto.tags().getFirst());
        assertFalse(dto.important());
        assertNotNull(dto.reminder());
        assertNull(dto.reminder().date());
        assertFalse(dto.removed());
        assertEquals("2025-12-11T21:41:01.970Z", dto.created());

        assertNotNull(dto.collection());
        assertEquals("collections", dto.collection().ref());
        assertEquals(64236151L, dto.collection().id());
        assertEquals(64236151L, dto.collection().oid());

        assertNotNull(dto.highlights());
        assertEquals("2025-12-12T09:33:45.385Z", dto.lastUpdate());
        assertEquals("cwe.mitre.org", dto.domain());

        assertNotNull(dto.creatorRef());
        assertEquals(4084166L, dto.creatorRef()._id());
        assertEquals("julien-wittouck", dto.creatorRef().name());
        assertEquals("", dto.creatorRef().email());

        assertEquals(1484662501L, dto.sort());
        assertEquals(64236151L, dto.collectionId());
    }
}

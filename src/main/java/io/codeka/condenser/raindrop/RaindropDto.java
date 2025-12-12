package io.codeka.condenser.raindrop;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * DTO representing a single Raindrop item as returned by the Raindrop API.
 *
 * This mirrors the JSON structure provided in the issue description.
 */
public record RaindropDto(
        long _id,
        String link,
        String title,
        String excerpt,
        String note,
        String type,
        UserRef user,
        String cover,
        List<Object> media,
        List<String> tags,
        boolean important,
        Reminder reminder,
        boolean removed,
        String created,
        CollectionRef collection,
        List<Object> highlights,
        String lastUpdate,
        String domain,
        CreatorRef creatorRef,
        long sort,
        long collectionId
) {
    /** Reference to a user ("$ref", "$id"). */
    public static record UserRef(
            @JsonProperty("$ref") String ref,
            @JsonProperty("$id") long id
    ) { }

    /** Reminder object (currently only a nullable date). */
    public static record Reminder(
            @Nullable String date
    ) { }

    /** Reference to a collection ("$ref", "$id", and "oid"). */
    public static record CollectionRef(
            @JsonProperty("$ref") String ref,
            @JsonProperty("$id") long id,
            long oid
    ) { }

    /** Creator reference object. */
    public static record CreatorRef(
            long _id,
            String avatar,
            String name,
            String email
    ) { }
}

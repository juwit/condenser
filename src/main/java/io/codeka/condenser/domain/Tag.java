package io.codeka.condenser.domain;

/**
 * Enumeration of tags used to categorize tech-watch links and articles.
 * <p>
 * Each tag carries an emoji and a human-friendly display name.
 */
public enum Tag {
    CLOUD("☁️", "Cloud"),
    DATABASES("🛢️", "Databases"),
    DEVOPS("⚙️", "DevOps"),
    DOCKER("🐳", "Docker"),
    EVENTS("📅", "Events"),
    IA("🤖", "IA"),
    INTERNET("🌐", "Internet"),
    JAVA("☕", "Java"),
    KUBERNETES("☸️", "Kubernetes"),
    LINUX("🐧", "Linux");

    private final String emoji;
    private final String displayName;

    Tag(String emoji, String displayName) {
        this.emoji = emoji;
        this.displayName = displayName;
    }

    public String emoji() {
        return emoji;
    }

    public String displayName() {
        return displayName;
    }

    /**
     * Convenience method to render the tag for Markdown titles: "emoji + space + displayName".
     */
    public String markdownTitle() {
        return emoji + " " + displayName;
    }
}

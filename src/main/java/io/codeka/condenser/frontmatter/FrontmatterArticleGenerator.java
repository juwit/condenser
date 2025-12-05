package io.codeka.condenser.frontmatter;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.ArticleDateSupplier;
import io.codeka.condenser.domain.ArticleGenerator;
import io.codeka.condenser.domain.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

/**
 * Generates a YAML frontmatter block for an {@link Article}.
 */
public class FrontmatterArticleGenerator implements ArticleGenerator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TITLE_MONTH_YEAR_FR = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.FRENCH);

    private final ArticleDateSupplier dateSupplier;

    public FrontmatterArticleGenerator(ArticleDateSupplier dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    @Override
    public String generate(Article article) {
        // Current date for the frontmatter
        LocalDate today = dateSupplier.get();

        StringBuilder sb = new StringBuilder();
        sb.append("---\n");
        sb.append("date: ").append(DATE_FORMAT.format(today)).append('\n');
        sb.append("language: fr\n");
        int day = today.getDayOfMonth();
        String period = (day <= 4 || day >= 26) ? "Fin " : "Début ";
        sb.append("title: ")
                .append("La veille de Wittouck - ")
                .append(period)
                .append(TITLE_MONTH_YEAR_FR.format(today))
                .append('\n');
        sb.append("series: La veille de Wittouck\n");
        sb.append("tags:\n");

        article.tags().stream()
                .sorted(Comparator.comparing(Enum::name))
                .map(Tag::name)
                .map(String::toLowerCase)
                .forEach(tag -> sb.append("  - ").append(tag).append('\n'));

        sb.append("draft: true\n");
        sb.append("---\n");

        return sb.toString();
    }
}

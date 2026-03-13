package io.codeka.condenser.application;

import io.codeka.condenser.domain.Article;
import io.codeka.condenser.domain.LinkSource;
import io.codeka.condenser.generators.CompositeArticleGenerator;
import io.codeka.condenser.generators.frontmatter.FrontmatterArticleGenerator;
import io.codeka.condenser.generators.markdown.MarkdownArticleGenerator;
import io.codeka.condenser.raindrop.RaindropConfiguration;
import io.codeka.condenser.renderers.CompositeArticleRenderer;
import io.codeka.condenser.renderers.ConsoleArticleRenderer;
import io.codeka.condenser.renderers.FileArticleRenderer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Import(RaindropConfiguration.class)
public class Application {

    static void main(String... args) {
        var context = SpringApplication.run(Application.class, args);

        var frontmatterGenerator = new FrontmatterArticleGenerator(LocalDate::now);
        var markdownGenerator = new MarkdownArticleGenerator();
        var articleGenerator = new CompositeArticleGenerator(List.of(frontmatterGenerator,markdownGenerator));

        var linkSource = context.getBean(LinkSource.class);

        var links = linkSource.getLinks();
        var article = new Article("Test", links);

        var articleContent = articleGenerator.generate(article);

        var renderer = new CompositeArticleRenderer(List.of(
                new FileArticleRenderer(Path.of("index.md")),
                new ConsoleArticleRenderer()
        ));

        renderer.render(articleContent);
    }
}

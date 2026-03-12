package io.codeka.condenser.raindrop;

import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.LinkSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaindropLinkSource implements LinkSource {

    private final RaindropApi raindropApi;

    private final RaindropMapper raindropMapper;

    private final RaindropConfigurationProperties properties;

    public RaindropLinkSource(RaindropApi raindropApi, RaindropMapper raindropMapper, RaindropConfigurationProperties properties) {
        this.raindropApi = raindropApi;
        this.raindropMapper = raindropMapper;
        this.properties = properties;
    }

    @Override
    public List<Link> getLinks() {
        var response = raindropApi.getRaindrops(properties.getCollectionId());
        return response.items().stream().map(raindropMapper::toLink).toList();
    }
}

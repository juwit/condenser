package io.codeka.condenser.raindrop;

import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.LinkSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaindropLinkSource implements LinkSource {

    private final RaindropApi raindropApi;

    private final RaindropMapper raindropMapper;

    public RaindropLinkSource(RaindropApi raindropApi, RaindropMapper raindropMapper) {
        this.raindropApi = raindropApi;
        this.raindropMapper = raindropMapper;
    }

    @Override
    public List<Link> getLinks() {
        var response = raindropApi.getRaindrops(1);
        return response.items().stream().map(raindropMapper::toLink).toList();
    }
}

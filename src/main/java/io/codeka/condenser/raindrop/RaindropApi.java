package io.codeka.condenser.raindrop;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface RaindropApi {

    @GetExchange("raindrops/{{collectionId}}")
    GetRaindropsResponseDto getRaindrops(@PathVariable long collectionId);
}

package io.codeka.condenser.raindrop;

import java.util.List;

public record GetRaindropsResponseDto(
        boolean result,
        List<RaindropDto> items,
        int count,
        long collectionId
) {
}

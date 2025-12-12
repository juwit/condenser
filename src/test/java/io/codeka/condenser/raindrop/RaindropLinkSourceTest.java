package io.codeka.condenser.raindrop;

import io.codeka.condenser.domain.Link;
import io.codeka.condenser.domain.Tag;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RaindropLinkSourceTest {

    @Test
    void getLinks_calls_api_with_collection_and_maps_items_to_links() {
        // Arrange
        RaindropApi api = mock(RaindropApi.class);
        RaindropMapper mapper = mock(RaindropMapper.class);
        RaindropLinkSource linkSource = new RaindropLinkSource(api, mapper);

        RaindropDto dto1 = mock(RaindropDto.class);
        RaindropDto dto2 = mock(RaindropDto.class);
        var response = new GetRaindropsResponseDto(true, List.of(dto1, dto2), 2, 64236151L);
        when(api.getRaindrops(64236151L)).thenReturn(response);

        Link link1 = new Link(
                URI.create("https://example.com/1"),
                "Title 1",
                Tag.JAVA,
                Optional.empty(),
                Optional.empty(),
                "Analysis 1"
        );
        Link link2 = new Link(
                URI.create("https://example.com/2"),
                "Title 2",
                Tag.JAVA,
                Optional.empty(),
                Optional.empty(),
                "Analysis 2"
        );

        when(mapper.toLink(dto1)).thenReturn(link1);
        when(mapper.toLink(dto2)).thenReturn(link2);

        // Act
        List<Link> links = linkSource.getLinks();

        // Assert
        verify(api, times(1)).getRaindrops(64236151L);
        verify(mapper).toLink(dto1);
        verify(mapper).toLink(dto2);
        assertEquals(List.of(link1, link2), links);
        assertEquals(2, links.size());
    }
}

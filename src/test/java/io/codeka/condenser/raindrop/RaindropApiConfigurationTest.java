package io.codeka.condenser.raindrop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest( classes = RaindropApiConfiguration.class)
class RaindropApiConfigurationTest {

    @Test
    void raindropApi_shouldBeAvailable(@Autowired RaindropApi raindropApi) {
        assertThat(raindropApi).isNotNull();
    }
}
package io.codeka.condenser.raindrop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "raindrop", basePackageClasses = RaindropApiConfiguration.class)
public class RaindropApiConfiguration {

    @Value("${RAINDROP_API_KEY}")
    private String raindropApiKey;

    @Bean
    RestClientHttpServiceGroupConfigurer myHttpServiceGroupConfigurer() {
        return (groups) -> groups.forEachClient((_, clientBuilder) -> clientBuilder.defaultHeader("Authorization", "Bearer " + raindropApiKey));
    }
}

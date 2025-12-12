package io.codeka.condenser.raindrop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "raindrop", basePackageClasses = RaindropApiConfiguration.class)
public class RaindropApiConfiguration {
}

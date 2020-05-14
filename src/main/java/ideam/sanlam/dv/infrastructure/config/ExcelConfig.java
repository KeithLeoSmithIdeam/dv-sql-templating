package ideam.sanlam.dv.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ExcelConfigProperties.class})
@ComponentScan("ideam.sanlam.dv.infrastructure.config")
public class ExcelConfig {
}

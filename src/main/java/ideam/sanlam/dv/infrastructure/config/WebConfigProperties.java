package ideam.sanlam.dv.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.config.web")
public class WebConfigProperties {

    @Getter @Setter
    private String baseHrefStaticFiles;

}



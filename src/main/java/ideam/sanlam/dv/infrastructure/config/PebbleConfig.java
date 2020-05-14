package ideam.sanlam.dv.infrastructure.config;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.DelegatingLoader;
import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PebbleConfig {

    /*@Bean
    public PebbleEngine pebbleEngine() {

        final FileLoader loader = new FileLoader();
        loader.setPrefix("templates/");
        loader.setSuffix("html");

        return new PebbleEngine.Builder().loader(loader).build();
    }*/

}

package ro.danix.first.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ro.danix.first.controller.config", "ro.danix.first.model.config"})
public class ApplicationConfig {

}

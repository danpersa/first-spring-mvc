package ro.danix.first.model.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author danix
 */
@Configuration
@ComponentScan({"ro.danix.first.model.domain"})
@Profile(value = "factories")
public class FactoriesConfig {

}

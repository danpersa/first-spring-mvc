package ro.danix.first.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 *
 * @author danix
 */
@Configuration
public class ConvertersConfig {


    @Bean
    public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
        return new MappingJacksonHttpMessageConverter();
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter();
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }
}

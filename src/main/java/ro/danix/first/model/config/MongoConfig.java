package ro.danix.first.model.config;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

/**
 *
 * @author Dan Persa
 */
@Configuration
@ComponentScan({"ro.danix.first.model", "ro.danix.first.exception"})
public class MongoConfig extends AbstractMongoConfiguration {

    public static final String DATABASE_NAME = "first-spring-mvc-dev";
    @Autowired
    private List<Converter<?, ?>> converters;

    @Override
    public Mongo mongo() throws Exception {
        Mongo mongo = new Mongo();
        mongo.setWriteConcern(WriteConcern.SAFE);

        return mongo;
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(converters);
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}

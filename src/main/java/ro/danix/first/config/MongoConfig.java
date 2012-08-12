package ro.danix.first.config;

import com.mongodb.Mongo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

/**
 *
 * @author Dan Persa
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo("localhost");
    }

    @Override
    public String getDatabaseName() {
        return "first-spring-mvc-dev";
    }

    @Override
    public String getMappingBasePackage() {
        return "ro.danix.first.model.businessobject";
    }

    // the following are optional
    @Override
    protected void afterMappingMongoConverterCreation(MappingMongoConverter converter) {
        List<MongoConverter> converterList = new ArrayList<MongoConverter>();
        CustomConversions customConversions = new CustomConversions(converterList);
        //    converterList.add(new org.springframework.data.mongodb.test.PersonReadConverter());
        //    converterList.add(new org.springframework.data.mongodb.test.PersonWriteConverter());
        converter.setCustomConversions(customConversions);
    }

    @Bean
    public LoggingEventListener mappingEventsListener() {
        return new LoggingEventListener();
    }
}

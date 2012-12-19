package ro.danix.first.model.config;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.danix.first.model.config.MongoConfig;

/**
 *
 * @author danix
 */
public class MongoConfigTest {

    @Test
    public void bootstrapAppFromJavaConfig() {

        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
        assertThat(context, is(notNullValue()));
    }
}

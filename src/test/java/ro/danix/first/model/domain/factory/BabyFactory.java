package ro.danix.first.model.domain.factory;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.Baby;

/**
 *
 * @author danix
 */
@Component
@Profile(value = "factories")
public class BabyFactory extends AbstractFactory<Baby> {

    public static final String NAME = "Gigi";
    public static final DateTime BIRTH_DATE = new DateTime(2010, 10, 20, 0, 0);
    
    @Override
    protected Baby init() {
        Baby baby = new Baby(NAME, BIRTH_DATE);
        return baby;
    } 
}

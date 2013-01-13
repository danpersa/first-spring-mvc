package ro.danix.first.model.converter;

import java.util.Date;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author danix
 */
@Component
public class DateTimeToDateConverter implements Converter<DateTime, Date> {

    @Override
    public Date convert(DateTime source) {
        return source == null ? null : source.toDate();
    }
}
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
public class DateToDateTimeConverter implements Converter<Date, DateTime> {

    @Override
    public DateTime convert(Date source) {
        return source == null ? null : new DateTime(source.getTime());
    }
}
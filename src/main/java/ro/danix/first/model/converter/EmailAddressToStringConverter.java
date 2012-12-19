package ro.danix.first.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.EmailAddress;

/**
 *
 * @author dpersa
 */
@Component
public class EmailAddressToStringConverter implements Converter<EmailAddress, String> {

    /* 
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public String convert(EmailAddress source) {
        return source == null ? null : source.toString();
    }
}
package ro.danix.first.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.danix.first.model.domain.EmailAddress;

/**
 *
 * @author danix
 */
@Component
public class StringToEmailAddressConverter implements Converter<String, EmailAddress> {

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public EmailAddress convert(String source) {
        return StringUtils.hasText(source) ? new EmailAddress(source) : null;
    }
}

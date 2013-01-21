package ro.danix.first.controller.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import lombok.extern.slf4j.Slf4j;

/**
 * @author danix
 */
@Component
@Slf4j
public class LocaleContextHolderWrapper {

    public Locale getCurrentLocale() {
        log.debug("Getting current locale");
        return LocaleContextHolder.getLocale();
    }
}

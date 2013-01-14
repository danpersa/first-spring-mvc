package ro.danix.first.model.domain.factory;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.WrapDynaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author danix
 */
public abstract class AbstractFactory<T> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractFactory.class);

    /**
     *
     * @return the default object returned every time the build method is called
     */
    protected abstract T init();

    public T build() {
        T object = init();
        return object;
    }

    /**
     *
     * @param example
     * @return a new object populated with the values from the example object
     */
    public T build(T example) {
        T object = build();
        if (example == null) {
            return object;
        }
        DynaBean dynaExampleObject = new WrapDynaBean(example);
        DynaBean dynaCompleteObject = new WrapDynaBean(object);
        DynaProperty[] dynaProperties = dynaExampleObject.getDynaClass().getDynaProperties();
        logger.info("Start building the " + object.getClass().getSimpleName() + " object");
        for (DynaProperty property : dynaProperties) {
            String propertyName = property.getName();
            Object dynaExampleObjectPropertyValue = dynaExampleObject.get(propertyName);
            if (dynaExampleObjectPropertyValue != null) {
                if (propertyName.equals("class")) {
                    continue;
                }
                logger.info("We set property name: <" + propertyName + "> to <" + dynaExampleObject.get(propertyName) + ">");
                dynaCompleteObject.set(propertyName, dynaExampleObject.get(propertyName));
            } else {
                logger.debug("We don't set property name: <" + propertyName + "> because is not null");
            }
        }
        return object;
    }
}

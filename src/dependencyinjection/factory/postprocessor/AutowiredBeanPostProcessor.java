package dependencyinjection.factory.postprocessor;

import dependencyinjection.factory.ApplicationContext;
import dependencyinjection.factory.annotations.Autowired;
import dependencyinjection.factory.annotations.Qualifier;

import java.lang.reflect.Field;
import java.util.Optional;

public class AutowiredBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void process(Object bean, ApplicationContext context) throws Exception {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                final String beanQualifierValue = getBeanQualifierValue(field);
                final Object fieldNewBean = context.getBean(field.getType(), beanQualifierValue);

                field.setAccessible(true);
                field.set(bean, fieldNewBean);
            }
        }
    }

    private String getBeanQualifierValue(Field field) {
        return Optional.ofNullable(field.getAnnotation(Qualifier.class))
                .map(Qualifier::value)
                .orElse(null);
    }
}

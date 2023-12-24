package dependencyinjection.factory.postprocessor;

import dependencyinjection.factory.ApplicationContext;

public interface BeanPostProcessor {

    void process(Object bean, ApplicationContext context) throws Exception;
}

package dependencyinjection.factory;

import dependencyinjection.factory.postprocessor.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class BeanFactory {

    private final ApplicationContext applicationContext;
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public BeanFactory(ApplicationContext applicationContext) throws Exception {
        this.applicationContext = applicationContext;
        for (Class<? extends BeanPostProcessor> beanPostProcessor : applicationContext.getBeanTypeConfig().getReflections().getSubTypesOf(BeanPostProcessor.class)) {
            beanPostProcessors.add(beanPostProcessor.getDeclaredConstructor().newInstance());
        }
    }

    public <T> T createBean(Class<T> implClass) throws Exception {
        final T newObject = implClass.getDeclaredConstructor().newInstance();

        applyBeanPostProcessors(newObject);

        return newObject;
    }

    private <T> void applyBeanPostProcessors(T newBean) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            beanPostProcessor.process(newBean, applicationContext);
        }
    }
}

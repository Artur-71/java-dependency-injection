package dependencyinjection.factory;

import dependencyinjection.factory.util.BeanUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private final BeanTypeConfig beanTypeConfig;
    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> beanContext = new ConcurrentHashMap<>();

    public ApplicationContext(BeanTypeConfig beanTypeConfig) {
        this.beanTypeConfig = beanTypeConfig;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanTypeConfig getBeanTypeConfig() {
        return beanTypeConfig;
    }

    public <T> T getBean(Class<T> clsType) throws Exception {
        return getBean(clsType, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clsType, String qualifier) throws Exception {
        final Class<? extends T> implClass = beanTypeConfig.getImplClass(clsType, qualifier);

        if (beanContext.containsKey(implClass)) {
            return (T) beanContext.get(implClass);
        }

        final T newBean = beanFactory.createBean(implClass);

        if (BeanUtil.isSingletonBean(newBean.getClass())) {
            beanContext.put(newBean.getClass(), newBean);
        }

        return newBean;
    }
}

package dependencyinjection.factory;

public class ApplicationConfig {

    private ApplicationConfig() {
    }

    public static ApplicationContext configure(String packageToScan) throws Exception {
        final ApplicationContext applicationContext = new ApplicationContext(new BeanTypeConfig(packageToScan));
        final BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);

        return applicationContext;
    }
}

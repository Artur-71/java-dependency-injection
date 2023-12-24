package dependencyinjection.factory.util;

import dependencyinjection.factory.annotations.Component;
import dependencyinjection.factory.annotations.Scope;
import dependencyinjection.factory.constants.BeanScope;

public class BeanUtil {

    public static boolean isBean(Class<?> cls) {
        return cls.isAnnotationPresent(Component.class);
    }

    public static boolean isSingletonBean(Class<?> beanCls) {
        final Scope scopeAnnotation = beanCls.getAnnotation(Scope.class);

        return scopeAnnotation == null
                || scopeAnnotation.value().isEmpty()
                || scopeAnnotation.value().equals(BeanScope.SINGLETON);
    }
}

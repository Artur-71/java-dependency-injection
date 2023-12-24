package dependencyinjection.factory;

import dependencyinjection.factory.util.BeanUtil;
import org.reflections.Reflections;

import java.util.Set;

public class BeanTypeConfig {

    private final Reflections reflections;

    public BeanTypeConfig(String packageToScan) {
        this.reflections = new Reflections(packageToScan);
    }

    public <T> Class<? extends T> getImplClass(Class<T> clsType, String qualifier) {
        if (clsType.isInterface()) {
            final Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clsType);

            if (subTypes.size() == 1) {
                final Class<? extends T> implClass = subTypes.iterator().next();
                if (!BeanUtil.isBean(implClass)) {
                    throw new IllegalArgumentException("Provided object is not a bean: " + implClass.getSimpleName());
                }

                return subTypes.iterator().next();
            }

            if (qualifier == null || qualifier.isEmpty()) {
                throw new IllegalStateException("Qualifier was not provided for an interface with multiple implementations. Interface: " + clsType.getSimpleName());
            }

            return subTypes.stream()
                    .filter(BeanUtil::isBean)
                    .filter(subType -> subType.getSimpleName().equals(qualifier))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Bean was not found for provided qualifier: " + qualifier));
        }

        if (!BeanUtil.isBean(clsType)) {
            throw new IllegalArgumentException("Provided object is not a bean: " + clsType.getSimpleName());
        }

        return clsType;
    }

    public Reflections getReflections() {
        return reflections;
    }
}

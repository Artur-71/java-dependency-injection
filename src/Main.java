import dependencyinjection.UserController;
import dependencyinjection.factory.ApplicationConfig;
import dependencyinjection.factory.ApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = ApplicationConfig.configure("dependencyinjection");

        UserController object1 = applicationContext.getBean(UserController.class);
        UserController object2 = applicationContext.getBean(UserController.class);

        System.out.println();
    }
}

package dependencyinjection;

import dependencyinjection.factory.annotations.Autowired;
import dependencyinjection.factory.annotations.Component;
import dependencyinjection.factory.annotations.Qualifier;

@Component
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private IUserService userService;
}

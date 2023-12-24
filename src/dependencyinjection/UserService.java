package dependencyinjection;

import dependencyinjection.factory.annotations.Autowired;
import dependencyinjection.factory.annotations.Component;

@Component
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
}

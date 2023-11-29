package user.use_case;

import user.entity.User;

public abstract class LoginUserDataAccessInterface {

    abstract boolean existsByName(String identifier);

    abstract void save(User user);

    abstract User get(String username);
}

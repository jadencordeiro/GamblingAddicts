package user.use_case;

import user.entity.User;

public interface LoginUserDataAccessInterface {

    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}

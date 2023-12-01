package user.data_access;


import user.entity.User;

public interface UserDataAccessInterface {

    boolean existsByName(String identifier);


    void save(User user);
}

package user.data_access;


import user.entity.User;

public abstract class UserDataAccessInterface {

    public abstract boolean existsByName(String identifier);
//    public abstract void place_bet(Bet bet);

    public abstract void save(User user);
}

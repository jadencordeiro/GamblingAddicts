package user.interface_adapter;

import bet.Bet;
import user.entity.User;

public abstract class UserDataAccessInterface {

    public abstract boolean existsByName(String identifier);

    public abstract void save(User user);

    public abstract void place_bet(Bet bet);
}

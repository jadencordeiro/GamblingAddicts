package user.entity;

public class PasswordValidatorService extends PasswordValidator {

    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
}

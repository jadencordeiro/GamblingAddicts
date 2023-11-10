package user;

import wallet.Wallet;

public class User {

    final private String name;

    final private String password;

    private Wallet wallet;

    public User(String name, String password) {

        this.name = name;
        this.password = password;
        this.wallet = new Wallet();

    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

}

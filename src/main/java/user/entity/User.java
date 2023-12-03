package user.entity;


import wallet.entity.Wallet;

import java.time.LocalDateTime;

/**
 * name: username
 * password: password
 * creationTime: time when created the account
 * wallet: see Wallet.java
 */
public class User {

    final private String name;

    final private String password;

    private final LocalDateTime creationTime;

    private Wallet wallet;

    public User(String name, String password, LocalDateTime creationTime) {

        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.wallet = new Wallet(name);

    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Wallet getWallet() { return this.wallet; }

}

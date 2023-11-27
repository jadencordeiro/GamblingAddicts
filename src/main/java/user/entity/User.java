package user.entity;


import java.time.LocalDateTime;

public class User {

    final private String name;

    final private String password;

    private final LocalDateTime creationTime;

    private Wallet wallet;

    public User(String name, String password, LocalDateTime creationTime) {

        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.wallet = new Wallet();

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

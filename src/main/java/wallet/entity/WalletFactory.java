package wallet.entity;

public class WalletFactory {
    public Wallet create(String name){
        return new Wallet(name);
    }
}

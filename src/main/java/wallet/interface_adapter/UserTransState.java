package wallet.interface_adapter;

public class UserTransState {
    private boolean depositFunds;

    public UserTransState(UserTransState copy) {
        depositFunds = copy.depositFunds;
    }
    public UserTransState() {}

    public boolean getDepositFunds(){ return depositFunds;}
    public void setDepositFunds(boolean depositFunds) {this.depositFunds = depositFunds;}


}

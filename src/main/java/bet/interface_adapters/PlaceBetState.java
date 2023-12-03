package bet.interface_adapters;

public class PlaceBetState {
    private boolean betOnHomeEnabled;
    private boolean betOnAwayEnabled;
    private double betAmount;
    private String errorMessage;

    public PlaceBetState() {
        // Set default state
        this.betOnHomeEnabled = true;
        this.betOnAwayEnabled = true;
        this.betAmount = 0.0;
    }

    public boolean isBetOnHomeEnabled() {
        return betOnHomeEnabled;
    }

    public void setBetOnHomeEnabled(boolean betOnHomeEnabled) {
        this.betOnHomeEnabled = betOnHomeEnabled;
    }

    public boolean isBetOnAwayEnabled() {
        return betOnAwayEnabled;
    }

    public void setBetOnAwayEnabled(boolean betOnAwayEnabled) {
        this.betOnAwayEnabled = betOnAwayEnabled;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public void setError(String error) {
        this.errorMessage = error;
    }
}


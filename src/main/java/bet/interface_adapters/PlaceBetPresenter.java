package bet.interface_adapters;

import bet.use_case.BetTransactionOutputBoundary;
import bet.use_case.BetTransactionOutputData;
import user.interface_adapter.LoggedInState;
import user.interface_adapter.LoginState;

public class PlaceBetPresenter implements BetTransactionOutputBoundary {

    private final PlaceBetViewModel placeBetViewModel;
    public PlaceBetPresenter(PlaceBetViewModel placeBetViewModel){
        this.placeBetViewModel = placeBetViewModel;
    }

    @Override
    public void prepareSuccessView(BetTransactionOutputData response) {


        PlaceBetState placeBetState = placeBetViewModel.getState();
        this.placeBetViewModel.setState(placeBetState);
        this.placeBetViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        PlaceBetState betState = placeBetViewModel.getState();
        betState.setError(error);
        placeBetViewModel.firePropertyChanged();
}}

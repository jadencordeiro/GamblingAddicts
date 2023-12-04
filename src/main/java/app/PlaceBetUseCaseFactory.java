package app;

import bet.data_access.BetDataAccessObject;
import bet.entity.BetFactory;
import bet.interface_adapters.PlaceBetController;
import bet.interface_adapters.PlaceBetPresenter;
import bet.interface_adapters.PlaceBetViewModel;
import bet.use_case.BetTransactionInputBoundary;
import bet.use_case.BetTransactionInteractor;
import bet.use_case.BetTransactionOutputBoundary;
import interface_adapter.ViewManagerModel;

public class PlaceBetUseCaseFactory {

    PlaceBetUseCaseFactory() {}

    public static PlaceBetController createPlaceBetController(ViewManagerModel viewManagerModel, PlaceBetViewModel placeBetViewModel, BetDataAccessObject betDataAccessObject){
        BetTransactionOutputBoundary betTransactionOutputBoundary = new PlaceBetPresenter(placeBetViewModel);

        BetFactory betFactory = new BetFactory();

        BetTransactionInputBoundary betTransactionInputBoundary = new BetTransactionInteractor(betDataAccessObject, betTransactionOutputBoundary);

        return new PlaceBetController(betTransactionInputBoundary);

    }

}

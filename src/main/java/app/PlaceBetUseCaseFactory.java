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
import schedule.data_access.FileScheduleDataAccessObject;
import schedule.service.refresh.RefreshScheduleDataAccessInterface;
import user.data_access.UserDataAccessObject;

public class PlaceBetUseCaseFactory {

    PlaceBetUseCaseFactory() {}

    public static PlaceBetController createPlaceBetController(ViewManagerModel viewManagerModel, PlaceBetViewModel placeBetViewModel, BetDataAccessObject betDataAccessObject, UserDataAccessObject userDAO, FileScheduleDataAccessObject scheduleDAO){
        BetTransactionOutputBoundary betTransactionOutputBoundary = new PlaceBetPresenter(placeBetViewModel);

        BetFactory betFactory = new BetFactory();

        BetTransactionInputBoundary betTransactionInputBoundary = new BetTransactionInteractor(betDataAccessObject, betTransactionOutputBoundary, userDAO, scheduleDAO);

        return new PlaceBetController(betTransactionInputBoundary);

    }

}

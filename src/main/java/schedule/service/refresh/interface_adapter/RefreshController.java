package schedule.service.refresh.interface_adapter;

import schedule.service.refresh.RefreshInputBoundary;
import schedule.service.refresh.RefreshInputData;

import java.time.LocalDateTime;

public class RefreshController {

    final RefreshInputBoundary refreshUseCaseInteractor;

    public RefreshController(RefreshInputBoundary refreshUseCaseInteractor) {
        this.refreshUseCaseInteractor = refreshUseCaseInteractor;
    }

    public void execute(){
        RefreshInputData refreshInputData = new RefreshInputData();
        refreshUseCaseInteractor.execute();
    }
}

package schedule.service.refresh;

import api.SportDB;
import schedule.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RefreshInteractor implements RefreshInputBoundary{
    private final SportDB sportDB;
    final RefreshScheduleDataAccessInterface scheduleDataAccessObject;
    final RefreshOutputBoundary refreshPresenter;
    public RefreshInteractor(SportDB sportDB, RefreshScheduleDataAccessInterface scheduleDataAccessObject, RefreshOutputBoundary refreshPresenter) {
        this.sportDB = sportDB;
        this.scheduleDataAccessObject = scheduleDataAccessObject;
        this.refreshPresenter = refreshPresenter;
    }

    @Override
    public void execute(RefreshInputData refreshInputData) {
        ArrayList<Event> events = sportDB.getEvents(refreshInputData.getDate());
        for (Event event: events){
            if (!scheduleDataAccessObject.existsByName(event.getTitle())){
                scheduleDataAccessObject.save(event);
            }
        }
    }
}

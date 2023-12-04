package schedule.service.refresh;

import api.SportDataIODB;
import org.junit.jupiter.api.Test;
import schedule.data_access.InMemoryScheduleDataAccessObject;
import schedule.entity.SportEventFactory;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RefreshInteractorTest {

    @Test
    void successTest(){
        RefreshScheduleDataAccessInterface scheduleRepository = new InMemoryScheduleDataAccessObject();

        RefreshOutputBoundary successPresenter = new RefreshOutputBoundary() {
            @Override
            public void prepareSuccessView(RefreshOutputData response) {
                for (Map<String, Object> event: response.getEvents()){
                    String title = event.get("home") + " vs " + event.get("away");
                    int homeScore = (Integer) event.get("homeScore");
                    int awayScore = (Integer) event.get("awayScore");
                    assertTrue(scheduleRepository.existsByName(title));
                    assertTrue(event.get("status").equals(0) || event.get("status").equals(-1) || event.get("status").equals(1));
                    assertTrue(homeScore >= 0 && awayScore >= 0);
                }
            }
        };

        RefreshInputBoundary interactor = new RefreshInteractor(new SportDataIODB(), scheduleRepository, successPresenter, new SportEventFactory());
        interactor.execute();
    }
}

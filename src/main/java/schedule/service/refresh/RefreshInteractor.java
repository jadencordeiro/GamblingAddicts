package schedule.service.refresh;

import api.SportDB;
import org.json.JSONArray;
import org.json.JSONObject;
import schedule.entity.Event;
import schedule.entity.EventFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RefreshInteractor implements RefreshInputBoundary{
    private final SportDB sportDB;
    final RefreshScheduleDataAccessInterface scheduleDataAccessObject;
    final RefreshOutputBoundary refreshPresenter;
    final EventFactory eventFactory;

    public RefreshInteractor(SportDB sportDB, RefreshScheduleDataAccessInterface scheduleDataAccessObject, RefreshOutputBoundary refreshPresenter, EventFactory eventFactory) {
        this.sportDB = sportDB;
        this.scheduleDataAccessObject = scheduleDataAccessObject;
        this.refreshPresenter = refreshPresenter;
        this.eventFactory = eventFactory;
    }

    @Override
    public void execute() {

        ArrayList<Event> events = UnpackJSONArray(sportDB.getEvents());
        for (Event event: events){
            if (!scheduleDataAccessObject.existsByName(event.getTitle())){
                scheduleDataAccessObject.save(event);
            }
        }

    }

    private ArrayList<Event> UnpackJSONArray(JSONArray events) {
        ArrayList<Event> games = new ArrayList<>();
        for (int i = 0; i < events.length(); i++) {
            JSONObject event = events.getJSONObject(i);
            Event game = eventFactory.create(event.getInt("GameKey"),
                    event.getString("HomeTeam"),
                    event.getString("AwayTeam"),
                    LocalDateTime.parse(event.getString("Date")));
            games.add(game);
        }
        return games;
    }
}

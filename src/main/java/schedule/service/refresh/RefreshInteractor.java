package schedule.service.refresh;

import api.SportDB;
import api.SportDataIODB;
import org.json.JSONArray;
import org.json.JSONObject;
import schedule.entity.Event;
import schedule.entity.EventFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

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
        Map<String, Event> schedule = scheduleDataAccessObject.getEvents();
        // delete old games
        String week = SportDataIODB.getWeek();
        for (Event event : schedule.values()) {
            if(!event.getWeek().equals(week)) {
                scheduleDataAccessObject.delete(event);
            }
        }
        // load new games
        JSONArray apiEvents = sportDB.getEvents();
        ArrayList<Event> events = UnpackJSONArray(apiEvents);
        for (Event event: events){
            if (!scheduleDataAccessObject.existsByName(event.getTitle())){
                scheduleDataAccessObject.save(event);
            }
        }

        // check activity of games
        for (int i = 0; i < apiEvents.length(); i++){
            JSONObject event = apiEvents.getJSONObject(i);
            JSONObject odds = event.getJSONArray("PregameOdds").getJSONObject(0);
            String title = event.getString("HomeTeam") + " vs " + event.getString("AwayTeam");
            int status = statusConverter(event.getString("Status"));
            if (status >= 0) {
                Event game = schedule.get(title);
                game.setActivity(status);
                game.setHomeScore(event.getInt("HomeScore"));
                game.setAwayScore(event.getInt("AwayScore"));
                game.setHomeOdds(odds.getInt("HomeMoneyLine"));
                game.setAwayOdds(odds.getInt("AwayMoneyLine"));
            }
        }



    }

    private ArrayList<Event> UnpackJSONArray(JSONArray events) {
        ArrayList<Event> games = new ArrayList<>();
        for (int i = 0; i < events.length(); i++) {
            JSONObject event = events.getJSONObject(i);
            Event game = eventFactory.create(event.getInt("GameID"),
                    event.getString("HomeTeamName"),
                    event.getString("AwayTeamName"),
                    LocalDateTime.parse(event.getString("Day")),
                    event.getString("Week"));
            games.add(game);
        }
        return games;
    }

    private int statusConverter(String status) {
        if (status.equals("InProgress")) {
            return 0;
        } else {
            return 1;
        }
    }
}

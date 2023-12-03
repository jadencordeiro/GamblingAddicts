package schedule.service.refresh;

import api.SportDB;
import api.SportDataIODB;
import org.json.JSONArray;
import org.json.JSONObject;
import schedule.entity.Event;
import schedule.entity.EventFactory;

import java.time.LocalDateTime;
import java.util.*;

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
        if(!schedule.keySet().isEmpty()){
            Event scheduledEvent = schedule.get(schedule.keySet().iterator().next());
            if(!scheduledEvent.getWeek().equals(week)) {
                scheduleDataAccessObject.delete();
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

        JSONArray eventsScores = sportDB.getScores();
        // update activity of games
        for (int i = 0; i < apiEvents.length(); i++){
            JSONObject event = apiEvents.getJSONObject(i);
            JSONObject odds = apiEvents.getJSONObject(i).getJSONArray("PregameOdds").getJSONObject(0);
            String title1 = event.getString("HomeTeamName") + " vs " + event.getString("AwayTeamName");
            Event game1 = schedule.get(title1);
            game1.setHomeOdds(odds.getFloat("HomeMoneyLine"));
            game1.setAwayOdds(odds.getFloat("AwayMoneyLine"));

            JSONObject eventScores = eventsScores.getJSONObject(i);
            String title2 = eventScores.getString("HomeTeam") + " vs " + eventScores.getString("AwayTeam");
            int status = statusConverter(eventScores.getString("Status"));
            Event game2 = schedule.get(title2);
            if (status >= 0) {
                game2.setActivity(status);
                game2.setHomeScore(eventScores.getInt("HomeScore"));
                game2.setAwayScore(eventScores.getInt("AwayScore"));
            }
        }

        RefreshOutputData refreshOutputData = outputDataBuilder(scheduleDataAccessObject.getEvents());
        refreshPresenter.prepareSuccessView(refreshOutputData);
    }

    private ArrayList<Event> UnpackJSONArray(JSONArray events) {
        ArrayList<Event> games = new ArrayList<>();
        for (int i = 0; i < events.length(); i++) {
            JSONObject event = events.getJSONObject(i);
            Event game = eventFactory.create(event.getInt("ScoreId"),
                    event.getString("HomeTeamName"),
                    event.getString("AwayTeamName"),
                    LocalDateTime.parse(event.getString("DateTime")),
                    String.valueOf(event.getInt("Week")));
            games.add(game);
        }
        return games;
    }

    private int statusConverter(String status) {
        if (status.equals("InProgress")) {
            return 0;
        } else if (status.equals("Final")) {
            return 1;
        } else {
            return -1;
        }
    }

    private RefreshOutputData outputDataBuilder(Map<String, Event> events_data){
        ArrayList<Map<String, Object>> events = new ArrayList<>();
        List<Event> list = new ArrayList<>(events_data.values());
        Collections.sort(list);

        for (Event e: list){
            Map<String, Object> event = new HashMap<>();
            event.put("home", e.getHome());
            event.put("away", e.getAway());
            event.put("status", e.getActivity());
            event.put("homeScore", e.getHomeScore());
            event.put("awayScore", e.getAwayScore());
            event.put("date", e.getDate());
            events.add(event);
        }
        return new RefreshOutputData(events);
    }
}

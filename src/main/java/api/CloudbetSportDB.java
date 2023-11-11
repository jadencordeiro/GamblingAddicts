package api;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import schedule.entity.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CloudbetSportDB implements SportDB {

    private static final String API_URL = "https://sports-api.cloudbet.com/pub";
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken(){return API_TOKEN;}

    @Override
    public ArrayList<Event> getEvents(LocalDateTime date) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "/v2/odds/fixtures?sport=american_football&date=%s", date.toLocalDate()))
                .addHeader("X-API-Key", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray competitions = responseBody.getJSONArray("competitions");
                ArrayList games = new ArrayList<>();
                JSONArray events = extractNFL(competitions);
                for (int i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    Event game = new Event(event.getInt("id"),
                                 event.getJSONObject("home").getString("name"),
                                 event.getJSONObject("away").getString("name"),
                                 LocalDateTime.parse(event.getString("cutoffTime")));
                    games.add(game);
                }
                return games;

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
    }

    private static JSONArray extractNFL(JSONArray competitions){
        for (int i = 0; i < competitions.length(); i++) {
            if (competitions.getJSONObject(i).getString("name").equals("NFL")){
                return competitions.getJSONObject(i).getJSONArray("events");
            }
        }
        return new JSONArray();
    }

}


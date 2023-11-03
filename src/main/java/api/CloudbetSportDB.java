package api;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import schedule.entity.Event;

import java.io.IOException;
import java.util.Date;

public class CloudbetSportDB implements SportDB {

    private static final String API_URL = "https://sports-api.cloudbet.com/pub";
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken(){return API_TOKEN;}

    @Override
    public Event getEvent(Date date) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "/v2/odds/fixtures?sport=american_football&date=%s", date))
                .addHeader("X-API-Key", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        return null;
    }

}


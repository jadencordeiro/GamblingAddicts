package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SportDataIODB implements SportDB {

    private static final String API_URL = "https://api.sportsdata.io/v3/nfl/odds/json";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private static LocalDate callDate;
    private static String week;
    private static String season;

    public static String getApiToken(){return API_TOKEN;}

    @Override
    public JSONArray getEvents() {
        updateVariables();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "/GameOddsByWeek/%s/%s?key=%s", season, week, API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            if (response.code() == 200) {
                return new JSONArray(response.body().string());
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONArray getScores() {
        updateVariables();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://api.sportsdata.io/v3/nfl/scores/json/ScoresByWeek/%s/%s?key=%s",
                        season, week, API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            if (response.code() == 200) {
                return new JSONArray(response.body().string());
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e){
            throw new RuntimeException(e);
        }
    }

    public static String getSeason() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://api.sportsdata.io/v3/nfl/scores/json/CurrentSeason?key=%s", API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            if (response.code() == 200) {
                return response.body().string();
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWeek() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://api.sportsdata.io/v3/nfl/scores/json/CurrentWeek?key=%s", API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            if (response.code() == 200) {
                return response.body().string();
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateVariables() {
        LocalDate today = LocalDate.now();
        if (!today.equals(callDate)) {
            week = getWeek();
            season = getSeason();
            callDate = today;
        }
    }
}


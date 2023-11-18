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

    private static final String API_URL = "https://api.sportsdata.io/v3/nfl/scores/json";
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
                .url(String.format(API_URL + "/ScoresByWeek/{%s}/{%s}?key=%s", season, week, API_TOKEN)).build();

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

    private String getSeason() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "/CurrentSeason?key=%s", API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return responseBody.toString();
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String getWeek() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(String.format(API_URL + "/CurrentWeek?key=%s", API_TOKEN)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return responseBody.toString();
            } else {
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


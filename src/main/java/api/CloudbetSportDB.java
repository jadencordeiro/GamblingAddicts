package api;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

public class CloudbetSportDB implements SportDB {

    private static final String API_URL = "https://sports-api.cloudbet.com/pub";
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken(){return API_TOKEN;}

    @Override
    public JSONArray getEvents() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "/v2/odds/fixtures?sport=american_football&date=%s", LocalDate.now()))
                .addHeader("X-API-Key", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray competitions = responseBody.getJSONArray("competitions");
                return extractNFL(competitions);
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


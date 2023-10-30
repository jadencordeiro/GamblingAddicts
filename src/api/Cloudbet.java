package api;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
public class Cloudbet implements CloudCall{

    private static String final String API_URL = "https://sports-api.cloudbet.com/pub/";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://sports-api.cloudbet.com/pub/v2/odds/fixtures?sport=%s&date=%s&limit=%s",
                        "soccer", "2023-10-01", 10))
                .addHeader("X-API-Key",  System.getenv("API_TOKEN"))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                System.out.println(responseBody);
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}


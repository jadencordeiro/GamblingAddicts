package api;
import org.json.JSONArray;

import java.time.LocalDateTime;

public interface SportDB {

    JSONArray getEvents();

    JSONArray getScores();
}

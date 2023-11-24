package schedule.service.refresh;

import java.util.ArrayList;
import java.util.Map;

public class RefreshOutputData {

    private final ArrayList<Map<String, Object>> events;

    public RefreshOutputData(ArrayList<Map<String, Object>> events){
        this.events = events;
    }

    public ArrayList<Map<String, Object>> getEvents(){
        return events;
    }
}
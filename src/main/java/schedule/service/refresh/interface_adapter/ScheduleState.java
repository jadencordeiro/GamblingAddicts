package schedule.service.refresh.interface_adapter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleState {

    private ArrayList<Map<String, Object>> events = new ArrayList<>();

    public ScheduleState(ScheduleState copy){
        events = copy.events;
    }

    public ScheduleState(){
    }

    public void setEvents(ArrayList<Map<String, Object>> events){
        this.events = events;
    }


    public ArrayList<Map<String, Object>> getEvents(){
        return events;
    }

    public String[][] getData() {
        String[][] array = new String[events.size()][5];
        int i = 0;
        for(Map<String, Object> game: events) {
            String[] a = {(String) game.get("home"), String.valueOf(game.get("homeScore")),
                    String.valueOf(game.get("awayScore")), (String) game.get("away"), String.valueOf(game.get("date"))};
            array[i] = a;
            i++;
        }
        return array;
    }
}

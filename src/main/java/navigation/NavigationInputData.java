package navigation;

public class NavigationInputData {

    final private String name;
    final private String user;

    public NavigationInputData(String name, String user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {return name;}

    public String getUser() {
        return user;
    }
}

package navigation;

public class NavigationOutputData {

    final private String name;
    final private String user;

    public NavigationOutputData(String name, String user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {return name;}

    public String getUser() {
        return user;
    }
}



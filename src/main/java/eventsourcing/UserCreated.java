package eventsourcing;

public class UserCreated extends Event{
    public String userName;
    public String email;

    public UserCreated(String userName,
                       String email) {
        this.userName = userName;
        this.email = email;
    }
}

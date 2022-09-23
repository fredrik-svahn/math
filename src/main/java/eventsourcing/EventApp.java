package eventsourcing;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EventApp {
    private static EventLog eventLog = new EventLog();



    public static void main(String[] args) {
        String chars =  "ABCDEFGHIJKLMNOPQRSTUVXYZ";

        for(int i = 0; i < 1e7; i++) {
            StringBuilder userName = new StringBuilder();

            for(int j = 0; j < 1; j++) {
                userName.append(chars.charAt((int)(Math.random() * chars.length())));
            }

            eventLog.emit(new UserCreated(userName.toString(), "fsv@kth.se"));
        }


        Set<String> takenUserNames = new HashSet<>();

        eventLog.handle(new EventLog.EventHandler() {
            void UserCreated(UserCreated event) {
                if(takenUserNames.contains(event.userName)) {
                    return;
                }

                takenUserNames.add(event.userName);
            }
        });

        System.out.println(takenUserNames.size());
    }
}

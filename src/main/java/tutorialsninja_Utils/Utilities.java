package tutorialsninja_Utils;

import java.util.Date;

public class Utilities {
    public static String generateTimeStamp() {
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":", "_");
        String email = "mathew" + timestamp + "@gmail.com";
        return email;
    }
}

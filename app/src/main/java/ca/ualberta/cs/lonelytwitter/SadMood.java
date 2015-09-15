package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by murdock on 9/15/15.
 */
public class SadMood extends Mood {
    public SadMood(Date date) {
        super(date);
    }

    public SadMood() {
    }

    public String getMood(){
        return ":C";
    }
}

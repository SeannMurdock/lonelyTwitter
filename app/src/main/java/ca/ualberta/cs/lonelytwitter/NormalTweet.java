package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by murdock on 9/15/15.
 */
public class NormalTweet extends Tweet {
    public NormalTweet(String text) {
        super(text);
    }

    public NormalTweet(String text, Date date) {
        super(text, date);
    }

    public Boolean isImportant(){
        return Boolean.FALSE;
    }
}

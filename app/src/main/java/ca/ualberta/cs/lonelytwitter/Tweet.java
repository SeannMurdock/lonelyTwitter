package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by murdock on 9/15/15.
 */
public abstract class Tweet implements Tweetable {
    private String text;
    private Date date;
    private ArrayList<Mood> moodlist;

    public ArrayList<Mood> getMoodlist() {
        return moodlist;
    }

    public void setMood(Mood mood) {
        this.moodlist.add(mood);
    }

    public String getText() {
        return text;

    }

    public void setText(String text){
            if (text.length() <= 140) {
                this.text = text;
            } else {
                throw new IllegalArgumentException("Tweets can't be that long, Shakespeare");
            }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date();
        this.moodlist = new ArrayList<Mood>();
    }

    public Tweet(String text, Date date) {
        this.text = text;
        this.date = date;
        this.moodlist = new ArrayList<Mood>();

    }

    public abstract Boolean isImportant();
}

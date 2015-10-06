package ca.ualberta.cs.lonelytwitter;

import android.text.BoringLayout;

import java.util.ArrayList;

/**
 * Created by murdock on 9/29/15.
 */
//This is model
// want to make observable
public class TweetList implements MyObservable, MyObserver {
    private Tweet mostRecentTweet;                                     //model
    private ArrayList<Tweet> tweets =  new ArrayList<Tweet>();             //model

    public void add(Tweet tweet){                                       //model
        if(hasTweet(tweet.getText())) throw new IllegalArgumentException("Illegal Argument"); //model
        else {
            mostRecentTweet = tweet;
            tweets.add(tweet);
            //
            tweet.addObserver(this);
            notifyAllObservers();
        }
    }

    public ArrayList<Tweet> getTweets(){
        return tweets;
    }

    public Tweet getMostRecentTweet (){
        return mostRecentTweet;
    }

    public int getcount(){
        return tweets.size();
    }

    public Boolean hasTweet(String newtweet){
        for (Tweet tweet : tweets) {
            if(tweet.getText().equals(newtweet)){
                return true;
            }
        }
        return false;
        }

    public int removeTweet(Tweet tweet) {
        int check = 0;
        if (hasTweet(tweet.getText())) {
            tweets.remove(tweet);
            check = 1;
        }
        return check;
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();


    public void addObserver(MyObserver observer) {
        observers.add(observer);

    }

    private void notifyAllObservers(){
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable) {
        notifyAllObservers();
    }



}

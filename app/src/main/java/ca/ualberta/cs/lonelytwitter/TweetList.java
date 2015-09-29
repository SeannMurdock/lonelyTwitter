package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by murdock on 9/29/15.
 */
public class TweetList {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets =  new ArrayList<Tweet>();

    public void add(Tweet tweet){
        if(hasTweet(tweet)) throw new IllegalArgumentException("Illegal Argument");
        else {
            mostRecentTweet = tweet;
            tweets.add(tweet);
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

    public boolean hasTweet(Tweet tweet){
        boolean boo = false;
        int c = this.getcount();
        for(int i = 0; i<c; i++){
            if (tweets.get(i) == tweet){
                boo = true;
            }
        }
        return boo;
    }

    public int removeTweet(Tweet tweet) {
        int check = 0;
        if (hasTweet(tweet)) {
            tweets.remove(tweet);
            check = 1;
        }
        return check;
    }

}

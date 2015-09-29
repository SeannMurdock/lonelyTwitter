package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by murdock on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void setUp(){

    }

    public void tearDown(){

    }

    public void testHoldsStuff() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.getcount(), 1);
        list.add(new NormalTweet("test2"));
        assertEquals(list.getcount(), 2);
    }
    
    public void testGetTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.add(new NormalTweet("test2"));
        list.add(new NormalTweet("test3"));
        list.getTweets();
    }

    public void testHasTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.add(new NormalTweet("test2"));
        list.add(new NormalTweet("test3"));
        assertEquals(list.hasTweet(tweet), true);
    }

    public void testAddDuplicateTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test1");
        Tweet tweet2 = new NormalTweet("test2");
        Tweet tweet3 = new NormalTweet("test3");
        Tweet tweet4 = new NormalTweet("test4");
        list.add(tweet);
        list.add(tweet2);
        list.add(tweet3);
        try {
            list.add(tweet);
            fail("missing exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal Argument", e.getMessage());
        }
    }

    public void testRemoveTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test1");
        Tweet tweet2 = new NormalTweet("test2");
        Tweet tweet3 = new NormalTweet("test3");
        Tweet tweet4 = new NormalTweet("test4");
        list.add(tweet);
        list.add(tweet2);
        list.add(tweet3);
        assertEquals(list.removeTweet(tweet2), 1);
    }
}
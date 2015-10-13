package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;
    private EditText editBodyText;
    private Button editSaveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }


    //UI testing, pretending to be the user
    public void testEditATweet() {
        // starts lonelytwitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        //reset the app to a known state
        activity.getTweets().clear();
        // user clicks on tweet they want to edit
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync(); // makes sure that all the threads finish
        bodyText.setText("hamburgers");
        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // makes sure that all the threads finish

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        // Set up an ActivityMonitor

        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync(); // makes sure that all the threads finish


        //Following was stolen from https://developer.android.com/training/activity-testing/activity-functional-testing.html October 2015


// Validate that ReceiverActivity is started
      //  TouchUtils.clickView(this, sendToReceiverButton);
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

// Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);


        // Test that the tweet being shown on the edit screen is the tweet we clicked on
        final ListView editTweets = receiverActivity.getEditTweet();
        Tweet editTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", editTweet.getText());

        //edit the text of that tweet
        editBodyText = receiverActivity.getBodyText();
        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                editBodyText.setText("cheeseburgers");
            }
        });
        getInstrumentation().waitForIdleSync(); // makes sure that all the threads finish

        editSaveButton = receiverActivity.getSaveButton();
        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                editSaveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // makes sure that all the threads finish


        editTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("cheeseburgers", editTweet.getText());


        // save our edits

        //assert that our edits were saved into tweet correctly

        //assert that our edits are shown on the screen to the user back in the main activity

        //end of test: clear the data
        //end of test: make sure the edit activity is closed
        receiverActivity.finish();


    }

}
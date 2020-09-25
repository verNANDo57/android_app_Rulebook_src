package com.verNANDo57.rulebook_educational;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.verNANDo57.rulebook_educational.customthemeengine.app.CustomThemeEngineAppCompatActivity;
import com.verNANDo57.rulebook_educational.for_pills.R;
import com.verNANDo57.rulebook_educational.preferences.RulebookApplicationSharedPreferences;

@SuppressLint("ClickableViewAccessibility")
public class AppRatingAgressiveActivity extends CustomThemeEngineAppCompatActivity {

    RulebookApplicationSharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences =  new RulebookApplicationSharedPreferences(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_bottomappbar_in_rating_page_agressive);



        //BottomAppBar
        final BottomAppBar bar_in_rating_page = findViewById(R.id.bar_in_rating_page);
        setSupportActionBar(bar_in_rating_page);
        //Antimation when activity starts------------------------------------------------
        if(preferences.loadRulebookAnimationsDisableState()==false) {
            TranslateAnimation animate = new TranslateAnimation(
                    0, 0, 250, 0);
            animate.setDuration(250);
            animate.setFillAfter(false);
            bar_in_rating_page.startAnimation(animate);
        }
        //End of Animation---------------------------------------------------------------

        //Hide on SwipeDown
        if (preferences.loadRulebookBottomAppBarAutoHideBooleanState()==false){
            bar_in_rating_page.setOnTouchListener(new View.OnTouchListener() {

                float mY;
                float swipeDistance;
                final float REQUIRED_SWIPE = 25;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float y = event.getY();

                    switch(event.getAction()) {

                        case MotionEvent.ACTION_DOWN :
                            swipeDistance = 0;
                            mY = y;
                            break;

                        case MotionEvent.ACTION_MOVE :
                            swipeDistance += y - mY;
                            if(Math.abs(swipeDistance) > REQUIRED_SWIPE) {
                                if(swipeDistance > 0) {
                                    if(bar_in_rating_page.getVisibility() == View.VISIBLE) {
                                        if(preferences.loadRulebookAnimationsDisableState()==false) {
                                            TranslateAnimation animate = new TranslateAnimation(
                                                    0,0,0,bar_in_rating_page.getHeight());
                                            animate.setDuration(250);
                                            animate.setFillAfter(false);
                                            bar_in_rating_page.startAnimation(animate);
                                        }
                                        bar_in_rating_page.setVisibility(View.GONE);
                                        swipeDistance = 0;
                                    }
                                }
                            }
                            mY = y;
                            break;
                    }
                    mY = y;
                    return false;
                }
            });
        }

        ScrollView rating_page_scrollview = findViewById(R.id.rating_page_scrollview);
        if(preferences.loadRulebookBottomAppBarAutoHideBooleanState()==true){
            rating_page_scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    //see https://gist.github.com/aqua30/e8623abaff190ee86727ee5ae8dac82a
                    int movement = rating_page_scrollview.getScrollY();

                    if(movement >= 100){
                        if (bar_in_rating_page.getVisibility() == View.VISIBLE) {
                            if (preferences.loadRulebookAnimationsDisableState() == false) {
                                TranslateAnimation animate = new TranslateAnimation(
                                        0, 0, 0, bar_in_rating_page.getHeight());
                                animate.setDuration(250);
                                animate.setFillAfter(false);
                                bar_in_rating_page.startAnimation(animate);
                            }
                            bar_in_rating_page.setVisibility(View.GONE);
                        }
                    } else if(movement >= -100){
                        if (bar_in_rating_page.getVisibility() == View.GONE) {
                            if (preferences.loadRulebookAnimationsDisableState() == false) {
                                TranslateAnimation animate = new TranslateAnimation(
                                        0, 0, bar_in_rating_page.getHeight(), 0);
                                animate.setDuration(250);
                                animate.setFillAfter(false);
                                bar_in_rating_page.startAnimation(animate);
                            }
                            bar_in_rating_page.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        //Press the smile to view the bottomappbar
        AppRatingAgressiveVector app_agressive_rating_vector = findViewById(R.id.app_agressive_rating_vector);
        if(preferences.loadRulebookBottomAppBarAutoHideBooleanState()==false){
            app_agressive_rating_vector.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bar_in_rating_page.getVisibility() == View.GONE) {
                        if(preferences.loadRulebookAnimationsDisableState()==false) {
                            TranslateAnimation animate = new TranslateAnimation(
                                    0,0,bar_in_rating_page.getHeight(),0);
                            animate.setDuration(250);
                            animate.setFillAfter(false);
                            bar_in_rating_page.startAnimation(animate);
                        }
                        bar_in_rating_page.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        //Title in Layout
        TextView rating_title = findViewById(R.id.rating_title);
            //Antimation when activity starts------------------------------------------------
                if(preferences.loadRulebookAnimationsDisableState()==false) {
                    TranslateAnimation animate_rating_title = new TranslateAnimation(
                            0, 0, 250, 0);
                    animate_rating_title.setDuration(150);
                    animate_rating_title.setFillAfter(false);
                    rating_title.startAnimation(animate_rating_title);
                }
            //End of Animation---------------------------------------------------------------

        //Rating Bar in Layout
        RatingBar rating_bar = findViewById(R.id.rating_bar);
        //Antimation when activity starts------------------------------------------------
        if(preferences.loadRulebookAnimationsDisableState()==false) {
            TranslateAnimation animate_rating_bar = new TranslateAnimation(
                    0, 0, 250, 0);
            animate_rating_bar.setDuration(200);
            animate_rating_bar.setFillAfter(false);
            rating_bar.startAnimation(animate_rating_bar);
        }
        //End of Animation---------------------------------------------------------------
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("Rating", String.valueOf(rating));
                app_agressive_rating_vector.setSmiley(rating);
            }
        });

        //Edittext for Review
        EditText review_box = findViewById(R.id.review_box);
            //Antimation when activity starts------------------------------------------------
                if(preferences.loadRulebookAnimationsDisableState()==false) {
                    TranslateAnimation animate_box = new TranslateAnimation(
                            0, 0, 250, 0);
                    animate_box.setDuration(250);
                    animate_box.setFillAfter(false);
                    review_box.startAnimation(animate_box);
                }
            //End of Animation---------------------------------------------------------------

        //Button to send review to dev
        Button review_send = findViewById(R.id.review_send_button);
        review_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Custom Method 7
                SendReview();
            }
        });
            //Antimation when activity starts------------------------------------------------
                if(preferences.loadRulebookAnimationsDisableState()==false) {
                    TranslateAnimation animate_sendbutton = new TranslateAnimation(
                            0, 0, 250, 0);
                    animate_sendbutton.setDuration(300);
                    animate_sendbutton.setFillAfter(false);
                    review_send.startAnimation(animate_sendbutton);
                }
            //End of Animation---------------------------------------------------------------
    }

    //system navigationbar using
    public boolean onKeyDown(int keyCode, KeyEvent event) {     switch (keyCode) {     case KeyEvent.KEYCODE_BACK:
        if (android.os.Build.VERSION.SDK_INT <android.os.Build.VERSION_CODES.ECLAIR     && event.getRepeatCount() == 0) {     onBackPressed();     }     }
        return super.onKeyDown(keyCode, event); }
    public void onBackPressed(){ finish(); }
    //end of navbar using
    
    //Custom Method 7
    public void SendReview(){
        //Include data from custom Method 6
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screeenwidth = dm.widthPixels;
        int screenheight = dm.heightPixels;
        int screendens = dm.densityDpi;
        double wi = (double)screeenwidth / (double)screendens;
        double hi = (double)screenheight / (double)screendens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x+y);
        screenInches = (double) Math.round(screenInches * 10) / 10;

        EditText review_box = findViewById(R.id.review_box);
        RatingBar rating_bar = findViewById(R.id.rating_bar);

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.app_dev_email)});
        email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_dev_part1) + String.valueOf(rating_bar.getRating()) + getString(R.string.app_dev_part2));
        email.putExtra(Intent.EXTRA_TEXT, review_box.getText().toString() + "\n" +"\n"
                + getString(R.string.review_device_name) + Build.DEVICE + "\n"
                + getString(R.string.review_device_model) + Build.MODEL + "\n"
                + getString(R.string.review_device_manufacter) + Build.MANUFACTURER + "\n"
                + getString(R.string.review_oc_name) + Build.ID + "\n"
                + getString(R.string.review_device_sdk_version) + Build.VERSION.SDK_INT + "\n"
                + getString(R.string.review_device_hardware) + Build.HARDWARE + "\n"
                + getString(R.string.review_device_brand) + Build.BRAND + "\n"
                + getString(R.string.review_device_fingerprint) + Build.FINGERPRINT + "\n"
                + getString(R.string.review_device_serial) + Build.SERIAL + "\n"
                + getString(R.string.review_device_android_version) + Build.VERSION.RELEASE + "\n"
                + getString(R.string.review_device_screen_resolution) + screeenwidth + "x" + screenheight + "\n"
                + getString(R.string.review_device_current_dpi_value) + screenInches);
        //need this to prompts email client only
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, getString(R.string.app_choose_email_client)));
    }
}
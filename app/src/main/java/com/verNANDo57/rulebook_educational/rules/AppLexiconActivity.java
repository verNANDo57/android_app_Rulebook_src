package com.verNANDo57.rulebook_educational.rules;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.verNANDo57.rulebook_educational.BottomNavBetweenLessonsFragment;
import com.verNANDo57.rulebook_educational.customthemeengine.app.CustomThemeEngineAppCompatActivity;
import com.verNANDo57.rulebook_educational.extradata.R;
import com.verNANDo57.rulebook_educational.preferences.RulebookApplicationSharedPreferences;
import com.verNANDo57.rulebook_educational.tools.Utils;

import static com.verNANDo57.rulebook_educational.tools.Utils.LOG_TAG;

public class AppLexiconActivity extends CustomThemeEngineAppCompatActivity {

    public String lexiconfile = "lexicon.html";
    RulebookApplicationSharedPreferences preferences;

    @SuppressLint({"ClickableViewAccessibility", "SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState)
    {
        preferences =  new RulebookApplicationSharedPreferences(this);

        super.onCreate(savedInstanceState);

        //Activity Content as LAYOUT
        setContentView(R.layout.app_bottomappbar_in_webview);

        //init WebView
        WebView app_rules_lexicon = findViewById(R.id.app_rules_webview);
        // displaying content in WebView from html file that stored in assets folder
        app_rules_lexicon.loadUrl("file:///android_asset/lexicon/" + lexiconfile);
        app_rules_lexicon.getSettings().setJavaScriptEnabled(true);
        //WebView preferences
        app_rules_lexicon.setInitialScale(255);

        //disable text selection
        app_rules_lexicon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        app_rules_lexicon.setLongClickable(false);

        app_rules_lexicon.getSettings().setSupportZoom(true);
        app_rules_lexicon.getSettings().setBuiltInZoomControls(true);
        app_rules_lexicon.getSettings().setDisplayZoomControls(false);

        //BottomAppBar
        final FloatingActionButton fab_in_schemes_and_tables = findViewById(R.id.fab_in_webview);
        fab_in_schemes_and_tables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final BottomAppBar bar_in_lexicon = findViewById(R.id.bar_in_webview);
        setSupportActionBar(bar_in_lexicon);
        if(preferences.loadRulebookAnimationsDisableState()==false) {
            Utils.setAnimatorToAnyView(bar_in_lexicon, "to_top", (float) 250);
        }

        //BottomNavigationView
        bar_in_lexicon.setNavigationOnClickListener(new NavigationView.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavBetweenLessonsFragment BottomNavBetweenLessons = new BottomNavBetweenLessonsFragment();
                BottomNavBetweenLessons.show(getSupportFragmentManager(), LOG_TAG);
            }
        });

        app_rules_lexicon.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                //see https://gist.github.com/aqua30/e8623abaff190ee86727ee5ae8dac82a
                int movement = app_rules_lexicon.getScrollY();

                if(movement >= 100){
                    if (bar_in_lexicon.getVisibility() == View.VISIBLE) {
                        if (preferences.loadRulebookAnimationsDisableState() == false) {
                            Utils.setAnimatorToAnyView(bar_in_lexicon, "to_bottom");
                        }
                        bar_in_lexicon.setVisibility(View.GONE);
                        fab_in_schemes_and_tables.hide();
                    }
                } else if(movement >= -100){
                    if (bar_in_lexicon.getVisibility() == View.GONE) {
                        if (preferences.loadRulebookAnimationsDisableState() == false) {
                            Utils.setAnimatorToAnyView(bar_in_lexicon, "to_top");
                        }
                        bar_in_lexicon.setVisibility(View.VISIBLE);
                        fab_in_schemes_and_tables.show();
                    }
                }
            }
        });
    }
}

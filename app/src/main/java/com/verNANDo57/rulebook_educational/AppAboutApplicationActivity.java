package com.verNANDo57.rulebook_educational;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.verNANDo57.rulebook_educational.customthemeengine.app.CustomThemeEngineAppCompatActivity;
import com.verNANDo57.rulebook_educational.for_pills.R;
import com.verNANDo57.rulebook_educational.preferences.RulebookApplicationSharedPreferences;


public class AppAboutApplicationActivity extends CustomThemeEngineAppCompatActivity
{
	RulebookApplicationSharedPreferences preferences;

	@SuppressLint("ClickableViewAccessibility")
	public void onCreate(Bundle savedInstanceState)
    {
		preferences =  new RulebookApplicationSharedPreferences(this);

		super.onCreate(savedInstanceState);

        //Activity Content as LAYOUT
        setContentView(R.layout.app_bottomappbar_in_about);

		//BottomAppBar
		final FloatingActionButton fab_in_about = findViewById(R.id.fab_in_about);
		fab_in_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view){
				finish();
			}
		});
		final BottomAppBar bar_in_credits = findViewById(R.id.bar_in_about);
		setSupportActionBar(bar_in_credits);

		//BottomNavigationView
		bar_in_credits.setNavigationOnClickListener(new NavigationView.OnClickListener() {
			@Override
			public void onClick(View v) {
				BottomNavBetweenActivitiesFragment BottomNavBetweenActivities = new BottomNavBetweenActivitiesFragment();
				BottomNavBetweenActivities.show(getSupportFragmentManager(), "TAG");
			}
		});

		TextView app_about_title = findViewById(R.id.app_about_title);

		ScrollView about_scrollview = findViewById(R.id.about_scrollview);
		about_scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				//see https://gist.github.com/aqua30/e8623abaff190ee86727ee5ae8dac82a
				int movement = about_scrollview.getScrollY();
				if(movement >= 100){
					if (bar_in_credits.getVisibility() == View.VISIBLE) {
						if (preferences.loadRulebookAnimationsDisableState() == false) {
							TranslateAnimation animate = new TranslateAnimation(
									0, 0, 0, bar_in_credits.getHeight());
							animate.setDuration(250);
							animate.setFillAfter(false);
							bar_in_credits.startAnimation(animate);
						}
						bar_in_credits.setVisibility(View.GONE);
						fab_in_about.hide();
					}
				} else if(movement >= -100){
					if (bar_in_credits.getVisibility() == View.GONE) {
						if (preferences.loadRulebookAnimationsDisableState() == false) {
							TranslateAnimation animate = new TranslateAnimation(
									0, 0, bar_in_credits.getHeight(), 0);
							animate.setDuration(250);
							animate.setFillAfter(false);
							bar_in_credits.startAnimation(animate);
						}
						bar_in_credits.setVisibility(View.VISIBLE);
						fab_in_about.show();
					}
				}
			}
		});

		Button review_page = findViewById(R.id.review_page);
		review_page.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Custom Method 3
				rate_us();
			}
		});

		ImageView about_preview = findViewById(R.id.about_preview);
		about_preview.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);

		ImageButton buttongit = findViewById(R.id.buttongit);
		buttongit.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
		buttongit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String github = "https://github.com/verNANDo57";
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setData(Uri.parse(github));
				startActivity(intent3);
			}
		});

		ImageButton buttonvk = findViewById(R.id.buttonvk);
		buttonvk.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
		buttonvk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String vk = "https://vk.com/vernando57";
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setData(Uri.parse(vk));
				startActivity(intent3);
		}
		});

		ImageButton buttontg = findViewById(R.id.buttontg);
		buttontg.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
		buttontg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String telegram = "https://t.me/VerNANDo57";
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setData(Uri.parse(telegram));
				startActivity(intent3);
		}
		});

		ImageButton buttonqiwi = findViewById(R.id.buttonqiwi);
		buttonqiwi.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
		buttonqiwi.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				String qiwi = "https://qiwi.com/n/VERNANDO57";
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setData(Uri.parse(qiwi));
				startActivity(intent3);
			}
		});
	}

    //ActionBar_Elements
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_menu, menu);
		return true;
	}

	@SuppressLint("NonConstantResourceId")
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.preferences_screen:
			Intent AppRulebookPreferencesScreenActivity = new Intent(this, com.verNANDo57.rulebook_educational.preferences.AppSettingsActivity.class);     startActivity(AppRulebookPreferencesScreenActivity);
			return true;

			case R.id.exit_actionbar_extended:
				androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.app_exit));
				builder.setMessage(getString(R.string.are_you_sure));
				builder.setIcon(R.drawable.ic_warning_outline);
				builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finishAffinity();
					}
				});
				builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				androidx.appcompat.app.AlertDialog alert = builder.create();
				alert.show();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	public void rate_us(){
		final Intent rate_app = new Intent(this, AppRatingAgressiveActivity.class);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			rate_app.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
		} else {
			rate_app.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		rate_app.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		startActivity(rate_app);
	}
}

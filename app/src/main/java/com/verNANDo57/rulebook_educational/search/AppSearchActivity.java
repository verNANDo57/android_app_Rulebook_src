package com.verNANDo57.rulebook_educational.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.verNANDo57.rulebook_educational.BottomNavBetweenLessonsFragment;
import com.verNANDo57.rulebook_educational.customthemeengine.app.CustomThemeEngineAppCompatActivity;
import com.verNANDo57.rulebook_educational.extradata.R;

import java.util.ArrayList;
import java.util.Locale;

import static com.verNANDo57.rulebook_educational.tools.Utils.LOG_TAG;

public class AppSearchActivity extends CustomThemeEngineAppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private EditText search_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_search);

        Toolbar toolbar = findViewById(R.id.toolbar_in_search);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new NavigationView.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavBetweenLessonsFragment BottomNavFragmentFromLessons = new BottomNavBetweenLessonsFragment();
                BottomNavFragmentFromLessons.show(getSupportFragmentManager(), LOG_TAG);
            }
        });

        // Init
        recyclerView = findViewById(R.id.search_recyclerview);
        searchAdapter = new SearchAdapter(SearchReferences.setupSearchData(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        search_edittext = findViewById(R.id.search_edittext);
        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Auto-generated method
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Auto-generated method
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Auto-generated method
               filter(s.toString());
            }
        });
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayList<SearchItemData> filteredList = new ArrayList<>();
        for (SearchItemData item : SearchReferences.setupSearchData(getApplicationContext())) {
            if (item.getItemTitle().toLowerCase().contains(charText)) {
                filteredList.add(item);
            } else if (item.getItemDescription().toLowerCase().contains(charText)) {
                filteredList.add(item);
            }
        }
        this.searchAdapter.filterList(filteredList);
        //notifyDataSetChanged();
    }
}

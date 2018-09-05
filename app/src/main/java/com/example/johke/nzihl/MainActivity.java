package com.example.johke.nzihl;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BottomBarFragment> fragments = new ArrayList<>(5);
    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_NEWS = "tag_frag_news";
    private static final String TAG_FRAGMENT_SCORES = "tag_frag_scores";
    private static final String TAG_FRAGMENT_STATS = "tag_frag_stats";
    private static final String TAG_FRAGMENT_SETTINGS = "tag_frag_settings";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0, TAG_FRAGMENT_HOME);
                    return true;
                case R.id.navigation_news:
                    switchFragment(1, TAG_FRAGMENT_NEWS);
                    return true;
                case R.id.navigation_scores:
                    switchFragment(2, TAG_FRAGMENT_SCORES);
                    return true;
                case R.id.navigation_stats:
                    switchFragment(3, TAG_FRAGMENT_STATS);
                    return true;
                case R.id.navigation_settings:
                    switchFragment(4, TAG_FRAGMENT_SETTINGS);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        buildFragmentsList();
        switchFragment(0, TAG_FRAGMENT_HOME);
    }

    private void buildFragmentsList() {
        BottomBarFragment homeFragment = buildFragment("Home");
        BottomBarFragment newsFragment = new NewsFragment();
        BottomBarFragment scoresFragment = buildFragment("Scores");
        BottomBarFragment statsFragment = buildFragment("Stats");
        BottomBarFragment settingsFragment = buildFragment("Settings");

        fragments.add(homeFragment);
        fragments.add(newsFragment);
        fragments.add(scoresFragment);
        fragments.add(statsFragment);
        fragments.add(settingsFragment);
    }

    private BottomBarFragment buildFragment(String title) {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BottomBarFragment.ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void switchFragment(int pos, String tag) {
        getFragmentManager().beginTransaction().replace(R.id.frame_fragmentholder, fragments.get(pos), tag).commit();
    }
}

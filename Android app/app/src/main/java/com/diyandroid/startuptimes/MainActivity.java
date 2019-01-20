package com.diyandroid.startuptimes;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    private NewsFragment newsFragment;
    private ReportingFragment reportingFragment;
    private UserFragment userFragment;
    private BlankFragment blankFragment;

    BottomNavigationView mMainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = (BottomNavigationView) findViewById(R.id.bottomNav);

        newsFragment = new NewsFragment();
        reportingFragment = new ReportingFragment();
        userFragment = new UserFragment();
        blankFragment = new BlankFragment();

        setFragment(newsFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.newsMenu:
                        setFragment(newsFragment);
                        return true;

                    case R.id.videosMenu:
                        setFragment(blankFragment);
                        return true;

                    case R.id.favoriteMenu:
                        setFragment(newsFragment);
                        return true;

                    case R.id.reportingMenu:
                        setFragment(reportingFragment);
                        return true;

                    case R.id.accountMenu:
                        setFragment(userFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to exit!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}

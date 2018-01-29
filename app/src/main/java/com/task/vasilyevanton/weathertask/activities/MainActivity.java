package com.task.vasilyevanton.weathertask.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.task.vasilyevanton.weathertask.R;
import com.task.vasilyevanton.weathertask.fragments.SettingsFragment;
import com.task.vasilyevanton.weathertask.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity {
    private static long sBackPressed;
    private Fragment settingsFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityInit();

        Fragment fragment = new WeatherFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void activityInit() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_action_settings) {
            if (settingsFragment == null) {
                settingsFragment = new SettingsFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).addToBackStack(null).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 0) {
            if (sBackPressed + 3000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show();
            }
            sBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            fm.popBackStack();
        }

    }
}

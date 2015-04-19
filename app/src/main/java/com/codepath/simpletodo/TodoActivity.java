package com.codepath.simpletodo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by ssun on 4/18/15.
 */

public class TodoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

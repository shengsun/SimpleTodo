package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class EditItemActivity extends ActionBarActivity {

    int position;
    String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        EditText etEditField = (EditText) findViewById(R.id.etEditItem);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgEditPriority);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbAddHigh: priority = "High";
                        break;
                    case R.id.rbAddMedium: priority = "Medium";
                        break;
                    case R.id.rbAddLow: priority  = "Low";
                        break;
                    default: priority = "Invalid Priority";
                        break;
                }

                Toast.makeText(getApplicationContext(), priority + "Priority",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        String item = intent.getStringExtra("item");
        String priority = intent.getStringExtra("priority");

        etEditField.setText(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSave(View v) {
        EditText etEditField = (EditText) findViewById(R.id.etEditItem);

        Intent data = new Intent();
        data.putExtra("item", etEditField.getText().toString());
        data.putExtra("priority", priority);
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        finish();
    }
}

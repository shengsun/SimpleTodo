package com.codepath.simpletodo;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements NewItemDialog.NewItemDialogListener {

    ArrayList<TodoItem> items;
    ArrayAdapter<TodoItem> itemsAdapter;
    ListView lvItems;

    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = TodoItem.loadAll();

        itemsAdapter = new TodoItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    @Override
    public void onFinishEditDialog(String title, String priority) {
        TodoItem item = new TodoItem(title, priority);
        item.save();
        itemsAdapter.add(item);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.get(position).delete();
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();

                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        TodoItem item = items.get(position);
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);

                        i.putExtra("priority", item.Priority);
                        i.putExtra("position", position);
                        i.putExtra("id", item.getId());
                        i.putExtra("item", item.Title);
                        startActivityForResult(i, REQUEST_CODE);
                    }
                }
        );
    }

//    private void writeItems() {
//        File filesDir = getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//
//        try {
//            FileUtils.writeLines(todoFile, items);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onAddItem(View v) {
//        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
//        String itemText = etNewItem.getText().toString();
//        //itemsAdapter.add(itemText);
//        etNewItem.setText("");
//        writeItems();
//    }

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
        if (id == R.id.add_new) {
            onNewItem();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onNewItem() {
        FragmentManager fm = getFragmentManager();
        NewItemDialog frag = new NewItemDialog();
        frag.show(fm, "fragment_add_item");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int position = data.getExtras().getInt("position");
            String editedItem = data.getExtras().getString("item");
            String priority = data.getExtras().getString("priority");

            TodoItem item = items.get(position);
            item.Title = editedItem;
            item.Priority = priority;
            item.save();

            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Item edited", Toast.LENGTH_SHORT).show();
        }
    }
}

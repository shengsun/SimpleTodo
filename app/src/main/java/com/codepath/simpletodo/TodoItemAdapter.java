package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by ssun on 4/19/15.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    public TodoItemAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        TodoItem item = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);

        tvTitle.setText(item.Title);
        tvPriority.setText(item.Priority);

        return view;
    }
}

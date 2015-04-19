package com.codepath.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssun on 4/19/15.
 */

/**
 * Manage the items via SQLite
 */
@Table(name = "TodoItem")
public class TodoItem extends Model {

    @Column(name = "title")
    public String Title;

    @Column(name = "priority")
    public String Priority;

    public TodoItem() {
        super();
    }

    public TodoItem(String title, String priority) {
        super();
        this.Title = title;
        this.Priority = priority;
    }

    public static ArrayList<TodoItem> loadAll() {
        List l = new Select().from(TodoItem.class).execute();
        return new ArrayList<TodoItem>(l);
    }
}

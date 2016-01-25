package com.example.nancy.cs193hw2;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nancy on 1/20/16.
 */
public class CustomListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> todoitems = new ArrayList<String>();
    private Context context;

    public CustomListAdapter(ArrayList<String> list, Context context) {
        this.todoitems = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todoitems.size();
    }

    @Override
    public Object getItem(int pos) {
        return todoitems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.todo_list_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listitemText = (TextView)view.findViewById(R.id.listitem);
        listitemText.setText(todoitems.get(position));

        //Handle delete Button and add onClickListeners
        CheckBox deleteCheck = (CheckBox)view.findViewById(R.id.deletecheckbox);

        deleteCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        todoitems.remove(position);
                        notifyDataSetChanged();
                    }
                }, 500);
            }
        });
        return view;
    }
}


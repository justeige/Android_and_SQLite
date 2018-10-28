package com.example.my.sql_test;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class RecordListActivity extends AppCompatActivity {

    private DBManager           _dbManager;
    private ListView            _listView;
    private SimpleCursorAdapter _adapter;

    final String[] from = new String[] { DatabaseHelper.ID, DatabaseHelper.RECORD_NAME, DatabaseHelper.DESC};
    final int[] to = new int[] {R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.empty_list);

        _dbManager = new DBManager(this);
        _dbManager.createNew();
        Cursor cursor = _dbManager.fetch_all();

        _listView = (ListView) findViewById(R.id.list_view);
        _listView.setEmptyView(findViewById(R.id.empty));

        _adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        _adapter.notifyDataSetChanged();

        _listView.setAdapter(_adapter);

        // OnCLickListiner For List Items
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyRecordActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddRecordActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}



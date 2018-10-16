package com.example.my.sql_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecordActivity extends Activity implements View.OnClickListener {

    private Button _addBtn;
    private EditText _editTextSubj;
    private EditText _editTextDesc;

    private DBManager _dbManager;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        _editTextSubj = (EditText) findViewById(R.id.subject_edittext);
        _editTextDesc = (EditText) findViewById(R.id.description_edittext);
        _addBtn = (Button) findViewById(R.id.add_record);

        _dbManager = new DBManager(this);
        _dbManager.createNew();
        _addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_record:
                final String name = _editTextSubj.getText().toString();
                final String desc = _editTextDesc.getText().toString();

                _dbManager.insert(name, desc);

                Intent main = new Intent(AddRecordActivity.this, RecordListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}

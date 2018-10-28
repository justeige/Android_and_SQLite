package com.example.my.sql_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecordActivity extends Activity implements View.OnClickListener {

    private EditText _editTextSubj;
    private EditText _editTextDesc;

    private DBManager _dbManager;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        _editTextSubj = findViewById(R.id.subject_edittext);
        _editTextDesc = findViewById(R.id.description_edittext);
        Button addBtn = findViewById(R.id.add_record);

        _dbManager = DBManager.createAndOpenDB(this);
        addBtn.setOnClickListener(this);
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

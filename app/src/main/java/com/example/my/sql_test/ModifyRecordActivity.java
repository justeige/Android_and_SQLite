package com.example.my.sql_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

class ModifyRecordActivity extends Activity implements View.OnClickListener {

    private EditText _titleText;
    private Button   _updateBtn, _deleteBtn;
    private EditText _descText;

    private long _id;
    private DBManager _dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        _dbManager = new DBManager(this);
        _dbManager.createNew();

        _titleText = (EditText) findViewById(R.id.subject_edittext);
        _descText = (EditText) findViewById(R.id.description_edittext);

        _updateBtn = (Button) findViewById(R.id.btn_update);
        _deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        _titleText.setText(name);
        _descText.setText(desc);

        _updateBtn.setOnClickListener(this);
        _deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = _titleText.getText().toString();
                String desc  = _descText.getText().toString();

                _dbManager.update(_id, title, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:
                _dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), RecordListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}

package com.slesh.willread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "by.slesh.willread.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText view = (EditText) findViewById(R.id.editText);
        String messageText = view.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, messageText);
        startActivity(intent);
    }

}

package com.slesh.willread;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ActiveFragment.ViewingCounter {

    public static final String EXTRA_MESSAGE = "by.slesh.willread.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("view details", savedInstanceState != null
                ? savedInstanceState.toString()
                : "empty");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment activeFragment = new ActiveFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, activeFragment)
                .commit();

    }

    public void next(View nextButton) {
        Log.d("view details", nextButton.toString());
        Fragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void sendMessage(View sendButton) {
        Log.d("view details", sendButton.toString());
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText view = findViewById(R.id.editText);
        String messageText = view.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, messageText);
        startActivity(intent);
    }

    private int count;

    @Override
    public int count() {
        return count;
    }

    @Override
    public int inc() {
        return count;
    }

}

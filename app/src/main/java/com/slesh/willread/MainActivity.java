package com.slesh.willread;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "by.slesh.willread.MESSAGE";
    public static final String HISTORY = "history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("app", savedInstanceState != null
                ? savedInstanceState.toString()
                : "empty");
        setContentView(R.layout.activity_main);

        ActiveFragment activeFragment = new ActiveFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, activeFragment)
                .commit();
    }

    public void save(View saveButton) {
        EditText editText = findViewById(R.id.editText);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(getCacheDir().getPath(), "history"), true);
            out.write(getHistory().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        addHistoryRecord(editText.getText().toString());
        updateHistory();
    }

    private String getHistory() {
        Set<String> historyRecords = getPreferences(MODE_PRIVATE)
                .getStringSet(HISTORY, new HashSet<>());
        StringBuilder historyBuilder = new StringBuilder();
        for (String historyItem : historyRecords) {
            historyBuilder.append(historyItem).append('\n');
        }
        return historyBuilder.toString();
    }

    private void addHistoryRecord(String record) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Set<String> history = preferences.getStringSet(HISTORY, new HashSet<>());
        history.add(record);
        preferences.edit().putStringSet(HISTORY, history).apply();
    }

    public void clean(View sendButton) {
        Log.d("view details", sendButton.toString());
        cleanHistory();
        updateHistory();
    }

    private void cleanHistory() {
        getPreferences(MODE_PRIVATE).edit().putStringSet(HISTORY, new HashSet<>()).apply();
    }

    private void updateHistory() {
        ActiveFragment activeFragment = (ActiveFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainer);
        activeFragment.updateHistory();
    }

    public void next(View nextButton) {
        Log.d("app", nextButton.toString());
        DetailsFragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

}

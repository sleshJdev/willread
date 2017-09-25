package com.slesh.willread;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.slesh.willread.db.BookmarkRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public BookmarkRepository bookmarkRepository;

    public static final String EXTRA_MESSAGE = "by.slesh.willread.MESSAGE";
    public static final String HISTORY = "history";

    private AppComponent appComponent;

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

    public void openBookmarks(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void save(View saveButton) {
        EditText editText = findViewById(R.id.editText);
        FileOutputStream out;
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
        if (new File(getCacheDir(), HISTORY).delete()) {
            Log.d("app", "history file was removed");
        }
    }

    private void updateHistory() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof ActiveFragment) {
            ((ActiveFragment) fragment).updateHistory();
        }
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

    @Override
    protected void onDestroy() {
        bookmarkRepository.close();
        super.onDestroy();
    }
}

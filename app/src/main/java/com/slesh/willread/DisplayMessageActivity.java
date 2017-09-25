package com.slesh.willread;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.slesh.willread.db.BookmarkContract.Bookmark;
import com.slesh.willread.db.BookmarkRepository;
import com.slesh.willread.ioc.ServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class DisplayMessageActivity extends AppCompatActivity {

    @Inject
    private BookmarkRepository bookmarkRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    public void save(View view) {
        SQLiteDatabase writable = bookmarkRepository.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Bookmark.TITLE, ((EditText) findViewById(R.id.titleText)).getEditableText().toString());
        values.put(Bookmark.LINK, ((EditText) findViewById(R.id.linkText)).getEditableText().toString());
        long rowId = writable.insert(Bookmark.TABLE_NAME, null, values);
        Log.d("app", "rowId: " + rowId);
        updateBookmarkList();
    }

    public void updateBookmarkList() {
        Cursor cursor = bookmarkRepository.getReadableDatabase().query(
                Bookmark.TABLE_NAME,
                new String[]{
                        Bookmark._ID,
                        Bookmark.TITLE,
                        Bookmark.LINK,
                        Bookmark.CREATED_ON},
                null,
                null,
                null,
                null,
                String.format("%s DESC", Bookmark.CREATED_ON)
        );
        List<Bookmark> bookmarks = new ArrayList<>();
        while (cursor.moveToNext()) {
            bookmarks.add(new Bookmark(
                    cursor.getLong(cursor.getColumnIndexOrThrow(Bookmark._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Bookmark.TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Bookmark.LINK)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Bookmark.CREATED_ON))
            ));
        }
        cursor.close();
        TextView bookmarksText = findViewById(R.id.bookmarksText);
        StringBuilder text = new StringBuilder();
        for (Bookmark it : bookmarks) {
            text.append(
                    String.format(
                            Locale.US,
                            "%1$-5d%2$-15s%3$-25s%4$s",
                            it.getId(), it.getTitle(), it.getLink(), it.getCreatedOn()))
                    .append("\n");
        }
        bookmarksText.setText(text);
    }

}

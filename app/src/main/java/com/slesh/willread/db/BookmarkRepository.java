package com.slesh.willread.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yauheni on 9/25/17.
 */

public final class BookmarkRepository extends SQLiteOpenHelper {

    public BookmarkRepository(Context context) {
        super(context, BookmarkContract.DATABASE_NAME, null, BookmarkContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookmarkContract.CREATE_SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BookmarkContract.DROP_SCHEMA);
        onCreate(db);
    }
}

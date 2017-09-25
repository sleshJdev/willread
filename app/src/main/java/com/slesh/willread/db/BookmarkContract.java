package com.slesh.willread.db;

import android.provider.BaseColumns;

/**
 * Created by yauheni on 9/25/17.
 */

public final class BookmarkContract {

    public static final String DATABASE_NAME = "will-read.bookmarkRepository";
    public static final byte DATABASE_VERSION = 1;
    public static final String CREATE_SCHEMA = String.format(
            "CREATE TABLE %s (_id INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT DEFAULT CURRENT_TIMESTAMP",
            Bookmark.TABLE_NAME, Bookmark.TITLE, Bookmark.LINK, Bookmark.CREATED_ON);
    public static final String DROP_SCHEMA = String.format("DROP TABLE IF EXISTS %s", Bookmark.TABLE_NAME);

    private BookmarkContract() {
        throw new IllegalStateException(BookmarkContract.class + " cannot be instantiated!");
    }

    public static class Bookmark implements BaseColumns {
        public static final String TABLE_NAME = "bookmark";
        public static final String TITLE = "title";
        public static final String LINK = "link";
        public static final String CREATED_ON = "created_on";

        private final long id;
        private final String title;
        private final String link;
        private final String createdOn;

        public Bookmark(long id, String title, String link, String createdOn) {
            this.id = id;
            this.title = title;
            this.link = link;
            this.createdOn = createdOn;
        }

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getCreatedOn() {
            return createdOn;
        }
    }


}

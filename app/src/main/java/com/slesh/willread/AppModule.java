package com.slesh.willread;

import android.content.Context;

import com.slesh.willread.db.BookmarkRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    public BookmarkRepository bookmarkRepository(Context context) {
        return new BookmarkRepository(context);
    }

}

package com.slesh.willread;


import android.content.Context;

import com.slesh.willread.db.BookmarkRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    BookmarkRepository bookmarkRepository(Context context);

}

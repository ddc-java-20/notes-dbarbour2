package edu.cnm.deepdive.notes.hilt;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.notes.model.dao.NoteDao;
import edu.cnm.deepdive.notes.service.NotesDatabase;
import edu.cnm.deepdive.notes.service.Preloader;
import javax.inject.Provider;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

  @Provides
  @Singleton
  NotesDatabase provideDatabase(@ApplicationContext Context context,Preloader callback) {
    return Room
        .databaseBuilder(context,
            NotesDatabase.class,NotesDatabase.getDatabaseName())
        .addCallback(callback)
        .build();
  }
  @Provides
  @Singleton
  NoteDao provideNoteDao(NotesDatabase database) {
    return database.getNoteDao();

  }

}

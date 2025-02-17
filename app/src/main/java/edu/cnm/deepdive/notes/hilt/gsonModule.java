package edu.cnm.deepdive.notes.hilt;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
class gsonModule {
  @Provides
  @Singleton
  Gson provideGson() {
    // TODO: 2/17/2025 Create a gson builder and invoke methods
    //  to configure it and build an instance of gson

  }

}

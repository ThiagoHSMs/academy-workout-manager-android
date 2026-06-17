package com.gymtracker.di;

import com.gymtracker.data.local.dao.TreinoDao;
import com.gymtracker.data.local.database.GymDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class DatabaseModule_ProvideTreinoDaoFactory implements Factory<TreinoDao> {
  private final Provider<GymDatabase> dbProvider;

  public DatabaseModule_ProvideTreinoDaoFactory(Provider<GymDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TreinoDao get() {
    return provideTreinoDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTreinoDaoFactory create(Provider<GymDatabase> dbProvider) {
    return new DatabaseModule_ProvideTreinoDaoFactory(dbProvider);
  }

  public static TreinoDao provideTreinoDao(GymDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTreinoDao(db));
  }
}

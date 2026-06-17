package com.gymtracker.di;

import com.gymtracker.data.local.dao.HistoricoDao;
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
public final class DatabaseModule_ProvideHistoricoDaoFactory implements Factory<HistoricoDao> {
  private final Provider<GymDatabase> dbProvider;

  public DatabaseModule_ProvideHistoricoDaoFactory(Provider<GymDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public HistoricoDao get() {
    return provideHistoricoDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideHistoricoDaoFactory create(Provider<GymDatabase> dbProvider) {
    return new DatabaseModule_ProvideHistoricoDaoFactory(dbProvider);
  }

  public static HistoricoDao provideHistoricoDao(GymDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHistoricoDao(db));
  }
}

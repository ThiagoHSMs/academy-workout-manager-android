package com.gymtracker.di;

import com.gymtracker.data.local.dao.ExercicioDao;
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
public final class DatabaseModule_ProvideExercicioDaoFactory implements Factory<ExercicioDao> {
  private final Provider<GymDatabase> dbProvider;

  public DatabaseModule_ProvideExercicioDaoFactory(Provider<GymDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExercicioDao get() {
    return provideExercicioDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExercicioDaoFactory create(Provider<GymDatabase> dbProvider) {
    return new DatabaseModule_ProvideExercicioDaoFactory(dbProvider);
  }

  public static ExercicioDao provideExercicioDao(GymDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExercicioDao(db));
  }
}

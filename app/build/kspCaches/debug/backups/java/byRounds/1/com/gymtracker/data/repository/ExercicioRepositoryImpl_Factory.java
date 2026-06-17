package com.gymtracker.data.repository;

import com.gymtracker.data.local.dao.ExercicioDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ExercicioRepositoryImpl_Factory implements Factory<ExercicioRepositoryImpl> {
  private final Provider<ExercicioDao> exercicioDaoProvider;

  public ExercicioRepositoryImpl_Factory(Provider<ExercicioDao> exercicioDaoProvider) {
    this.exercicioDaoProvider = exercicioDaoProvider;
  }

  @Override
  public ExercicioRepositoryImpl get() {
    return newInstance(exercicioDaoProvider.get());
  }

  public static ExercicioRepositoryImpl_Factory create(
      Provider<ExercicioDao> exercicioDaoProvider) {
    return new ExercicioRepositoryImpl_Factory(exercicioDaoProvider);
  }

  public static ExercicioRepositoryImpl newInstance(ExercicioDao exercicioDao) {
    return new ExercicioRepositoryImpl(exercicioDao);
  }
}

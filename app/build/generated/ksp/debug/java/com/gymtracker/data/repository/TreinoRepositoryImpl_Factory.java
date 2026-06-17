package com.gymtracker.data.repository;

import com.gymtracker.data.local.dao.ExercicioDao;
import com.gymtracker.data.local.dao.TreinoDao;
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
public final class TreinoRepositoryImpl_Factory implements Factory<TreinoRepositoryImpl> {
  private final Provider<TreinoDao> treinoDaoProvider;

  private final Provider<ExercicioDao> exercicioDaoProvider;

  public TreinoRepositoryImpl_Factory(Provider<TreinoDao> treinoDaoProvider,
      Provider<ExercicioDao> exercicioDaoProvider) {
    this.treinoDaoProvider = treinoDaoProvider;
    this.exercicioDaoProvider = exercicioDaoProvider;
  }

  @Override
  public TreinoRepositoryImpl get() {
    return newInstance(treinoDaoProvider.get(), exercicioDaoProvider.get());
  }

  public static TreinoRepositoryImpl_Factory create(Provider<TreinoDao> treinoDaoProvider,
      Provider<ExercicioDao> exercicioDaoProvider) {
    return new TreinoRepositoryImpl_Factory(treinoDaoProvider, exercicioDaoProvider);
  }

  public static TreinoRepositoryImpl newInstance(TreinoDao treinoDao, ExercicioDao exercicioDao) {
    return new TreinoRepositoryImpl(treinoDao, exercicioDao);
  }
}

package com.gymtracker.presentation.evolucao;

import com.gymtracker.domain.repository.ExercicioRepository;
import com.gymtracker.domain.repository.HistoricoRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class EvolucaoViewModel_Factory implements Factory<EvolucaoViewModel> {
  private final Provider<HistoricoRepository> historicoRepositoryProvider;

  private final Provider<ExercicioRepository> exercicioRepositoryProvider;

  public EvolucaoViewModel_Factory(Provider<HistoricoRepository> historicoRepositoryProvider,
      Provider<ExercicioRepository> exercicioRepositoryProvider) {
    this.historicoRepositoryProvider = historicoRepositoryProvider;
    this.exercicioRepositoryProvider = exercicioRepositoryProvider;
  }

  @Override
  public EvolucaoViewModel get() {
    return newInstance(historicoRepositoryProvider.get(), exercicioRepositoryProvider.get());
  }

  public static EvolucaoViewModel_Factory create(
      Provider<HistoricoRepository> historicoRepositoryProvider,
      Provider<ExercicioRepository> exercicioRepositoryProvider) {
    return new EvolucaoViewModel_Factory(historicoRepositoryProvider, exercicioRepositoryProvider);
  }

  public static EvolucaoViewModel newInstance(HistoricoRepository historicoRepository,
      ExercicioRepository exercicioRepository) {
    return new EvolucaoViewModel(historicoRepository, exercicioRepository);
  }
}

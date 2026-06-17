package com.gymtracker.domain.usecase;

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
public final class SalvarTreinoRealizadoUseCase_Factory implements Factory<SalvarTreinoRealizadoUseCase> {
  private final Provider<HistoricoRepository> repositoryProvider;

  public SalvarTreinoRealizadoUseCase_Factory(Provider<HistoricoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SalvarTreinoRealizadoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SalvarTreinoRealizadoUseCase_Factory create(
      Provider<HistoricoRepository> repositoryProvider) {
    return new SalvarTreinoRealizadoUseCase_Factory(repositoryProvider);
  }

  public static SalvarTreinoRealizadoUseCase newInstance(HistoricoRepository repository) {
    return new SalvarTreinoRealizadoUseCase(repository);
  }
}

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
public final class GetEvolucaoUseCase_Factory implements Factory<GetEvolucaoUseCase> {
  private final Provider<HistoricoRepository> repositoryProvider;

  public GetEvolucaoUseCase_Factory(Provider<HistoricoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetEvolucaoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetEvolucaoUseCase_Factory create(
      Provider<HistoricoRepository> repositoryProvider) {
    return new GetEvolucaoUseCase_Factory(repositoryProvider);
  }

  public static GetEvolucaoUseCase newInstance(HistoricoRepository repository) {
    return new GetEvolucaoUseCase(repository);
  }
}

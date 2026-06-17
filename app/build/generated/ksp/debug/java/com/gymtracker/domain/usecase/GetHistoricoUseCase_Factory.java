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
public final class GetHistoricoUseCase_Factory implements Factory<GetHistoricoUseCase> {
  private final Provider<HistoricoRepository> repositoryProvider;

  public GetHistoricoUseCase_Factory(Provider<HistoricoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetHistoricoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetHistoricoUseCase_Factory create(
      Provider<HistoricoRepository> repositoryProvider) {
    return new GetHistoricoUseCase_Factory(repositoryProvider);
  }

  public static GetHistoricoUseCase newInstance(HistoricoRepository repository) {
    return new GetHistoricoUseCase(repository);
  }
}

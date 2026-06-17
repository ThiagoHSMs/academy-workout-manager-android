package com.gymtracker.domain.usecase;

import com.gymtracker.domain.repository.TreinoRepository;
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
public final class GetProximoTreinoUseCase_Factory implements Factory<GetProximoTreinoUseCase> {
  private final Provider<TreinoRepository> repositoryProvider;

  public GetProximoTreinoUseCase_Factory(Provider<TreinoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProximoTreinoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProximoTreinoUseCase_Factory create(
      Provider<TreinoRepository> repositoryProvider) {
    return new GetProximoTreinoUseCase_Factory(repositoryProvider);
  }

  public static GetProximoTreinoUseCase newInstance(TreinoRepository repository) {
    return new GetProximoTreinoUseCase(repository);
  }
}

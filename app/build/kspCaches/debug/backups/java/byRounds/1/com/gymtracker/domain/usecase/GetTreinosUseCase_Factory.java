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
public final class GetTreinosUseCase_Factory implements Factory<GetTreinosUseCase> {
  private final Provider<TreinoRepository> repositoryProvider;

  public GetTreinosUseCase_Factory(Provider<TreinoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetTreinosUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetTreinosUseCase_Factory create(Provider<TreinoRepository> repositoryProvider) {
    return new GetTreinosUseCase_Factory(repositoryProvider);
  }

  public static GetTreinosUseCase newInstance(TreinoRepository repository) {
    return new GetTreinosUseCase(repository);
  }
}

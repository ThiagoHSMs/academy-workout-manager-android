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
public final class DuplicarTreinoUseCase_Factory implements Factory<DuplicarTreinoUseCase> {
  private final Provider<TreinoRepository> repositoryProvider;

  public DuplicarTreinoUseCase_Factory(Provider<TreinoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DuplicarTreinoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DuplicarTreinoUseCase_Factory create(
      Provider<TreinoRepository> repositoryProvider) {
    return new DuplicarTreinoUseCase_Factory(repositoryProvider);
  }

  public static DuplicarTreinoUseCase newInstance(TreinoRepository repository) {
    return new DuplicarTreinoUseCase(repository);
  }
}

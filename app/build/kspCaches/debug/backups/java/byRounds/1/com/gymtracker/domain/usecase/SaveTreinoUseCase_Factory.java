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
public final class SaveTreinoUseCase_Factory implements Factory<SaveTreinoUseCase> {
  private final Provider<TreinoRepository> repositoryProvider;

  public SaveTreinoUseCase_Factory(Provider<TreinoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveTreinoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveTreinoUseCase_Factory create(Provider<TreinoRepository> repositoryProvider) {
    return new SaveTreinoUseCase_Factory(repositoryProvider);
  }

  public static SaveTreinoUseCase newInstance(TreinoRepository repository) {
    return new SaveTreinoUseCase(repository);
  }
}

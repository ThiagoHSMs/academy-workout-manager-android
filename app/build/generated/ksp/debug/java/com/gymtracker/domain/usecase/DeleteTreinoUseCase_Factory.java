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
public final class DeleteTreinoUseCase_Factory implements Factory<DeleteTreinoUseCase> {
  private final Provider<TreinoRepository> repositoryProvider;

  public DeleteTreinoUseCase_Factory(Provider<TreinoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteTreinoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteTreinoUseCase_Factory create(Provider<TreinoRepository> repositoryProvider) {
    return new DeleteTreinoUseCase_Factory(repositoryProvider);
  }

  public static DeleteTreinoUseCase newInstance(TreinoRepository repository) {
    return new DeleteTreinoUseCase(repository);
  }
}

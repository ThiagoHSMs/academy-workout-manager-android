package com.gymtracker.domain.usecase;

import com.gymtracker.domain.repository.ExercicioRepository;
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
public final class DeleteExercicioUseCase_Factory implements Factory<DeleteExercicioUseCase> {
  private final Provider<ExercicioRepository> repositoryProvider;

  public DeleteExercicioUseCase_Factory(Provider<ExercicioRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteExercicioUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteExercicioUseCase_Factory create(
      Provider<ExercicioRepository> repositoryProvider) {
    return new DeleteExercicioUseCase_Factory(repositoryProvider);
  }

  public static DeleteExercicioUseCase newInstance(ExercicioRepository repository) {
    return new DeleteExercicioUseCase(repository);
  }
}

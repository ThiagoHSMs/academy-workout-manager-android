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
public final class SaveExercicioUseCase_Factory implements Factory<SaveExercicioUseCase> {
  private final Provider<ExercicioRepository> repositoryProvider;

  public SaveExercicioUseCase_Factory(Provider<ExercicioRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveExercicioUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveExercicioUseCase_Factory create(
      Provider<ExercicioRepository> repositoryProvider) {
    return new SaveExercicioUseCase_Factory(repositoryProvider);
  }

  public static SaveExercicioUseCase newInstance(ExercicioRepository repository) {
    return new SaveExercicioUseCase(repository);
  }
}

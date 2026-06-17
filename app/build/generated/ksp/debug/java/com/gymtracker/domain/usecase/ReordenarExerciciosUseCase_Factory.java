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
public final class ReordenarExerciciosUseCase_Factory implements Factory<ReordenarExerciciosUseCase> {
  private final Provider<ExercicioRepository> repositoryProvider;

  public ReordenarExerciciosUseCase_Factory(Provider<ExercicioRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ReordenarExerciciosUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ReordenarExerciciosUseCase_Factory create(
      Provider<ExercicioRepository> repositoryProvider) {
    return new ReordenarExerciciosUseCase_Factory(repositoryProvider);
  }

  public static ReordenarExerciciosUseCase newInstance(ExercicioRepository repository) {
    return new ReordenarExerciciosUseCase(repository);
  }
}

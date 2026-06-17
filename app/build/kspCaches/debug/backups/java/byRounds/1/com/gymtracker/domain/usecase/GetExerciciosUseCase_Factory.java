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
public final class GetExerciciosUseCase_Factory implements Factory<GetExerciciosUseCase> {
  private final Provider<ExercicioRepository> repositoryProvider;

  public GetExerciciosUseCase_Factory(Provider<ExercicioRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetExerciciosUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetExerciciosUseCase_Factory create(
      Provider<ExercicioRepository> repositoryProvider) {
    return new GetExerciciosUseCase_Factory(repositoryProvider);
  }

  public static GetExerciciosUseCase newInstance(ExercicioRepository repository) {
    return new GetExerciciosUseCase(repository);
  }
}

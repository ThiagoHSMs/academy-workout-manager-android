package com.gymtracker.presentation.exercicios;

import com.gymtracker.domain.usecase.GetExerciciosUseCase;
import com.gymtracker.domain.usecase.SaveExercicioUseCase;
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
public final class ExercicioFormViewModel_Factory implements Factory<ExercicioFormViewModel> {
  private final Provider<SaveExercicioUseCase> saveExercicioProvider;

  private final Provider<GetExerciciosUseCase> getExerciciosProvider;

  public ExercicioFormViewModel_Factory(Provider<SaveExercicioUseCase> saveExercicioProvider,
      Provider<GetExerciciosUseCase> getExerciciosProvider) {
    this.saveExercicioProvider = saveExercicioProvider;
    this.getExerciciosProvider = getExerciciosProvider;
  }

  @Override
  public ExercicioFormViewModel get() {
    return newInstance(saveExercicioProvider.get(), getExerciciosProvider.get());
  }

  public static ExercicioFormViewModel_Factory create(
      Provider<SaveExercicioUseCase> saveExercicioProvider,
      Provider<GetExerciciosUseCase> getExerciciosProvider) {
    return new ExercicioFormViewModel_Factory(saveExercicioProvider, getExerciciosProvider);
  }

  public static ExercicioFormViewModel newInstance(SaveExercicioUseCase saveExercicio,
      GetExerciciosUseCase getExercicios) {
    return new ExercicioFormViewModel(saveExercicio, getExercicios);
  }
}

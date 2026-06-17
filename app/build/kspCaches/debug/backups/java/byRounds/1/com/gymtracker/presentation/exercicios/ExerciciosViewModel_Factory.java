package com.gymtracker.presentation.exercicios;

import com.gymtracker.domain.usecase.DeleteExercicioUseCase;
import com.gymtracker.domain.usecase.GetExerciciosUseCase;
import com.gymtracker.domain.usecase.ReordenarExerciciosUseCase;
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
public final class ExerciciosViewModel_Factory implements Factory<ExerciciosViewModel> {
  private final Provider<GetExerciciosUseCase> getExerciciosProvider;

  private final Provider<DeleteExercicioUseCase> deleteExercicioProvider;

  private final Provider<ReordenarExerciciosUseCase> reordenarProvider;

  public ExerciciosViewModel_Factory(Provider<GetExerciciosUseCase> getExerciciosProvider,
      Provider<DeleteExercicioUseCase> deleteExercicioProvider,
      Provider<ReordenarExerciciosUseCase> reordenarProvider) {
    this.getExerciciosProvider = getExerciciosProvider;
    this.deleteExercicioProvider = deleteExercicioProvider;
    this.reordenarProvider = reordenarProvider;
  }

  @Override
  public ExerciciosViewModel get() {
    return newInstance(getExerciciosProvider.get(), deleteExercicioProvider.get(), reordenarProvider.get());
  }

  public static ExerciciosViewModel_Factory create(
      Provider<GetExerciciosUseCase> getExerciciosProvider,
      Provider<DeleteExercicioUseCase> deleteExercicioProvider,
      Provider<ReordenarExerciciosUseCase> reordenarProvider) {
    return new ExerciciosViewModel_Factory(getExerciciosProvider, deleteExercicioProvider, reordenarProvider);
  }

  public static ExerciciosViewModel newInstance(GetExerciciosUseCase getExercicios,
      DeleteExercicioUseCase deleteExercicio, ReordenarExerciciosUseCase reordenar) {
    return new ExerciciosViewModel(getExercicios, deleteExercicio, reordenar);
  }
}

package com.gymtracker.presentation.treinos;

import com.gymtracker.domain.usecase.GetTreinoByIdUseCase;
import com.gymtracker.domain.usecase.SaveTreinoUseCase;
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
public final class TreinoFormViewModel_Factory implements Factory<TreinoFormViewModel> {
  private final Provider<GetTreinoByIdUseCase> getTreinoByIdProvider;

  private final Provider<SaveTreinoUseCase> saveTreinoProvider;

  public TreinoFormViewModel_Factory(Provider<GetTreinoByIdUseCase> getTreinoByIdProvider,
      Provider<SaveTreinoUseCase> saveTreinoProvider) {
    this.getTreinoByIdProvider = getTreinoByIdProvider;
    this.saveTreinoProvider = saveTreinoProvider;
  }

  @Override
  public TreinoFormViewModel get() {
    return newInstance(getTreinoByIdProvider.get(), saveTreinoProvider.get());
  }

  public static TreinoFormViewModel_Factory create(
      Provider<GetTreinoByIdUseCase> getTreinoByIdProvider,
      Provider<SaveTreinoUseCase> saveTreinoProvider) {
    return new TreinoFormViewModel_Factory(getTreinoByIdProvider, saveTreinoProvider);
  }

  public static TreinoFormViewModel newInstance(GetTreinoByIdUseCase getTreinoById,
      SaveTreinoUseCase saveTreino) {
    return new TreinoFormViewModel(getTreinoById, saveTreino);
  }
}

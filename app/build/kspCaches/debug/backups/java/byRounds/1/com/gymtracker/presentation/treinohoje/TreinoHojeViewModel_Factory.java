package com.gymtracker.presentation.treinohoje;

import com.gymtracker.domain.usecase.GetTreinoHojeUseCase;
import com.gymtracker.domain.usecase.SalvarTreinoRealizadoUseCase;
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
public final class TreinoHojeViewModel_Factory implements Factory<TreinoHojeViewModel> {
  private final Provider<GetTreinoHojeUseCase> getTreinoHojeProvider;

  private final Provider<SalvarTreinoRealizadoUseCase> salvarTreinoProvider;

  public TreinoHojeViewModel_Factory(Provider<GetTreinoHojeUseCase> getTreinoHojeProvider,
      Provider<SalvarTreinoRealizadoUseCase> salvarTreinoProvider) {
    this.getTreinoHojeProvider = getTreinoHojeProvider;
    this.salvarTreinoProvider = salvarTreinoProvider;
  }

  @Override
  public TreinoHojeViewModel get() {
    return newInstance(getTreinoHojeProvider.get(), salvarTreinoProvider.get());
  }

  public static TreinoHojeViewModel_Factory create(
      Provider<GetTreinoHojeUseCase> getTreinoHojeProvider,
      Provider<SalvarTreinoRealizadoUseCase> salvarTreinoProvider) {
    return new TreinoHojeViewModel_Factory(getTreinoHojeProvider, salvarTreinoProvider);
  }

  public static TreinoHojeViewModel newInstance(GetTreinoHojeUseCase getTreinoHoje,
      SalvarTreinoRealizadoUseCase salvarTreino) {
    return new TreinoHojeViewModel(getTreinoHoje, salvarTreino);
  }
}

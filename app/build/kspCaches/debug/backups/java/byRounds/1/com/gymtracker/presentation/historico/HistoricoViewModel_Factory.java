package com.gymtracker.presentation.historico;

import com.gymtracker.domain.usecase.GetHistoricoUseCase;
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
public final class HistoricoViewModel_Factory implements Factory<HistoricoViewModel> {
  private final Provider<GetHistoricoUseCase> getHistoricoProvider;

  public HistoricoViewModel_Factory(Provider<GetHistoricoUseCase> getHistoricoProvider) {
    this.getHistoricoProvider = getHistoricoProvider;
  }

  @Override
  public HistoricoViewModel get() {
    return newInstance(getHistoricoProvider.get());
  }

  public static HistoricoViewModel_Factory create(
      Provider<GetHistoricoUseCase> getHistoricoProvider) {
    return new HistoricoViewModel_Factory(getHistoricoProvider);
  }

  public static HistoricoViewModel newInstance(GetHistoricoUseCase getHistorico) {
    return new HistoricoViewModel(getHistorico);
  }
}

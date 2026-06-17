package com.gymtracker.presentation.calendario;

import com.gymtracker.domain.repository.HistoricoRepository;
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
public final class CalendarioViewModel_Factory implements Factory<CalendarioViewModel> {
  private final Provider<HistoricoRepository> historicoRepositoryProvider;

  public CalendarioViewModel_Factory(Provider<HistoricoRepository> historicoRepositoryProvider) {
    this.historicoRepositoryProvider = historicoRepositoryProvider;
  }

  @Override
  public CalendarioViewModel get() {
    return newInstance(historicoRepositoryProvider.get());
  }

  public static CalendarioViewModel_Factory create(
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    return new CalendarioViewModel_Factory(historicoRepositoryProvider);
  }

  public static CalendarioViewModel newInstance(HistoricoRepository historicoRepository) {
    return new CalendarioViewModel(historicoRepository);
  }
}

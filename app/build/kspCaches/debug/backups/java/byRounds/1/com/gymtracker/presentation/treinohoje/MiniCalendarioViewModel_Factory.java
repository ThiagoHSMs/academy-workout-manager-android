package com.gymtracker.presentation.treinohoje;

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
public final class MiniCalendarioViewModel_Factory implements Factory<MiniCalendarioViewModel> {
  private final Provider<HistoricoRepository> historicoRepositoryProvider;

  public MiniCalendarioViewModel_Factory(
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    this.historicoRepositoryProvider = historicoRepositoryProvider;
  }

  @Override
  public MiniCalendarioViewModel get() {
    return newInstance(historicoRepositoryProvider.get());
  }

  public static MiniCalendarioViewModel_Factory create(
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    return new MiniCalendarioViewModel_Factory(historicoRepositoryProvider);
  }

  public static MiniCalendarioViewModel newInstance(HistoricoRepository historicoRepository) {
    return new MiniCalendarioViewModel(historicoRepository);
  }
}

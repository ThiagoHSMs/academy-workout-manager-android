package com.gymtracker.presentation.historico;

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
public final class HistoricoDetalheViewModel_Factory implements Factory<HistoricoDetalheViewModel> {
  private final Provider<HistoricoRepository> historicoRepositoryProvider;

  public HistoricoDetalheViewModel_Factory(
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    this.historicoRepositoryProvider = historicoRepositoryProvider;
  }

  @Override
  public HistoricoDetalheViewModel get() {
    return newInstance(historicoRepositoryProvider.get());
  }

  public static HistoricoDetalheViewModel_Factory create(
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    return new HistoricoDetalheViewModel_Factory(historicoRepositoryProvider);
  }

  public static HistoricoDetalheViewModel newInstance(HistoricoRepository historicoRepository) {
    return new HistoricoDetalheViewModel(historicoRepository);
  }
}

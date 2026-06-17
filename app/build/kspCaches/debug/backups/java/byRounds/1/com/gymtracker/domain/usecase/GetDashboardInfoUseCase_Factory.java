package com.gymtracker.domain.usecase;

import com.gymtracker.domain.repository.HistoricoRepository;
import com.gymtracker.domain.repository.TreinoRepository;
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
public final class GetDashboardInfoUseCase_Factory implements Factory<GetDashboardInfoUseCase> {
  private final Provider<TreinoRepository> treinoRepositoryProvider;

  private final Provider<HistoricoRepository> historicoRepositoryProvider;

  public GetDashboardInfoUseCase_Factory(Provider<TreinoRepository> treinoRepositoryProvider,
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    this.treinoRepositoryProvider = treinoRepositoryProvider;
    this.historicoRepositoryProvider = historicoRepositoryProvider;
  }

  @Override
  public GetDashboardInfoUseCase get() {
    return newInstance(treinoRepositoryProvider.get(), historicoRepositoryProvider.get());
  }

  public static GetDashboardInfoUseCase_Factory create(
      Provider<TreinoRepository> treinoRepositoryProvider,
      Provider<HistoricoRepository> historicoRepositoryProvider) {
    return new GetDashboardInfoUseCase_Factory(treinoRepositoryProvider, historicoRepositoryProvider);
  }

  public static GetDashboardInfoUseCase newInstance(TreinoRepository treinoRepository,
      HistoricoRepository historicoRepository) {
    return new GetDashboardInfoUseCase(treinoRepository, historicoRepository);
  }
}

package com.gymtracker.data.repository;

import com.gymtracker.data.local.dao.HistoricoDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HistoricoRepositoryImpl_Factory implements Factory<HistoricoRepositoryImpl> {
  private final Provider<HistoricoDao> historicoDaoProvider;

  public HistoricoRepositoryImpl_Factory(Provider<HistoricoDao> historicoDaoProvider) {
    this.historicoDaoProvider = historicoDaoProvider;
  }

  @Override
  public HistoricoRepositoryImpl get() {
    return newInstance(historicoDaoProvider.get());
  }

  public static HistoricoRepositoryImpl_Factory create(
      Provider<HistoricoDao> historicoDaoProvider) {
    return new HistoricoRepositoryImpl_Factory(historicoDaoProvider);
  }

  public static HistoricoRepositoryImpl newInstance(HistoricoDao historicoDao) {
    return new HistoricoRepositoryImpl(historicoDao);
  }
}

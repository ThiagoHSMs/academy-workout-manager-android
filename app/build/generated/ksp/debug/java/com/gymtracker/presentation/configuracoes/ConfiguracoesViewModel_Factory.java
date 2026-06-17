package com.gymtracker.presentation.configuracoes;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ConfiguracoesViewModel_Factory implements Factory<ConfiguracoesViewModel> {
  private final Provider<Context> contextProvider;

  public ConfiguracoesViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConfiguracoesViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static ConfiguracoesViewModel_Factory create(Provider<Context> contextProvider) {
    return new ConfiguracoesViewModel_Factory(contextProvider);
  }

  public static ConfiguracoesViewModel newInstance(Context context) {
    return new ConfiguracoesViewModel(context);
  }
}

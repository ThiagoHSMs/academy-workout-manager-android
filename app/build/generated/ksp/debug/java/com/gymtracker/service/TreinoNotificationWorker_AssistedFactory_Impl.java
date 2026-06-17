package com.gymtracker.service;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class TreinoNotificationWorker_AssistedFactory_Impl implements TreinoNotificationWorker_AssistedFactory {
  private final TreinoNotificationWorker_Factory delegateFactory;

  TreinoNotificationWorker_AssistedFactory_Impl(TreinoNotificationWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public TreinoNotificationWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<TreinoNotificationWorker_AssistedFactory> create(
      TreinoNotificationWorker_Factory delegateFactory) {
    return InstanceFactory.create(new TreinoNotificationWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<TreinoNotificationWorker_AssistedFactory> createFactoryProvider(
      TreinoNotificationWorker_Factory delegateFactory) {
    return InstanceFactory.create(new TreinoNotificationWorker_AssistedFactory_Impl(delegateFactory));
  }
}

package com.gymtracker.service;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.gymtracker.domain.repository.TreinoRepository;
import dagger.internal.DaggerGenerated;
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
public final class TreinoNotificationWorker_Factory {
  private final Provider<TreinoRepository> treinoRepositoryProvider;

  public TreinoNotificationWorker_Factory(Provider<TreinoRepository> treinoRepositoryProvider) {
    this.treinoRepositoryProvider = treinoRepositoryProvider;
  }

  public TreinoNotificationWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, treinoRepositoryProvider.get());
  }

  public static TreinoNotificationWorker_Factory create(
      Provider<TreinoRepository> treinoRepositoryProvider) {
    return new TreinoNotificationWorker_Factory(treinoRepositoryProvider);
  }

  public static TreinoNotificationWorker newInstance(Context appContext,
      WorkerParameters workerParams, TreinoRepository treinoRepository) {
    return new TreinoNotificationWorker(appContext, workerParams, treinoRepository);
  }
}

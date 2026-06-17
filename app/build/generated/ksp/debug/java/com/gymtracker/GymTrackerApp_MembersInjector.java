package com.gymtracker;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class GymTrackerApp_MembersInjector implements MembersInjector<GymTrackerApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public GymTrackerApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<GymTrackerApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new GymTrackerApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(GymTrackerApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.gymtracker.GymTrackerApp.workerFactory")
  public static void injectWorkerFactory(GymTrackerApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}

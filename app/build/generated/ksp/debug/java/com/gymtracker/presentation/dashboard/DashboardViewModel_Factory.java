package com.gymtracker.presentation.dashboard;

import com.gymtracker.domain.usecase.GetDashboardInfoUseCase;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<GetDashboardInfoUseCase> getDashboardInfoProvider;

  public DashboardViewModel_Factory(Provider<GetDashboardInfoUseCase> getDashboardInfoProvider) {
    this.getDashboardInfoProvider = getDashboardInfoProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(getDashboardInfoProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<GetDashboardInfoUseCase> getDashboardInfoProvider) {
    return new DashboardViewModel_Factory(getDashboardInfoProvider);
  }

  public static DashboardViewModel newInstance(GetDashboardInfoUseCase getDashboardInfo) {
    return new DashboardViewModel(getDashboardInfo);
  }
}

package com.gymtracker.presentation.treinos;

import com.gymtracker.domain.usecase.DeleteTreinoUseCase;
import com.gymtracker.domain.usecase.DuplicarTreinoUseCase;
import com.gymtracker.domain.usecase.GetTreinosUseCase;
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
public final class TreinosViewModel_Factory implements Factory<TreinosViewModel> {
  private final Provider<GetTreinosUseCase> getTreinosProvider;

  private final Provider<DeleteTreinoUseCase> deleteTreinoProvider;

  private final Provider<DuplicarTreinoUseCase> duplicarTreinoProvider;

  public TreinosViewModel_Factory(Provider<GetTreinosUseCase> getTreinosProvider,
      Provider<DeleteTreinoUseCase> deleteTreinoProvider,
      Provider<DuplicarTreinoUseCase> duplicarTreinoProvider) {
    this.getTreinosProvider = getTreinosProvider;
    this.deleteTreinoProvider = deleteTreinoProvider;
    this.duplicarTreinoProvider = duplicarTreinoProvider;
  }

  @Override
  public TreinosViewModel get() {
    return newInstance(getTreinosProvider.get(), deleteTreinoProvider.get(), duplicarTreinoProvider.get());
  }

  public static TreinosViewModel_Factory create(Provider<GetTreinosUseCase> getTreinosProvider,
      Provider<DeleteTreinoUseCase> deleteTreinoProvider,
      Provider<DuplicarTreinoUseCase> duplicarTreinoProvider) {
    return new TreinosViewModel_Factory(getTreinosProvider, deleteTreinoProvider, duplicarTreinoProvider);
  }

  public static TreinosViewModel newInstance(GetTreinosUseCase getTreinos,
      DeleteTreinoUseCase deleteTreino, DuplicarTreinoUseCase duplicarTreino) {
    return new TreinosViewModel(getTreinos, deleteTreino, duplicarTreino);
  }
}

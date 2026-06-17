package com.gymtracker.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.gymtracker.data.local.dao.ExercicioDao;
import com.gymtracker.data.local.dao.ExercicioDao_Impl;
import com.gymtracker.data.local.dao.HistoricoDao;
import com.gymtracker.data.local.dao.HistoricoDao_Impl;
import com.gymtracker.data.local.dao.TreinoDao;
import com.gymtracker.data.local.dao.TreinoDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GymDatabase_Impl extends GymDatabase {
  private volatile TreinoDao _treinoDao;

  private volatile ExercicioDao _exercicioDao;

  private volatile HistoricoDao _historicoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `treinos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `diaSemana` INTEGER NOT NULL, `grupoMuscular` TEXT NOT NULL, `observacoes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercicios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `treinoId` INTEGER NOT NULL, `nome` TEXT NOT NULL, `series` INTEGER NOT NULL, `repeticoes` INTEGER NOT NULL, `carga` REAL NOT NULL, `descanso` INTEGER NOT NULL, `observacoes` TEXT NOT NULL, `ordem` INTEGER NOT NULL, FOREIGN KEY(`treinoId`) REFERENCES `treinos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercicios_treinoId` ON `exercicios` (`treinoId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `historico_treinos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `treinoId` INTEGER, `nomeTreino` TEXT NOT NULL, `dataExecucao` TEXT NOT NULL, `horaExecucao` TEXT NOT NULL, FOREIGN KEY(`treinoId`) REFERENCES `treinos`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_historico_treinos_treinoId` ON `historico_treinos` (`treinoId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `historico_exercicios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `historicoTreinoId` INTEGER NOT NULL, `exercicioId` INTEGER, `nomeExercicio` TEXT NOT NULL, `cargaUtilizada` REAL NOT NULL, `seriesRealizadas` INTEGER NOT NULL, `repeticoesRealizadas` INTEGER NOT NULL, FOREIGN KEY(`historicoTreinoId`) REFERENCES `historico_treinos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`exercicioId`) REFERENCES `exercicios`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_historico_exercicios_historicoTreinoId` ON `historico_exercicios` (`historicoTreinoId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_historico_exercicios_exercicioId` ON `historico_exercicios` (`exercicioId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b0627a9a236ff76b536317dce60a2b65')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `treinos`");
        db.execSQL("DROP TABLE IF EXISTS `exercicios`");
        db.execSQL("DROP TABLE IF EXISTS `historico_treinos`");
        db.execSQL("DROP TABLE IF EXISTS `historico_exercicios`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTreinos = new HashMap<String, TableInfo.Column>(5);
        _columnsTreinos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("diaSemana", new TableInfo.Column("diaSemana", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("grupoMuscular", new TableInfo.Column("grupoMuscular", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTreinos.put("observacoes", new TableInfo.Column("observacoes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTreinos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTreinos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTreinos = new TableInfo("treinos", _columnsTreinos, _foreignKeysTreinos, _indicesTreinos);
        final TableInfo _existingTreinos = TableInfo.read(db, "treinos");
        if (!_infoTreinos.equals(_existingTreinos)) {
          return new RoomOpenHelper.ValidationResult(false, "treinos(com.gymtracker.data.local.entity.TreinoEntity).\n"
                  + " Expected:\n" + _infoTreinos + "\n"
                  + " Found:\n" + _existingTreinos);
        }
        final HashMap<String, TableInfo.Column> _columnsExercicios = new HashMap<String, TableInfo.Column>(9);
        _columnsExercicios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("treinoId", new TableInfo.Column("treinoId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("series", new TableInfo.Column("series", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("repeticoes", new TableInfo.Column("repeticoes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("carga", new TableInfo.Column("carga", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("descanso", new TableInfo.Column("descanso", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("observacoes", new TableInfo.Column("observacoes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercicios.put("ordem", new TableInfo.Column("ordem", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercicios = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExercicios.add(new TableInfo.ForeignKey("treinos", "CASCADE", "NO ACTION", Arrays.asList("treinoId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExercicios = new HashSet<TableInfo.Index>(1);
        _indicesExercicios.add(new TableInfo.Index("index_exercicios_treinoId", false, Arrays.asList("treinoId"), Arrays.asList("ASC")));
        final TableInfo _infoExercicios = new TableInfo("exercicios", _columnsExercicios, _foreignKeysExercicios, _indicesExercicios);
        final TableInfo _existingExercicios = TableInfo.read(db, "exercicios");
        if (!_infoExercicios.equals(_existingExercicios)) {
          return new RoomOpenHelper.ValidationResult(false, "exercicios(com.gymtracker.data.local.entity.ExercicioEntity).\n"
                  + " Expected:\n" + _infoExercicios + "\n"
                  + " Found:\n" + _existingExercicios);
        }
        final HashMap<String, TableInfo.Column> _columnsHistoricoTreinos = new HashMap<String, TableInfo.Column>(5);
        _columnsHistoricoTreinos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoTreinos.put("treinoId", new TableInfo.Column("treinoId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoTreinos.put("nomeTreino", new TableInfo.Column("nomeTreino", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoTreinos.put("dataExecucao", new TableInfo.Column("dataExecucao", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoTreinos.put("horaExecucao", new TableInfo.Column("horaExecucao", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistoricoTreinos = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysHistoricoTreinos.add(new TableInfo.ForeignKey("treinos", "SET NULL", "NO ACTION", Arrays.asList("treinoId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesHistoricoTreinos = new HashSet<TableInfo.Index>(1);
        _indicesHistoricoTreinos.add(new TableInfo.Index("index_historico_treinos_treinoId", false, Arrays.asList("treinoId"), Arrays.asList("ASC")));
        final TableInfo _infoHistoricoTreinos = new TableInfo("historico_treinos", _columnsHistoricoTreinos, _foreignKeysHistoricoTreinos, _indicesHistoricoTreinos);
        final TableInfo _existingHistoricoTreinos = TableInfo.read(db, "historico_treinos");
        if (!_infoHistoricoTreinos.equals(_existingHistoricoTreinos)) {
          return new RoomOpenHelper.ValidationResult(false, "historico_treinos(com.gymtracker.data.local.entity.HistoricoTreinoEntity).\n"
                  + " Expected:\n" + _infoHistoricoTreinos + "\n"
                  + " Found:\n" + _existingHistoricoTreinos);
        }
        final HashMap<String, TableInfo.Column> _columnsHistoricoExercicios = new HashMap<String, TableInfo.Column>(7);
        _columnsHistoricoExercicios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("historicoTreinoId", new TableInfo.Column("historicoTreinoId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("exercicioId", new TableInfo.Column("exercicioId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("nomeExercicio", new TableInfo.Column("nomeExercicio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("cargaUtilizada", new TableInfo.Column("cargaUtilizada", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("seriesRealizadas", new TableInfo.Column("seriesRealizadas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHistoricoExercicios.put("repeticoesRealizadas", new TableInfo.Column("repeticoesRealizadas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistoricoExercicios = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysHistoricoExercicios.add(new TableInfo.ForeignKey("historico_treinos", "CASCADE", "NO ACTION", Arrays.asList("historicoTreinoId"), Arrays.asList("id")));
        _foreignKeysHistoricoExercicios.add(new TableInfo.ForeignKey("exercicios", "SET NULL", "NO ACTION", Arrays.asList("exercicioId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesHistoricoExercicios = new HashSet<TableInfo.Index>(2);
        _indicesHistoricoExercicios.add(new TableInfo.Index("index_historico_exercicios_historicoTreinoId", false, Arrays.asList("historicoTreinoId"), Arrays.asList("ASC")));
        _indicesHistoricoExercicios.add(new TableInfo.Index("index_historico_exercicios_exercicioId", false, Arrays.asList("exercicioId"), Arrays.asList("ASC")));
        final TableInfo _infoHistoricoExercicios = new TableInfo("historico_exercicios", _columnsHistoricoExercicios, _foreignKeysHistoricoExercicios, _indicesHistoricoExercicios);
        final TableInfo _existingHistoricoExercicios = TableInfo.read(db, "historico_exercicios");
        if (!_infoHistoricoExercicios.equals(_existingHistoricoExercicios)) {
          return new RoomOpenHelper.ValidationResult(false, "historico_exercicios(com.gymtracker.data.local.entity.HistoricoExercicioEntity).\n"
                  + " Expected:\n" + _infoHistoricoExercicios + "\n"
                  + " Found:\n" + _existingHistoricoExercicios);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b0627a9a236ff76b536317dce60a2b65", "adfe8a137ab33bbeabbe85b013c25719");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "treinos","exercicios","historico_treinos","historico_exercicios");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `treinos`");
      _db.execSQL("DELETE FROM `exercicios`");
      _db.execSQL("DELETE FROM `historico_treinos`");
      _db.execSQL("DELETE FROM `historico_exercicios`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TreinoDao.class, TreinoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExercicioDao.class, ExercicioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HistoricoDao.class, HistoricoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TreinoDao treinoDao() {
    if (_treinoDao != null) {
      return _treinoDao;
    } else {
      synchronized(this) {
        if(_treinoDao == null) {
          _treinoDao = new TreinoDao_Impl(this);
        }
        return _treinoDao;
      }
    }
  }

  @Override
  public ExercicioDao exercicioDao() {
    if (_exercicioDao != null) {
      return _exercicioDao;
    } else {
      synchronized(this) {
        if(_exercicioDao == null) {
          _exercicioDao = new ExercicioDao_Impl(this);
        }
        return _exercicioDao;
      }
    }
  }

  @Override
  public HistoricoDao historicoDao() {
    if (_historicoDao != null) {
      return _historicoDao;
    } else {
      synchronized(this) {
        if(_historicoDao == null) {
          _historicoDao = new HistoricoDao_Impl(this);
        }
        return _historicoDao;
      }
    }
  }
}

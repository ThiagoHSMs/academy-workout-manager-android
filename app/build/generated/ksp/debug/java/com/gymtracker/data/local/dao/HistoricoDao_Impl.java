package com.gymtracker.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.gymtracker.data.local.entity.HistoricoExercicioEntity;
import com.gymtracker.data.local.entity.HistoricoTreinoEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoricoDao_Impl implements HistoricoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HistoricoTreinoEntity> __insertionAdapterOfHistoricoTreinoEntity;

  private final EntityInsertionAdapter<HistoricoExercicioEntity> __insertionAdapterOfHistoricoExercicioEntity;

  private final EntityDeletionOrUpdateAdapter<HistoricoTreinoEntity> __deletionAdapterOfHistoricoTreinoEntity;

  public HistoricoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistoricoTreinoEntity = new EntityInsertionAdapter<HistoricoTreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `historico_treinos` (`id`,`treinoId`,`nomeTreino`,`dataExecucao`,`horaExecucao`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoricoTreinoEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTreinoId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getTreinoId());
        }
        statement.bindString(3, entity.getNomeTreino());
        statement.bindString(4, entity.getDataExecucao());
        statement.bindString(5, entity.getHoraExecucao());
      }
    };
    this.__insertionAdapterOfHistoricoExercicioEntity = new EntityInsertionAdapter<HistoricoExercicioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `historico_exercicios` (`id`,`historicoTreinoId`,`exercicioId`,`nomeExercicio`,`cargaUtilizada`,`seriesRealizadas`,`repeticoesRealizadas`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoricoExercicioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getHistoricoTreinoId());
        if (entity.getExercicioId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getExercicioId());
        }
        statement.bindString(4, entity.getNomeExercicio());
        statement.bindDouble(5, entity.getCargaUtilizada());
        statement.bindLong(6, entity.getSeriesRealizadas());
        statement.bindLong(7, entity.getRepeticoesRealizadas());
      }
    };
    this.__deletionAdapterOfHistoricoTreinoEntity = new EntityDeletionOrUpdateAdapter<HistoricoTreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `historico_treinos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HistoricoTreinoEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertHistoricoTreino(final HistoricoTreinoEntity historico,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfHistoricoTreinoEntity.insertAndReturnId(historico);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertHistoricoExercicio(final HistoricoExercicioEntity exercicio,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfHistoricoExercicioEntity.insertAndReturnId(exercicio);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertHistoricoExercicios(final List<HistoricoExercicioEntity> exercicios,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistoricoExercicioEntity.insert(exercicios);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHistoricoTreino(final HistoricoTreinoEntity historico,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHistoricoTreinoEntity.handle(historico);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HistoricoTreinoEntity>> getAllHistorico() {
    final String _sql = "SELECT * FROM historico_treinos ORDER BY dataExecucao DESC, horaExecucao DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos"}, new Callable<List<HistoricoTreinoEntity>>() {
      @Override
      @NonNull
      public List<HistoricoTreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final List<HistoricoTreinoEntity> _result = new ArrayList<HistoricoTreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoTreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _item = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HistoricoTreinoEntity>> getHistoricoByPeriodo(final String inicio,
      final String fim) {
    final String _sql = "\n"
            + "        SELECT * FROM historico_treinos \n"
            + "        WHERE dataExecucao BETWEEN ? AND ? \n"
            + "        ORDER BY dataExecucao DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, inicio);
    _argIndex = 2;
    _statement.bindString(_argIndex, fim);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos"}, new Callable<List<HistoricoTreinoEntity>>() {
      @Override
      @NonNull
      public List<HistoricoTreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final List<HistoricoTreinoEntity> _result = new ArrayList<HistoricoTreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoTreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _item = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HistoricoTreinoEntity>> getHistoricoByTreino(final long treinoId) {
    final String _sql = "SELECT * FROM historico_treinos WHERE treinoId = ? ORDER BY dataExecucao DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, treinoId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos"}, new Callable<List<HistoricoTreinoEntity>>() {
      @Override
      @NonNull
      public List<HistoricoTreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final List<HistoricoTreinoEntity> _result = new ArrayList<HistoricoTreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoTreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _item = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getHistoricoById(final long id,
      final Continuation<? super HistoricoTreinoEntity> $completion) {
    final String _sql = "SELECT * FROM historico_treinos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HistoricoTreinoEntity>() {
      @Override
      @Nullable
      public HistoricoTreinoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final HistoricoTreinoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _result = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> countTreinosNoMes(final String anoMes) {
    final String _sql = "\n"
            + "        SELECT COUNT(*) FROM historico_treinos \n"
            + "        WHERE dataExecucao LIKE ? || '%'\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, anoMes);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getDatasDoMes(final String anoMes) {
    final String _sql = "\n"
            + "        SELECT dataExecucao FROM historico_treinos \n"
            + "        WHERE dataExecucao LIKE ? || '%'\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, anoMes);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUltimoTreino(final Continuation<? super HistoricoTreinoEntity> $completion) {
    final String _sql = "SELECT * FROM historico_treinos ORDER BY dataExecucao DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HistoricoTreinoEntity>() {
      @Override
      @Nullable
      public HistoricoTreinoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final HistoricoTreinoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _result = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HistoricoExercicioEntity>> getExerciciosDoHistorico(final long historicoId) {
    final String _sql = "SELECT * FROM historico_exercicios WHERE historicoTreinoId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, historicoId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_exercicios"}, new Callable<List<HistoricoExercicioEntity>>() {
      @Override
      @NonNull
      public List<HistoricoExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHistoricoTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "historicoTreinoId");
          final int _cursorIndexOfExercicioId = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioId");
          final int _cursorIndexOfNomeExercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeExercicio");
          final int _cursorIndexOfCargaUtilizada = CursorUtil.getColumnIndexOrThrow(_cursor, "cargaUtilizada");
          final int _cursorIndexOfSeriesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesRealizadas");
          final int _cursorIndexOfRepeticoesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoesRealizadas");
          final List<HistoricoExercicioEntity> _result = new ArrayList<HistoricoExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpHistoricoTreinoId;
            _tmpHistoricoTreinoId = _cursor.getLong(_cursorIndexOfHistoricoTreinoId);
            final Long _tmpExercicioId;
            if (_cursor.isNull(_cursorIndexOfExercicioId)) {
              _tmpExercicioId = null;
            } else {
              _tmpExercicioId = _cursor.getLong(_cursorIndexOfExercicioId);
            }
            final String _tmpNomeExercicio;
            _tmpNomeExercicio = _cursor.getString(_cursorIndexOfNomeExercicio);
            final double _tmpCargaUtilizada;
            _tmpCargaUtilizada = _cursor.getDouble(_cursorIndexOfCargaUtilizada);
            final int _tmpSeriesRealizadas;
            _tmpSeriesRealizadas = _cursor.getInt(_cursorIndexOfSeriesRealizadas);
            final int _tmpRepeticoesRealizadas;
            _tmpRepeticoesRealizadas = _cursor.getInt(_cursorIndexOfRepeticoesRealizadas);
            _item = new HistoricoExercicioEntity(_tmpId,_tmpHistoricoTreinoId,_tmpExercicioId,_tmpNomeExercicio,_tmpCargaUtilizada,_tmpSeriesRealizadas,_tmpRepeticoesRealizadas);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getExerciciosDoHistoricoSync(final long historicoId,
      final Continuation<? super List<HistoricoExercicioEntity>> $completion) {
    final String _sql = "SELECT * FROM historico_exercicios WHERE historicoTreinoId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, historicoId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HistoricoExercicioEntity>>() {
      @Override
      @NonNull
      public List<HistoricoExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHistoricoTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "historicoTreinoId");
          final int _cursorIndexOfExercicioId = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioId");
          final int _cursorIndexOfNomeExercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeExercicio");
          final int _cursorIndexOfCargaUtilizada = CursorUtil.getColumnIndexOrThrow(_cursor, "cargaUtilizada");
          final int _cursorIndexOfSeriesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesRealizadas");
          final int _cursorIndexOfRepeticoesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoesRealizadas");
          final List<HistoricoExercicioEntity> _result = new ArrayList<HistoricoExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpHistoricoTreinoId;
            _tmpHistoricoTreinoId = _cursor.getLong(_cursorIndexOfHistoricoTreinoId);
            final Long _tmpExercicioId;
            if (_cursor.isNull(_cursorIndexOfExercicioId)) {
              _tmpExercicioId = null;
            } else {
              _tmpExercicioId = _cursor.getLong(_cursorIndexOfExercicioId);
            }
            final String _tmpNomeExercicio;
            _tmpNomeExercicio = _cursor.getString(_cursorIndexOfNomeExercicio);
            final double _tmpCargaUtilizada;
            _tmpCargaUtilizada = _cursor.getDouble(_cursorIndexOfCargaUtilizada);
            final int _tmpSeriesRealizadas;
            _tmpSeriesRealizadas = _cursor.getInt(_cursorIndexOfSeriesRealizadas);
            final int _tmpRepeticoesRealizadas;
            _tmpRepeticoesRealizadas = _cursor.getInt(_cursorIndexOfRepeticoesRealizadas);
            _item = new HistoricoExercicioEntity(_tmpId,_tmpHistoricoTreinoId,_tmpExercicioId,_tmpNomeExercicio,_tmpCargaUtilizada,_tmpSeriesRealizadas,_tmpRepeticoesRealizadas);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HistoricoExercicioEntity>> getEvolucaoPorExercicio(final long exercicioId) {
    final String _sql = "\n"
            + "        SELECT he.* FROM historico_exercicios he\n"
            + "        INNER JOIN historico_treinos ht ON he.historicoTreinoId = ht.id\n"
            + "        WHERE he.exercicioId = ?\n"
            + "        ORDER BY ht.dataExecucao ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exercicioId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_exercicios",
        "historico_treinos"}, new Callable<List<HistoricoExercicioEntity>>() {
      @Override
      @NonNull
      public List<HistoricoExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHistoricoTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "historicoTreinoId");
          final int _cursorIndexOfExercicioId = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioId");
          final int _cursorIndexOfNomeExercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeExercicio");
          final int _cursorIndexOfCargaUtilizada = CursorUtil.getColumnIndexOrThrow(_cursor, "cargaUtilizada");
          final int _cursorIndexOfSeriesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesRealizadas");
          final int _cursorIndexOfRepeticoesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoesRealizadas");
          final List<HistoricoExercicioEntity> _result = new ArrayList<HistoricoExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpHistoricoTreinoId;
            _tmpHistoricoTreinoId = _cursor.getLong(_cursorIndexOfHistoricoTreinoId);
            final Long _tmpExercicioId;
            if (_cursor.isNull(_cursorIndexOfExercicioId)) {
              _tmpExercicioId = null;
            } else {
              _tmpExercicioId = _cursor.getLong(_cursorIndexOfExercicioId);
            }
            final String _tmpNomeExercicio;
            _tmpNomeExercicio = _cursor.getString(_cursorIndexOfNomeExercicio);
            final double _tmpCargaUtilizada;
            _tmpCargaUtilizada = _cursor.getDouble(_cursorIndexOfCargaUtilizada);
            final int _tmpSeriesRealizadas;
            _tmpSeriesRealizadas = _cursor.getInt(_cursorIndexOfSeriesRealizadas);
            final int _tmpRepeticoesRealizadas;
            _tmpRepeticoesRealizadas = _cursor.getInt(_cursorIndexOfRepeticoesRealizadas);
            _item = new HistoricoExercicioEntity(_tmpId,_tmpHistoricoTreinoId,_tmpExercicioId,_tmpNomeExercicio,_tmpCargaUtilizada,_tmpSeriesRealizadas,_tmpRepeticoesRealizadas);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HistoricoExercicioEntity>> getEvolucaoPorNomeExercicio(
      final String nomeExercicio) {
    final String _sql = "\n"
            + "        SELECT he.*, ht.dataExecucao FROM historico_exercicios he\n"
            + "        INNER JOIN historico_treinos ht ON he.historicoTreinoId = ht.id\n"
            + "        WHERE he.nomeExercicio LIKE '%' || ? || '%'\n"
            + "        ORDER BY ht.dataExecucao ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nomeExercicio);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_exercicios",
        "historico_treinos"}, new Callable<List<HistoricoExercicioEntity>>() {
      @Override
      @NonNull
      public List<HistoricoExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHistoricoTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "historicoTreinoId");
          final int _cursorIndexOfExercicioId = CursorUtil.getColumnIndexOrThrow(_cursor, "exercicioId");
          final int _cursorIndexOfNomeExercicio = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeExercicio");
          final int _cursorIndexOfCargaUtilizada = CursorUtil.getColumnIndexOrThrow(_cursor, "cargaUtilizada");
          final int _cursorIndexOfSeriesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesRealizadas");
          final int _cursorIndexOfRepeticoesRealizadas = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoesRealizadas");
          final List<HistoricoExercicioEntity> _result = new ArrayList<HistoricoExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpHistoricoTreinoId;
            _tmpHistoricoTreinoId = _cursor.getLong(_cursorIndexOfHistoricoTreinoId);
            final Long _tmpExercicioId;
            if (_cursor.isNull(_cursorIndexOfExercicioId)) {
              _tmpExercicioId = null;
            } else {
              _tmpExercicioId = _cursor.getLong(_cursorIndexOfExercicioId);
            }
            final String _tmpNomeExercicio;
            _tmpNomeExercicio = _cursor.getString(_cursorIndexOfNomeExercicio);
            final double _tmpCargaUtilizada;
            _tmpCargaUtilizada = _cursor.getDouble(_cursorIndexOfCargaUtilizada);
            final int _tmpSeriesRealizadas;
            _tmpSeriesRealizadas = _cursor.getInt(_cursorIndexOfSeriesRealizadas);
            final int _tmpRepeticoesRealizadas;
            _tmpRepeticoesRealizadas = _cursor.getInt(_cursorIndexOfRepeticoesRealizadas);
            _item = new HistoricoExercicioEntity(_tmpId,_tmpHistoricoTreinoId,_tmpExercicioId,_tmpNomeExercicio,_tmpCargaUtilizada,_tmpSeriesRealizadas,_tmpRepeticoesRealizadas);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HistoricoTreinoEntity>> searchHistoricoByExercicio(final String query) {
    final String _sql = "\n"
            + "        SELECT ht.* FROM historico_treinos ht\n"
            + "        INNER JOIN historico_exercicios he ON he.historicoTreinoId = ht.id\n"
            + "        WHERE he.nomeExercicio LIKE '%' || ? || '%'\n"
            + "        ORDER BY ht.dataExecucao DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"historico_treinos",
        "historico_exercicios"}, new Callable<List<HistoricoTreinoEntity>>() {
      @Override
      @NonNull
      public List<HistoricoTreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNomeTreino = CursorUtil.getColumnIndexOrThrow(_cursor, "nomeTreino");
          final int _cursorIndexOfDataExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "dataExecucao");
          final int _cursorIndexOfHoraExecucao = CursorUtil.getColumnIndexOrThrow(_cursor, "horaExecucao");
          final List<HistoricoTreinoEntity> _result = new ArrayList<HistoricoTreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HistoricoTreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpTreinoId;
            if (_cursor.isNull(_cursorIndexOfTreinoId)) {
              _tmpTreinoId = null;
            } else {
              _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            }
            final String _tmpNomeTreino;
            _tmpNomeTreino = _cursor.getString(_cursorIndexOfNomeTreino);
            final String _tmpDataExecucao;
            _tmpDataExecucao = _cursor.getString(_cursorIndexOfDataExecucao);
            final String _tmpHoraExecucao;
            _tmpHoraExecucao = _cursor.getString(_cursorIndexOfHoraExecucao);
            _item = new HistoricoTreinoEntity(_tmpId,_tmpTreinoId,_tmpNomeTreino,_tmpDataExecucao,_tmpHoraExecucao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

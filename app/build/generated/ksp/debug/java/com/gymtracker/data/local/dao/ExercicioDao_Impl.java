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
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.gymtracker.data.local.entity.ExercicioEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class ExercicioDao_Impl implements ExercicioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExercicioEntity> __insertionAdapterOfExercicioEntity;

  private final EntityDeletionOrUpdateAdapter<ExercicioEntity> __deletionAdapterOfExercicioEntity;

  private final EntityDeletionOrUpdateAdapter<ExercicioEntity> __updateAdapterOfExercicioEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOrdem;

  public ExercicioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExercicioEntity = new EntityInsertionAdapter<ExercicioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exercicios` (`id`,`treinoId`,`nome`,`series`,`repeticoes`,`carga`,`descanso`,`observacoes`,`ordem`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExercicioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTreinoId());
        statement.bindString(3, entity.getNome());
        statement.bindLong(4, entity.getSeries());
        statement.bindLong(5, entity.getRepeticoes());
        statement.bindDouble(6, entity.getCarga());
        statement.bindLong(7, entity.getDescanso());
        statement.bindString(8, entity.getObservacoes());
        statement.bindLong(9, entity.getOrdem());
      }
    };
    this.__deletionAdapterOfExercicioEntity = new EntityDeletionOrUpdateAdapter<ExercicioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `exercicios` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExercicioEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfExercicioEntity = new EntityDeletionOrUpdateAdapter<ExercicioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `exercicios` SET `id` = ?,`treinoId` = ?,`nome` = ?,`series` = ?,`repeticoes` = ?,`carga` = ?,`descanso` = ?,`observacoes` = ?,`ordem` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExercicioEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTreinoId());
        statement.bindString(3, entity.getNome());
        statement.bindLong(4, entity.getSeries());
        statement.bindLong(5, entity.getRepeticoes());
        statement.bindDouble(6, entity.getCarga());
        statement.bindLong(7, entity.getDescanso());
        statement.bindString(8, entity.getObservacoes());
        statement.bindLong(9, entity.getOrdem());
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateOrdem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE exercicios SET ordem = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertExercicio(final ExercicioEntity exercicio,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExercicioEntity.insertAndReturnId(exercicio);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExercicio(final ExercicioEntity exercicio,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExercicioEntity.handle(exercicio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateExercicio(final ExercicioEntity exercicio,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExercicioEntity.handle(exercicio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOrdem(final long id, final int ordem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOrdem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, ordem);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateOrdem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExercicioEntity>> getExerciciosByTreino(final long treinoId) {
    final String _sql = "SELECT * FROM exercicios WHERE treinoId = ? ORDER BY ordem ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, treinoId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercicios"}, new Callable<List<ExercicioEntity>>() {
      @Override
      @NonNull
      public List<ExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "series");
          final int _cursorIndexOfRepeticoes = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoes");
          final int _cursorIndexOfCarga = CursorUtil.getColumnIndexOrThrow(_cursor, "carga");
          final int _cursorIndexOfDescanso = CursorUtil.getColumnIndexOrThrow(_cursor, "descanso");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final int _cursorIndexOfOrdem = CursorUtil.getColumnIndexOrThrow(_cursor, "ordem");
          final List<ExercicioEntity> _result = new ArrayList<ExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTreinoId;
            _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpSeries;
            _tmpSeries = _cursor.getInt(_cursorIndexOfSeries);
            final int _tmpRepeticoes;
            _tmpRepeticoes = _cursor.getInt(_cursorIndexOfRepeticoes);
            final double _tmpCarga;
            _tmpCarga = _cursor.getDouble(_cursorIndexOfCarga);
            final int _tmpDescanso;
            _tmpDescanso = _cursor.getInt(_cursorIndexOfDescanso);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            final int _tmpOrdem;
            _tmpOrdem = _cursor.getInt(_cursorIndexOfOrdem);
            _item = new ExercicioEntity(_tmpId,_tmpTreinoId,_tmpNome,_tmpSeries,_tmpRepeticoes,_tmpCarga,_tmpDescanso,_tmpObservacoes,_tmpOrdem);
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
  public Object getExercicioById(final long id,
      final Continuation<? super ExercicioEntity> $completion) {
    final String _sql = "SELECT * FROM exercicios WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExercicioEntity>() {
      @Override
      @Nullable
      public ExercicioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "series");
          final int _cursorIndexOfRepeticoes = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoes");
          final int _cursorIndexOfCarga = CursorUtil.getColumnIndexOrThrow(_cursor, "carga");
          final int _cursorIndexOfDescanso = CursorUtil.getColumnIndexOrThrow(_cursor, "descanso");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final int _cursorIndexOfOrdem = CursorUtil.getColumnIndexOrThrow(_cursor, "ordem");
          final ExercicioEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTreinoId;
            _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpSeries;
            _tmpSeries = _cursor.getInt(_cursorIndexOfSeries);
            final int _tmpRepeticoes;
            _tmpRepeticoes = _cursor.getInt(_cursorIndexOfRepeticoes);
            final double _tmpCarga;
            _tmpCarga = _cursor.getDouble(_cursorIndexOfCarga);
            final int _tmpDescanso;
            _tmpDescanso = _cursor.getInt(_cursorIndexOfDescanso);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            final int _tmpOrdem;
            _tmpOrdem = _cursor.getInt(_cursorIndexOfOrdem);
            _result = new ExercicioEntity(_tmpId,_tmpTreinoId,_tmpNome,_tmpSeries,_tmpRepeticoes,_tmpCarga,_tmpDescanso,_tmpObservacoes,_tmpOrdem);
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
  public Flow<List<ExercicioEntity>> searchExercicios(final String query) {
    final String _sql = "SELECT * FROM exercicios WHERE nome LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercicios"}, new Callable<List<ExercicioEntity>>() {
      @Override
      @NonNull
      public List<ExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "series");
          final int _cursorIndexOfRepeticoes = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoes");
          final int _cursorIndexOfCarga = CursorUtil.getColumnIndexOrThrow(_cursor, "carga");
          final int _cursorIndexOfDescanso = CursorUtil.getColumnIndexOrThrow(_cursor, "descanso");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final int _cursorIndexOfOrdem = CursorUtil.getColumnIndexOrThrow(_cursor, "ordem");
          final List<ExercicioEntity> _result = new ArrayList<ExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTreinoId;
            _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpSeries;
            _tmpSeries = _cursor.getInt(_cursorIndexOfSeries);
            final int _tmpRepeticoes;
            _tmpRepeticoes = _cursor.getInt(_cursorIndexOfRepeticoes);
            final double _tmpCarga;
            _tmpCarga = _cursor.getDouble(_cursorIndexOfCarga);
            final int _tmpDescanso;
            _tmpDescanso = _cursor.getInt(_cursorIndexOfDescanso);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            final int _tmpOrdem;
            _tmpOrdem = _cursor.getInt(_cursorIndexOfOrdem);
            _item = new ExercicioEntity(_tmpId,_tmpTreinoId,_tmpNome,_tmpSeries,_tmpRepeticoes,_tmpCarga,_tmpDescanso,_tmpObservacoes,_tmpOrdem);
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
  public Object getExerciciosByTreinoSync(final long treinoId,
      final Continuation<? super List<ExercicioEntity>> $completion) {
    final String _sql = "SELECT * FROM exercicios WHERE treinoId = ? ORDER BY ordem ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, treinoId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExercicioEntity>>() {
      @Override
      @NonNull
      public List<ExercicioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTreinoId = CursorUtil.getColumnIndexOrThrow(_cursor, "treinoId");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "series");
          final int _cursorIndexOfRepeticoes = CursorUtil.getColumnIndexOrThrow(_cursor, "repeticoes");
          final int _cursorIndexOfCarga = CursorUtil.getColumnIndexOrThrow(_cursor, "carga");
          final int _cursorIndexOfDescanso = CursorUtil.getColumnIndexOrThrow(_cursor, "descanso");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final int _cursorIndexOfOrdem = CursorUtil.getColumnIndexOrThrow(_cursor, "ordem");
          final List<ExercicioEntity> _result = new ArrayList<ExercicioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExercicioEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTreinoId;
            _tmpTreinoId = _cursor.getLong(_cursorIndexOfTreinoId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpSeries;
            _tmpSeries = _cursor.getInt(_cursorIndexOfSeries);
            final int _tmpRepeticoes;
            _tmpRepeticoes = _cursor.getInt(_cursorIndexOfRepeticoes);
            final double _tmpCarga;
            _tmpCarga = _cursor.getDouble(_cursorIndexOfCarga);
            final int _tmpDescanso;
            _tmpDescanso = _cursor.getInt(_cursorIndexOfDescanso);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            final int _tmpOrdem;
            _tmpOrdem = _cursor.getInt(_cursorIndexOfOrdem);
            _item = new ExercicioEntity(_tmpId,_tmpTreinoId,_tmpNome,_tmpSeries,_tmpRepeticoes,_tmpCarga,_tmpDescanso,_tmpObservacoes,_tmpOrdem);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

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
import com.gymtracker.data.local.entity.TreinoEntity;
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
public final class TreinoDao_Impl implements TreinoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TreinoEntity> __insertionAdapterOfTreinoEntity;

  private final EntityDeletionOrUpdateAdapter<TreinoEntity> __deletionAdapterOfTreinoEntity;

  private final EntityDeletionOrUpdateAdapter<TreinoEntity> __updateAdapterOfTreinoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTreinoById;

  public TreinoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTreinoEntity = new EntityInsertionAdapter<TreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `treinos` (`id`,`nome`,`diaSemana`,`grupoMuscular`,`observacoes`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TreinoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getDiaSemana());
        statement.bindString(4, entity.getGrupoMuscular());
        statement.bindString(5, entity.getObservacoes());
      }
    };
    this.__deletionAdapterOfTreinoEntity = new EntityDeletionOrUpdateAdapter<TreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `treinos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TreinoEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTreinoEntity = new EntityDeletionOrUpdateAdapter<TreinoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `treinos` SET `id` = ?,`nome` = ?,`diaSemana` = ?,`grupoMuscular` = ?,`observacoes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TreinoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getDiaSemana());
        statement.bindString(4, entity.getGrupoMuscular());
        statement.bindString(5, entity.getObservacoes());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteTreinoById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM treinos WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTreino(final TreinoEntity treino,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTreinoEntity.insertAndReturnId(treino);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTreino(final TreinoEntity treino,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTreinoEntity.handle(treino);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTreino(final TreinoEntity treino,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTreinoEntity.handle(treino);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTreinoById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTreinoById.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfDeleteTreinoById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TreinoEntity>> getAllTreinos() {
    final String _sql = "SELECT * FROM treinos ORDER BY diaSemana ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"treinos"}, new Callable<List<TreinoEntity>>() {
      @Override
      @NonNull
      public List<TreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDiaSemana = CursorUtil.getColumnIndexOrThrow(_cursor, "diaSemana");
          final int _cursorIndexOfGrupoMuscular = CursorUtil.getColumnIndexOrThrow(_cursor, "grupoMuscular");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final List<TreinoEntity> _result = new ArrayList<TreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDiaSemana;
            _tmpDiaSemana = _cursor.getInt(_cursorIndexOfDiaSemana);
            final String _tmpGrupoMuscular;
            _tmpGrupoMuscular = _cursor.getString(_cursorIndexOfGrupoMuscular);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            _item = new TreinoEntity(_tmpId,_tmpNome,_tmpDiaSemana,_tmpGrupoMuscular,_tmpObservacoes);
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
  public Object getTreinoById(final long id, final Continuation<? super TreinoEntity> $completion) {
    final String _sql = "SELECT * FROM treinos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TreinoEntity>() {
      @Override
      @Nullable
      public TreinoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDiaSemana = CursorUtil.getColumnIndexOrThrow(_cursor, "diaSemana");
          final int _cursorIndexOfGrupoMuscular = CursorUtil.getColumnIndexOrThrow(_cursor, "grupoMuscular");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final TreinoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDiaSemana;
            _tmpDiaSemana = _cursor.getInt(_cursorIndexOfDiaSemana);
            final String _tmpGrupoMuscular;
            _tmpGrupoMuscular = _cursor.getString(_cursorIndexOfGrupoMuscular);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            _result = new TreinoEntity(_tmpId,_tmpNome,_tmpDiaSemana,_tmpGrupoMuscular,_tmpObservacoes);
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
  public Object getTreinoPorDia(final int diaSemana,
      final Continuation<? super TreinoEntity> $completion) {
    final String _sql = "SELECT * FROM treinos WHERE diaSemana = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, diaSemana);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TreinoEntity>() {
      @Override
      @Nullable
      public TreinoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDiaSemana = CursorUtil.getColumnIndexOrThrow(_cursor, "diaSemana");
          final int _cursorIndexOfGrupoMuscular = CursorUtil.getColumnIndexOrThrow(_cursor, "grupoMuscular");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final TreinoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDiaSemana;
            _tmpDiaSemana = _cursor.getInt(_cursorIndexOfDiaSemana);
            final String _tmpGrupoMuscular;
            _tmpGrupoMuscular = _cursor.getString(_cursorIndexOfGrupoMuscular);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            _result = new TreinoEntity(_tmpId,_tmpNome,_tmpDiaSemana,_tmpGrupoMuscular,_tmpObservacoes);
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
  public Flow<List<TreinoEntity>> searchTreinos(final String query) {
    final String _sql = "SELECT * FROM treinos WHERE nome LIKE '%' || ? || '%' OR grupoMuscular LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"treinos"}, new Callable<List<TreinoEntity>>() {
      @Override
      @NonNull
      public List<TreinoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDiaSemana = CursorUtil.getColumnIndexOrThrow(_cursor, "diaSemana");
          final int _cursorIndexOfGrupoMuscular = CursorUtil.getColumnIndexOrThrow(_cursor, "grupoMuscular");
          final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
          final List<TreinoEntity> _result = new ArrayList<TreinoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TreinoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDiaSemana;
            _tmpDiaSemana = _cursor.getInt(_cursorIndexOfDiaSemana);
            final String _tmpGrupoMuscular;
            _tmpGrupoMuscular = _cursor.getString(_cursorIndexOfGrupoMuscular);
            final String _tmpObservacoes;
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
            _item = new TreinoEntity(_tmpId,_tmpNome,_tmpDiaSemana,_tmpGrupoMuscular,_tmpObservacoes);
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

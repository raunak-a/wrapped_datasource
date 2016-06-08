package org.raunaka.database.datasource;

import com.codahale.metrics.Clock;
import com.codahale.metrics.Timer;
import lombok.Data;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * @author rAun007
 */

@Data
public class WrappedStatement implements Statement {

    private final Statement   statement;
    protected final DBMetrics dbMetrics;

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        Timer.Context context = dbMetrics.getReadTimer().time();
        try {
            return statement.executeQuery(sql);
        } finally {
            context.stop();
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeUpdate(sql);
        } finally {
            context.stop();
        }
    }

    @Override
    public void close() throws SQLException {
        statement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        statement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        statement.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        statement.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        statement.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        statement.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        statement.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        statement.setCursorName(name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {

        boolean returnVal;
        long startTime = Clock.defaultClock().getTick();
        returnVal = statement.execute(sql);
        long endTime = Clock.defaultClock().getTick();

        if (returnVal) {
            dbMetrics.getReadTimer().update(endTime - startTime, TimeUnit.NANOSECONDS);
        } else {
            dbMetrics.getWriteTimer().update(endTime - startTime, TimeUnit.NANOSECONDS);
        }
        return returnVal;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        Timer.Context context = dbMetrics.getReadTimer().time();
        try {
            return statement.getResultSet();
        } finally {
            context.stop();
        }
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        statement.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        statement.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        statement.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {

        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeBatch();
        } finally {
            context.stop();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return statement.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeUpdate(sql, autoGeneratedKeys);
        } finally {
            context.stop();
        }
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeUpdate(sql, columnIndexes);
        } finally {
            context.stop();
        }
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeUpdate(sql, columnNames);
        } finally {
            context.stop();
        }
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        Timer.Context context = dbMetrics.getUnknownQueryTimer().time();
        try {
            return statement.execute(sql, autoGeneratedKeys);
        } finally {
            context.stop();
        }
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        Timer.Context context = dbMetrics.getUnknownQueryTimer().time();
        try {
            return statement.execute(sql, columnIndexes);
        } finally {
            context.stop();
        }
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        Timer.Context context = dbMetrics.getUnknownQueryTimer().time();
        try {
            return statement.execute(sql, columnNames);
        } finally {
            context.stop();
        }
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        statement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return statement.isCloseOnCompletion();
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        return statement.getLargeUpdateCount();
    }

    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        statement.setLargeMaxRows(max);
    }

    @Override
    public long getLargeMaxRows() throws SQLException {
        return statement.getLargeMaxRows();
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        Timer.Context context = dbMetrics.getUnknownQueryTimer().time();
        try {
            return statement.executeLargeBatch();
        } finally {
            context.stop();
        }
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeLargeUpdate(sql);
        } finally {
            context.stop();
        }
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeLargeUpdate(sql, autoGeneratedKeys);
        } finally {
            context.stop();
        }
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeLargeUpdate(sql, columnIndexes);
        } finally {
            context.stop();
        }
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        Timer.Context context = dbMetrics.getWriteTimer().time();
        try {
            return statement.executeLargeUpdate(sql, columnNames);
        } finally {
            context.stop();
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return statement.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return statement.isWrapperFor(iface);
    }
}

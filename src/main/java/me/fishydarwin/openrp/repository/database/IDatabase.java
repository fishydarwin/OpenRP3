package me.fishydarwin.openrp.repository.database;

import java.sql.Connection;

/**
 * Represents a certain type of database.
 */
public interface IDatabase {

    /**
     * Returns the connection associated to this database.
     * @return The connection.
     */
    Connection getConnection();

    /**
     * Flushes this database's resources and connection.
     */
    void flush();

}

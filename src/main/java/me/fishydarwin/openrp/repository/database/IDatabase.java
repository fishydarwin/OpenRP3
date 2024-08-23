/*
 *    Copyright 2024 The OpenRP Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

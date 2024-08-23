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

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalH2Database implements IDatabase {

    private final String username = "sa";
    private final String password = "";
    private final Connection connection;

    public LocalH2Database(File localFile) throws IOException, SQLException {

        // Ensure local file exists & is valid.
        if (localFile.getName().matches("^.*\\.[^\\\\]+$")) { // voodoo magic regex
            // https://stackoverflow.com/questions/22863973/regex-check-if-a-file-has-any-extension
            throw new IOException("Local DB file must not terminate in any format.");
        }
        if (!localFile.exists()) {
            if (!localFile.getParentFile().exists()) {
                if (!localFile.mkdirs()) {
                    throw new IOException("Could not create directories for local file " + localFile);
                }
            }
            if (!localFile.createNewFile()) {
                throw new IOException("Could not make local DB file " + localFile);
            }
        }

        String connectionURL = "jdbc:h2:" + localFile.getAbsolutePath();

        connection = DriverManager.getConnection(connectionURL, username, password);

        //TODO: initialize tables & database if they dont exist!!!!!!!!
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void flush() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not flush H2 Local Connection: " + e.getMessage());
        }
    }
}

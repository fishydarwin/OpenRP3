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

package me.fishydarwin.openrp;

import me.fishydarwin.openrp.repository.database.IDatabase;
import me.fishydarwin.openrp.repository.database.LocalH2Database;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class OpenRP extends JavaPlugin {

    private static OpenRP singleton;
    public static OpenRP getInstance() {
        return singleton;
    }

    private IDatabase database;
    public IDatabase getDatabase() {
        return database;
    }

    @Override
    public void onEnable() {
        singleton = this;

        // initialize managers, utilities, etc.
        // TODO: check config.yml for database type
        try {
            database = new LocalH2Database(new File(getDataFolder(), "database"));
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Could not initialize database: " + e.getMessage());
        }

    }

    @Override
    public void onDisable() {
        // clean up managers, utilities, etc.
        getServer().getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
        database.flush();
    }
}

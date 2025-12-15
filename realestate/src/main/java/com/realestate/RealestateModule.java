package com.realestate;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Модуль Google Guice для впровадження залежностей SQLite
 */
public class RealestateModule extends AbstractModule {

    @Override
    protected void configure() {
        // Прив'язуємо JDBC URL для SQLite
        bindConstant().annotatedWith(Names.named("JDBC_URL")).to("jdbc:sqlite:realestate.db");
    }

    @Provides
    @Singleton
    public Connection provideConnection(@Named("JDBC_URL") String jdbcUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl);
        initializeDatabase(connection);
        return connection;
    }

    private void initializeDatabase(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Створюємо таблицю для Deal
            String createDealTable = "CREATE TABLE IF NOT EXISTS deals (" +
                "id INTEGER PRIMARY KEY, " +
                "date TEXT NOT NULL, " +
                "status TEXT NOT NULL" +
                ");";
            stmt.execute(createDealTable);

            String createBuyerTable = "CREATE TABLE IF NOT EXISTS buyers (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "contactInfo TEXT NOT NULL, " +
                "deposit REAL NOT NULL" +
                ");";
            stmt.execute(createBuyerTable);

            String createAgentTable = "CREATE TABLE IF NOT EXISTS agents (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "contactInfo TEXT NOT NULL, " +
                "agency TEXT NOT NULL" +
                ");";
            stmt.execute(createAgentTable);

            String createSellerTable = "CREATE TABLE IF NOT EXISTS sellers (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "contactInfo TEXT NOT NULL, " +
                "property TEXT NOT NULL" +
                ");";
            stmt.execute(createSellerTable);
        }
    }
}
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

        // Встановлюємо фабрики для assisted injection
        install(new FactoryModuleBuilder()
            .implement(Buyer.class, Buyer.class)
            .build(BuyerFactory.class));
        install(new FactoryModuleBuilder()
            .implement(Seller.class, Seller.class)
            .build(SellerFactory.class));
        install(new FactoryModuleBuilder()
            .implement(Agent.class, Agent.class)
            .build(AgentFactory.class));
        install(new FactoryModuleBuilder()
            .implement(Deal.class, Deal.class)
            .build(DealFactory.class));
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
                "status TEXT NOT NULL, " +
                "seller_id INTEGER, " +
                "buyer_id INTEGER, " +
                "agent_id INTEGER, " +
                "bank_id INTEGER" +
                ");";
            stmt.execute(createDealTable);

            // Можна додати інші таблиці для Seller, Buyer, Agent, Bank якщо потрібно
            String createSellerTable = "CREATE TABLE IF NOT EXISTS sellers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "property TEXT NOT NULL" +
                ");";
            stmt.execute(createSellerTable);

            String createBuyerTable = "CREATE TABLE IF NOT EXISTS buyers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "budget REAL NOT NULL" +
                ");";
            stmt.execute(createBuyerTable);

            String createAgentTable = "CREATE TABLE IF NOT EXISTS agents (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "company TEXT NOT NULL" +
                ");";
            stmt.execute(createAgentTable);

            String createBankTable = "CREATE TABLE IF NOT EXISTS banks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL" +
                ");";
            stmt.execute(createBankTable);
        }
    }
}
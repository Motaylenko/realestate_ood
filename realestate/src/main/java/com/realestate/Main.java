package com.realestate;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Головний клас для демонстрації роботи програми
 */
public class Main {
    public static void main(String[] args) {
        // Створення інжектора Guice
        Injector injector = Guice.createInjector(new RealestateModule());

        // Отримання фабрик
        BuyerFactory buyerFactory = injector.getInstance(BuyerFactory.class);
        SellerFactory sellerFactory = injector.getInstance(SellerFactory.class);
        AgentFactory agentFactory = injector.getInstance(AgentFactory.class);
        DealFactory dealFactory = injector.getInstance(DealFactory.class);

        // Створення учасників
        Buyer buyer = buyerFactory.create(1, "Іван Петренко", "ivan@email.com", 10000.0f);
        Seller seller = sellerFactory.create(1, "Марія Коваленко", "maria@email.com", "Квартира на Хрещатику");
        Agent agent = agentFactory.create(1, "Олег Сидоренко", "oleg@email.com", "Київська Нерухомість");
        Bank bank = new Bank("ПриватБанк", "office@privatbank.ua");
        
        // Створення угоди
        Deal deal = dealFactory.create(1, "2025-10-20");
        deal.setBuyer(buyer);
        deal.setSeller(seller);
        deal.setAgent(agent);
        deal.setBank(bank);
        
        // Демонстрація процесу
        System.out.println("\n=== Демонстрація роботи програми ===\n");
        
        agent.organizeTour();
        agent.showProperty();
        
        buyer.sendProposal();
        seller.reviewOffer();
        seller.counterOffer();
        
        if (bank.evaluateMortgage()) {
            buyer.finalizeDeal();
            deal.confirm();
        } else {
            deal.cancel();
        }
    }
}
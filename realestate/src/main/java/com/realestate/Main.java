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

        DealService dealService = injector.getInstance(DealService.class);

        // Створення учасників з впровадженням залежностей через сетери
        Buyer buyer = new Buyer();
        injector.injectMembers(buyer);
        buyer.setId(1);
        buyer.setName("Іван Петренко");
        buyer.setContactInfo("ivan@email.com");
        buyer.setDeposit(10000.0f);

        Seller seller = new Seller();
        injector.injectMembers(seller);
        seller.setId(1);
        seller.setName("Марія Коваленко");
        seller.setContactInfo("maria@email.com");
        seller.setProperty("Квартира на Хрещатику");

        Agent agent = new Agent();
        injector.injectMembers(agent);
        agent.setId(1);
        agent.setName("Олег Сидоренко");
        agent.setContactInfo("oleg@email.com");
        agent.setAgency("Київська Нерухомість");

        Bank bank = new Bank("ПриватБанк", "office@privatbank.ua");
        
        // Створення угоди
        Deal deal = new Deal();
        deal.setId(1);
        deal.setDate("2025-10-20");
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
            dealService.saveDeal(deal);
        } else {
            deal.cancel();
            dealService.saveDeal(deal);
        }
    }
}
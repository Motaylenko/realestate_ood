/**
 * Головний клас для демонстрації роботи програми
 */
public class Main {
    public static void main(String[] args) {
        // Створення учасників
        Buyer buyer = new Buyer("Іван Петренко", "ivan@email.com", 10000.0f);
        Seller seller = new Seller("Марія Коваленко", "maria@email.com", "Квартира на Хрещатику");
        Agent agent = new Agent("Олег Сидоренко", "oleg@email.com", "Київська Нерухомість");
        Bank bank = new Bank("ПриватБанк", "office@privatbank.ua");
        
        // Створення угоди
        Deal deal = new Deal(1, "2025-10-20");
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
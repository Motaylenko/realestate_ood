/**
 * Клас банку
 */
public class Bank extends Participant {
    public Bank(String name, String contactInfo) {
        super(name, contactInfo);
    }
    
    public boolean evaluateMortgage() {
        System.out.println("Банк " + name + " оцінює можливість надання іпотеки");
        return true; // Імітація позитивного рішення
    }
}
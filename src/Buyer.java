/**
 * Клас покупця нерухомості
 */
public class Buyer extends Participant {
    private float deposit;
    
    public Buyer(String name, String contactInfo, float deposit) {
        super(name, contactInfo);
        this.deposit = deposit;
    }
    
    public void sendProposal() {
        System.out.println("Покупець " + name + " надіслав пропозицію");
    }
    
    public final void finalizeDeal() {
        System.out.println("Покупець " + name + " завершує угоду. Завдаток: " + deposit);
    }
}
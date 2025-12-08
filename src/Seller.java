/**
 * Клас продавця нерухомості
 */
public class Seller extends Participant implements Reviewable {
    private String property;
    
    public Seller(String name, String contactInfo, String property) {
        super(name, contactInfo);
        this.property = property;
    }
    
    public void counterOffer() {
        System.out.println("Продавець " + name + " зробив зустрічну пропозицію щодо " + property);
    }
    
    @Override
    public void reviewOffer() {
        System.out.println("Продавець " + name + " переглядає пропозицію");
    }
}
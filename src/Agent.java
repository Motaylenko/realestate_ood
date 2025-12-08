/**
 * Клас агента з нерухомості
 */
public class Agent extends Participant implements Reviewable {
    private String agency;
    
    public Agent(String name, String contactInfo, String agency) {
        super(name, contactInfo);
        this.agency = agency;
    }
    
    public void organizeTour() {
        System.out.println("Агент " + name + " з " + agency + " організовує 3D-тур");
    }
    
    public void showProperty() {
        System.out.println("Агент " + name + " показує нерухомість");
    }
    
    @Override
    public void reviewOffer() {
        System.out.println("Агент " + name + " переглядає пропозицію");
    }
}
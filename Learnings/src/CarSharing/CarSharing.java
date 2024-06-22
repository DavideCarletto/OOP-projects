package CarSharing;
public class CarSharing {


    public static void main(String[] args) throws Exception { //il main è statico perchè la vm non deve creare un istanza di CarSharing per eseguire il main. Ponendolo static non è necessario  
        Posto p1 = new Posto(Posizione.ANTERIORE, Lato.CENTRALE, false, false);
        Posto p2 = new Posto(Posizione.POSTERIORE, Lato.DESTRO, false, true);
        Posto p3 = new Posto(Posizione.ANTERIORE, Lato.SINISTRO, true, false);

        CarSharingService service = CarSharingServiceFactory.carSharingServiceFactory();
        
        service.prenota(p1);
        service.prenota(p2);
        service.prenota(p3);

    }
}

package CarSharing;
public class CarSharingServiceImp implements CarSharingService{

    @Override
    public void prenota(Posto posto) {
        if(posto.isConducente() == false && posto.isPrenotato()== false){
            System.out.println("Posto prenotato: "+posto.toString());
            posto.setPrenotato(true);
        }
    }

    
}

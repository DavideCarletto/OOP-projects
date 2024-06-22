package CarSharing;
public class CarSharingServiceFactory {

    public static CarSharingService carSharingServiceFactory(){
        CarSharingService carSharingService = (CarSharingService) new CarSharingServiceImp();
        return carSharingService; 
    } 
}

package Biblioteche;

public class InvalidCode extends Exception{

    public InvalidCode(String s){
        super(s);

        System.out.println(s);
    }

}

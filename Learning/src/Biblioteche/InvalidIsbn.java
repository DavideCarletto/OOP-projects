package Biblioteche;

public class InvalidIsbn extends Exception{

        public InvalidIsbn(String s){
            super(s);

            System.out.println(s);
        }
}

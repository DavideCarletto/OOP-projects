package Biblioteche;

import java.util.ArrayList;

public class ElencoLibri extends ArrayList<Libro>{
    
    public void addLibro(Libro libro){
        add(libro);
    }

    public boolean checkPresenzaLibro(String ISBN){
        boolean value = false;

        for(int i=0; i<this.size();i++){
            if(ISBN.equals(this.get(i).getISBN())){
                value = true;
                break;
            }
            
        }
        return value;
    }

    public ElencoLibri getElencoLibriOrded(){
        ElencoLibri sorted = new ElencoLibri();
        sorted.addAll(this);
        
        for(Libro libro: sorted){
            libro.orderAutori(libro);
        }

        return sorted;
    }

    public void printElenco(){
        for(Libro libro: this){
            System.out.println(libro.toString());
        }
    }

    public Libro getLibroFromIsbn(String isbn){
        for(Libro libro: this){
            if(isbn.equals(libro.getISBN()))
                return libro;
        }
        return null;
    }
}

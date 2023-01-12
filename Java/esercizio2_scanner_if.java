// scrivere un programma stampi il minimo di due interi presi da tastiera.
import java.util.Scanner;       // importo la classe Scanner della libreria util

class principale {
    public static void main(String[] argv){
        Scanner scan = new Scanner(System.in);      // scan e' una istanza di Scanner

        // richiesta del primo numero da tastiera
        System.out.print("Inserire il primo numero: ");
        int primo = scan.nextInt();
        
        // richiesta del secondo numero da tastiera
        System.out.print("Inserire il secondo numero: ");
        int secondo = scan.nextInt();

        // stampa del risultato
        System.out.print("Il minore e': ");
        
        if(primo > secondo){        // se secondo e' il minore lo stampo
            System.out.print(secondo);
        }       // fine if

        if(secondo > primo){        // se primo e' il minore lo stampo
            System.out.print(primo);
        }       // fine if

        if(primo == secondo){       // se sono uguali non stampo nessuno
            System.out.print("nessuno, sono uguali.");
        }       // fine if
    }       // fine main
}       // fine classe

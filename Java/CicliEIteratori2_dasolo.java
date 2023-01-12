// Inizializzare 3 variabili del tipo che pensate adatto: X = 11 , Y = 22 , Z = 12.5
// Creare un menu con quattro opzioni: Aggiungi - Sottrai - Stampa - Esci
// Tramite aggiungi dovremo poter scegliere a quale variabile e poi aggiungere un numero a scelta 
// Tramite sottrai dovremo poter scegliere a quale variabile e poi sottrarre un numero a scelta senza poter scendere sotto 1 di valore minimo
// Tramite stampa dovrà Poter stampare o tutte le variabili o una a scelta.
// Tramite Esci dovrà uscire dal loop e ringraziare.

// importazione Scanner
import java.util.Scanner;

// INIZIO CLASSE DEPOSITO
class Deposito{
    // dichiarazione e inizializzazione variabili
    int X;
    int Y;
    double Z;

    // costruttore
    Deposito(int X, int Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;

        return;
    }
    
    // aggiunge daaggiungere all'attributo variabile.
    // il carattere può essere maiuscolo o minuscolo.
    // se non è un carattere valido stampa "Variabile inesistente!"
    void aggiungi(char variabile,int daaggiungere){

        // controllo quale attributo è variabile (case-insensitive)
        if(variabile == 'x' || variabile == 'X') X += daaggiungere;
        if(variabile == 'y' || variabile == 'Y') Y += daaggiungere;
        if(variabile == 'z' || variabile == 'Z') Z += daaggiungere;

        // se non è un attributo stampo un errore
        if(variabile != 'x' && variabile != 'X' && variabile != 'y' && variabile != 'Y' && variabile != 'z' && variabile != 'Z') System.out.println("Variabile inesistente!");

        return;
    }
    
    // sottrae datogliere all'attributo variabile
    // se la variabile non è abbastanza grande da rimanere positiva o nulla, stampa "Non posso sottrarre datogliere a variabile"
    // se non è un carattere valido stampa "Variabile inesistente!"
    void sottrai(char variabile,int datogliere){

        // controllo le variabili e eventualmente stampo che non è possibile
        if( (variabile == 'x' || variabile == 'X') && (X - datogliere < 0 ) ) {
            System.out.println("Non posso sottrarre " + datogliere + "a X");
            return;
        }
        if( (variabile == 'y' || variabile == 'Y') && (X - datogliere < 0 ) ) {
            System.out.println("Non posso sottrarre " + datogliere + " a Y");
            return;
        }
        if( (variabile == 'z' || variabile == 'Z') && (X - datogliere < 0 ) ) {
            System.out.println("Non posso sottrarre " + datogliere + " a Z");
            return;
        }
        
        // decremento la variabile giusta
        if(variabile == 'x' || variabile == 'X') X -= datogliere;
        if(variabile == 'y' || variabile == 'Y') Y -= datogliere;
        if(variabile == 'z' || variabile == 'Z') Z -= datogliere;

        // se non è un attributo stampo un errore
        if(variabile != 'x' && variabile != 'X' && variabile != 'y' && variabile != 'Y' && variabile != 'z' && variabile != 'Z') System.out.println("Variabile inesistente!");

        return;
    }
    
    // stampa tutto il deposito
    void stampatutti(){

        // stampo una intestazione
        System.out.println("-------DEPOSITO DATI COMPLETO--------");

        // stampo tutti gli attributi
        System.out.println("X = " + X);
        System.out.println("Y = " + Y);
        System.out.println("Z = " + Z);

        // stampo la fine della intestazione
        System.out.println("-------------------------------------");
    }
    
    // stampa l'attributo variabile
    void stampauno (char variabile){

        // stampo una intestazione
        System.out.println("-------ACCESSO AD UN DATO DEL DEPOSITO------");

        // stampo la variabile variabile
        if(variabile == 'x' || variabile == 'X') System.out.println("X = " + X);
        if(variabile == 'y' || variabile == 'Y') System.out.println("Y = " + Y);
        if(variabile == 'z' || variabile == 'Z') System.out.println("Z = " + Z);

        // se la variabile non esiste stampa un messaggio
        if(variabile != 'x' && variabile != 'X' && variabile != 'y' && variabile != 'Y' && variabile != 'z' && variabile != 'Z') System.out.println("Variabile inesistente!");
        System.out.println("-------------------------------------");
    }
}       // FINE CLASSE DEPOSITO


// INIZIO CLASSE PRINCIPALE
class principale{
    public static void main(String[] arg){

        // istanzio un Deposito
        Deposito dati = new Deposito(11,22,12.5);       

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // dichiaro e inizializzo variabili di utilità
        String comando;
        String variabile;
        char carattere;
        int diquanto;

        do {

            // stampo plancia menù
            System.out.println("\n---------------MENU---------------");
            System.out.println("I comandi possibili sono: Aggiungi, Sottrai, Stampa, Esci");
            System.out.print("Inserisci un comando valido: ");

            // prendo un comando da tastiera
            comando = scan.nextLine();

            // se è stato inserito "Aggiungi" (case-insensitive)
            if(comando.equalsIgnoreCase("Aggiungi")){
                
                // chiedo quale variabile da tastiera
                System.out.print("Quale variabile? [se inseriti più caratteri viene preso il primo]");
                variabile = scan.nextLine();

                // considero solo il primo carattere (indicato a video)
                carattere = variabile.charAt(0);

                // chiedo di quanto vuole aggiungere
                System.out.print("Di quanto vuoi aggiungere? ");
                diquanto = scan.nextInt();

                // chiamo metodo aggiungi
                dati.aggiungi(carattere, diquanto);
            }
            
            // se è stato inserito "Sottrai" (case-insensitive)
            if (comando.equalsIgnoreCase("Sottrai")){

                // chiedo quale variabile da tastiera
                System.out.print("Quale variabile? [se inseriti più caratteri viene preso il primo]");
                variabile = scan.nextLine();

                // considero solo il primo carattere (indicato a video)
                carattere = variabile.charAt(0);

                // chiedo di quanto si vuole sottrarre da tastiera
                System.out.print("Di quanto vuoi sottrarre? ");
                diquanto = scan.nextInt();

                // chiamo metodo sottrai
                dati.sottrai(carattere, diquanto);
            }
            
            // se è stato inserito "Sottrai" (case-insensitive)
            if (comando.equalsIgnoreCase("Stampa")){

                // chiedo se si vuole stampare tutto o una sola variabile
                System.out.print("Tutto o una variabile? ");
                variabile = scan.nextLine();

                // se è stato inserito "Tutto" (case-insensitive)
                if(variabile.equalsIgnoreCase("Tutto")){
                    //chiamo metodo stampatutti
                    dati.stampatutti();
                }

                // se è stato inserito "una variabile" (case-insensitive)
                if(variabile.equalsIgnoreCase("una variabile") || variabile.equalsIgnoreCase("variabile")){
                    
                    //chiedo quale variabile da tastiera
                    System.out.print("Quale variabile? [se inseriti più caratteri viene preso il primo]");
                    variabile = scan.nextLine();

                    // considero solo il primo carattere (indicato a video)
                    carattere = variabile.charAt(0);

                    //chiamo metodo stampauno
                    dati.stampauno(carattere);
                }
            }

            //se è stato inserito "Esci" (case-insensitive)
            if(comando.equalsIgnoreCase("Esci"))
                
            // esco dal do-while 
                break;

        } while (true);     // fine del do-while

        // ringrazio e termino
        System.out.println("Grazie!");

        // chiudo lo Scanner scan
        scan.close();
    }
}       // FINE CLASSE PRINCIPALE
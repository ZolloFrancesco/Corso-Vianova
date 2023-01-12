// CREARE UN SISTEMA DI INSERIMENTO IN CICLO
// NEL SENSO DI AVERE UNO SCANNER, E FARLO GIRARE PIù VOLTE CON un LOOP
// CHE RIEMPA DUE ARRAY UNO DI NUMERI E UNO DI STRINGHE
// A SCELTA DELL'UTENTE FINO A CHE SCEGLIE ESCI.
import java.util.Scanner;

// classe Dati
class Dati{

    // dichiaro due array privati, uno di interi e unodi stringhe
    private
    int[] nArray;
    String[] sArray;

    public
    // costruttore che inizializza i due array.
    // prende in ingresso la dimensione del vettore di interi nElem
    // e la dimensione del vettore di stringhe sElem
    Dati(int nElem, int sElem){

        // assegno le due stringhe
        nArray = new int[nElem];
        sArray = new String[sElem];

        // riempio il vettore di numeri con degli 0
        for (int i = 0; i < this.nArray.length; i++) {
            nArray[i] = 0;
        }       // FINE CICLO FOR
        
        // riempio il vettore di stringhe con una stringa vuota
        for (int i = 0; i < this.sArray.length; i++) {
            sArray[i] = "";
        }       // FINE CICLO FOR
    }
    
    // funzione che riempie uno dei due array.
    // prende in ingresso numeri, che indica se riempire l'array di numeri o di stringhe
    void riempiArray(boolean numeri){

        // stampo una semplice intestazione
        System.out.print("\nINIZIO DELL'INSERIMENTO NELL'ARRAY DI ");

        // l'intestazione cambia a seconda della volontà del chiamante
        if(numeri) {
            System.out.println("INTERI:");
        } else {
            System.out.println("STRINGHE:");
        }       // FINE IF

        // istanzio un oggetto Scanner 
        Scanner scan = new Scanner(System.in);

        // n identifica la dimensione del vettore che il chiamante vuole riempire
        int n = (numeri)? nArray.length : sArray.length;

        // chiedo ogni elemento dell'array scelto
        for (int i = 0; i < n; i++) {

            // stampo una semplice intestaione
            System.out.print("\nInserire l'elemento " + (i+1) + " del vettore di");

            // se il chiamante intende riempire l'array di numeri
            if(numeri) {

                // finisco di stampare l'intestazione
                System.out.print(" interi:");

                // prendo l'intero da tastiera e vado a capo
                nArray[i] = scan.nextInt();
                System.out.println("");
            }       // FINE IF
            // se il chiamante intende riempire l'array di numeri
            else {

                // finisco di stampare l'intestazione
                System.out.print(" stringhe:");

                //prendo la stringa da tastiera e vado a capo
                sArray[i] = scan.nextLine();
                System.out.println("");
            }       // FINE ELSE
        }       // FINE CICLO FOR
    }       // FINE FUNZIONE
    
    // funzione che stampa uno solo di due array
    // numero indica la scelta
    void stampaArray(boolean numeri){

        // stampo una semplice intestazione
        System.out.print("\nINIZIO DELLA STAMPA DELL'ARRAY DI ");

        // completo l'intestazione a seconda della volotà del chiamante
        if(numeri) {
            System.out.println("INTERI:");
        } else {
            System.out.println("STRINGHE:");
        }       // FINE IF

        // n identifica la dimensione del vettore che il chiamante vuole riempire
        int n = (numeri)? nArray.length : sArray.length;

        // per ogni elemento inserisco nell'array appropriato
        for (int i = 0; i < n; i++) {
            if(numeri){
                System.out.print(nArray[i] + " ");
            } else {
                System.out.print(sArray[i] + " ");
            }       // FINE IF
        }       // FINE FOR

        // vado a capo
        System.out.println("");
    }
}

class app{
    public static void main(String[] arg){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // chiedo da tastiera la dimensione del vettore di interi
        System.out.println("Inserisci la dimensione del vettore di interi: ");
        int dimInt = scan.nextInt();

        // chiedo da tastiera la dimensione del vettore di stringhe
        System.out.println("Inserisci la dimensione del vettore di stringhe: ");
        int dimStr = scan.nextInt();
        
        // istanzio un oggetto di tipo Dati
        Dati data = new Dati(dimInt,dimStr);

        // dichiaro due variabili di utilità
        String comando;
        boolean numeri;

        // entro in un ciclo che ad ogni iterazione esegue un comando del menù
        while(true){

            // stampo una semplice intestazione
            System.out.println("\n---------------MENU---------------");
            System.out.println("I comandi possibili sono: Riempi, Stampa, Esci");
            System.out.print("Inserisci un comando valido: ");

            // prendo il comando da tastiera
            comando = scan.nextLine();

            // caso in cui il comando sia "Riempi" (case-insensitive)
            if(comando.equalsIgnoreCase("Riempi")){

                // chiedo da tastiera quale vettore voglio riempire
                System.out.print("Quale vettore vuoi riempire? ");
                comando = scan.nextLine();

                // numeri identifica la volontà del main di riempire il vettore di numeri o di stringhe.
                // nel caso in cui l'utente inserisca qualcosa di diverso da "Numeri" (case insensitive),
                // il programma seleziona automaticamente il vettore di stringhe
                numeri = (comando.equalsIgnoreCase("numeri")) ? true : false;

                // chiamata al metodo riempiArray, per riempire il vettore identificato da numeri
                data.riempiArray(numeri);
            }       // FINE IF RIEMPI
            
            // caso in cui il comando sia "Stampa" (case-insensitive)
            if(comando.equalsIgnoreCase("Stampa")){

                // chiedo quale vettore voglio stampare da tastiera
                System.out.print("Quale vettore vuoi stampare? ");
                comando = scan.nextLine();

                // numeri identifica la volontà del main di riempire il vettore di numeri o di stringhe.
                // nel caso in cui l'utente inserisca qualcosa di diverso da "Numeri" (case insensitive),
                // il programma seleziona automaticamente il vettore di stringhe
                numeri = (comando.equalsIgnoreCase("numeri")) ? true : false;

                // chiamata al metodo stampaArray, per stampare il vettore identificato da numeri
                data.stampaArray(numeri);
            }       // FINE IF STAMPA

            if(comando.equalsIgnoreCase("Esci")){
                break;
            }       // FINE IF ESCI
        }       // FINE CICLO WHILE
    }       // FINE MAIN
}       // FINE CLASSE
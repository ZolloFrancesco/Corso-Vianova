// Creiamo un sistema che ti permetta di inserire username e password
// Solo dopo questo inserimento il sistema deve proseguire e chiederti di confermare entrambi gli elementi
// Dopo averli confermati potremmo accedere a Quattro opzioni
// Aggiungi ad ArrayConstringhe (  Nome casuale )
// Aggiungi ad ArrayConNumeri  (  Nome casuale )
// Stampa
// Esci
// All'uscita dovrà stampare uno alla volta ogni elemento dell'array e chiederti la password per confermare le modifiche. 
// Difficile:  Far anche rimuovere e modificare gli elementi. Aggiungendo al menu due sotto funzioni per ogni scelta di aggiunta possibile

import java.util.ArrayList;
import java.util.Scanner;

// CLASSE LOGIN PER GESTIRE DATI DI ACCESSO
class Login{
    
    // attributi dove vengono salvati username e password
    private String username = "Francesco";
    private String password = "Password";

    // metodo privato che prende in ingresso username e password
    // restituisce true se utente e password corrispondono agli attributi omonimi
    private boolean passLogin(String usr, String psw){
        if(username.equals(usr) && password.equals(psw)) return true;
        return false;
    }

    // metodo che effettua il login utilizzando il metodo passLogin
    // stampa una piccola plancia dove è possibile inserire i dati
    public boolean effettua(){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // dichiaro stringhe di utilità
        String usr,psw;

        // stampo plancia
        System.out.println("---------------- ACCESSO AL SISTEMA ---------------");

        // richiedo l'username da tastiera
        System.out.print("Inserisci username: ");
        usr = scan.nextLine();
        
        // richiedo password da tastiera
        System.out.print("Inserisci password: ");
        psw = scan.nextLine();

        // controllo username e password e ritorno true se sono giusto
        if (passLogin(usr, psw)) return true;

        // altrimenti ritorno false
        return false;
    }
}

// CLASSE DATI IN CUI SALVO GLI ARRAY
class Dati{
    
    // array in cui verranno effettivamente salvate le modifiche
    private ArrayList<String> sArray_real = new ArrayList<>();
    private ArrayList<Integer> nArray_real = new ArrayList<>();

    // array in cui verranno salvate le modifiche all'interno della sessione
    private ArrayList<String> sArray = new ArrayList<>();
    private ArrayList<Integer> nArray = new ArrayList<>();

    // array log in cui salvo le modifiche sotto forma di informazioni
    private ArrayList<String> modifiche = new ArrayList<>();

    // metodo che aggiorna gli array di sessione e svuota l'array delle modifiche
    public void aggiorna(){

        // fa clear degli array di sessione
        sArray_real.clear();
        nArray_real.clear();

        // aggiorno gli array effettivi
        sArray_real.addAll(sArray);
        nArray_real.addAll(nArray);

        // svuoto il log
        modifiche.clear();

        return;
    }

    // metodo che stampa uno dei due array a seconda del valore del booleano numeri in ingresso
    public void stampa(boolean numeri){

        // vado a capo
        System.out.println("");

        // se l'array che il chiamante vuole stampare è quello di numeri
        if(numeri){

            // stampo l'array di numeri
            System.out.print("NUMERI:");
            System.out.println(nArray);
        } 
        // altrimenti stampo l'array di stringhe
        else{
            System.out.println("STRINGHE:");
            System.out.println(sArray);
        }

        // concludo la stampa
        System.out.println("---------------");

        return;
    }
    
    // metodo che modifica uno dei due array a seconda del valore di numeri in ingresso
    public void aggiungi(boolean numeri){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // variabili di utilità
        int intero_da_aggiungere;
        String stringa_da_aggiungere , stringa_di_modifica;

        // stampo semplice plancia
        System.out.print("Inserire valore da aggiungere al vettore di ");

        // se l'array che il chiamante vuole stampare è quello di numeri
        if(numeri){

            // finisco di stampare la plancia
            System.out.print(" numeri: ");

            // prendo l'intero da tastiera
            intero_da_aggiungere = scan.nextInt();

            // inserisco il nuovo elemento in coda all'array di interi
            nArray.add(intero_da_aggiungere);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai aggiunto " + intero_da_aggiungere + " al vettore di numeri";

            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica);
        } 
        // se l'array che il chiamante vuole stampare è quello di stringhe
        else{

            // finisco di stampare la plancia
            System.out.println(" stringhe: ");

            // prendo l'elemento da tastiera
            stringa_da_aggiungere = scan.nextLine();

            // aggiungo l'elemento in coda all'array di stringhe
            sArray.add(stringa_da_aggiungere);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai aggiunto (" + stringa_da_aggiungere + ") al vettore di stringhe";

            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica); 
        }

        // concludo la stampa della plancia
        System.out.println("---------------");

        return;
    }

    // metodo che modifica un array dipendentemente dal valore del booleano numeri in ingresso.
    // ritorna falso se la modifica non è andata a buon fine perchè si è inserito un indice non valido.
    boolean modifica(boolean numeri){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // variabili di utilità
        int indice, intero_da_modificare;
        String stringa_da_modificare, stringa_di_modifica;

        // stampo la parte comune della plancia
        System.out.println("STAI PER MODIFICARE L'ARRAY DI ");

        // se il chiamante vuole modificare l'array di numeri
        if(numeri){

            // finisco di stampare la plancia
            System.out.println(" NUMERI");

            // prendo l'indice dell'array da modificare
            System.out.print("Quale elemento dell'array vorresti modificare? ");
            indice = scan.nextInt();

            // se l'indice dell'array è non valido (ovvero se non ho riempito l'array fino a quell'indice) ritorno false
            if(indice >= nArray.size()) return false;

            // se l'indice è corretto chiedo il valore nuovo
            System.out.print("Inserisci il nuovo valore: ");
            intero_da_modificare = scan.nextInt();

            // modifico l'array con l'intero appena inserito da tastiera
            nArray.set(indice, intero_da_modificare);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai modificato l'elemento di posizione " + indice + " in " + intero_da_modificare + " nel vettore di interi";
            
            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica);

        } 
        // se il chiamante voleva accedere all'array di stringhe
        else {

            // finisco di stampare la plancia
            System.out.println(" STRINGHE");

            // chiedo quale elemento dell'array vuole modificare
            System.out.print("Quale elemento dell'array vorresti modificare? ");
            indice = scan.nextInt();

            // se l'indice dell'array è non valido (ovvero se non ho riempito l'array fino a quell'indice) ritorno false
            if(indice >= sArray.size()) return false;

            // se l'indice è corretto chiedo il valore nuovo
            System.out.print("Inserisci il nuovo valore: ");
            stringa_da_modificare = scan.nextLine();

            // modifico l'array con la stringa appena inserita da tastiera
            sArray.set(indice, stringa_da_modificare);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai modificato l'elemento di posizione " + indice + " in (" + stringa_da_modificare + ") nel vettore di stringhe";
            
            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica);
        }

        // se la modifica è andata a buon fine ritorno true
        return true;
    }
    
    // metodo che elimina un elemento di uno degli array dipendentemente dal valore del booleano in ingresso numeri
    boolean elimina(boolean numeri){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // dichiaro variabili di utilità
        int indice;
        String stringa_di_modifica;

        // stampo la prima parte della plancia
        System.out.println("STAI PER ELIMINARE UN ELEMENTO DELL'L'ARRAY DI ");

        // se il chiamante vuole eliminare dall'array di interi
        if(numeri){

            // finisco di stampare la plancia
            System.out.println(" NUMERI");

            // chiedo quale elemento dell'array si intende eliminare
            System.out.print("Quale elemento dell'array vorresti eliminare? ");
            indice = scan.nextInt();

            // se l'indice inserito non appartiene agli indici di elementi significativi ritorno false
            if(indice >= nArray.size()) return false;

            // rimuovo l'elemento di indice indice
            nArray.remove(indice);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai eliminato l'elemento di indice " + indice + " nel vettore di interi";

            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica);
            
        } 
        // se il chiamante vuole eliminare dall'array di stringhe
        else {

            // finisco di stampare la plancia
            System.out.println(" STRINGHE");

            // chiedo l'indice dell'elemento da eliminare da tastiera
            System.out.print("Quale elemento dell'array vorresti eliminare? ");
            indice = scan.nextInt();

            // se l'indice inserito non appartiene agli indici di elementi significativi ritorno false
            if(indice >= sArray.size()) return false;

            //rimuovo l'elemento di indice indice
            sArray.remove(indice);

            // compongo il messaggio di log
            stringa_di_modifica = "Hai eliminato l'elemento di indice " + indice + " nel vettore di stringhe";

            // inserisco il messaggio di log nell'array modifiche
            modifiche.add(stringa_di_modifica);
        }

        // se l'eliminazione è andata a buon fine ritorno true
        return true;
    }
}

class principale{
    public static void main (String[] arg){

        // istanzio uno Scanner
        Scanner scan = new Scanner(System.in);

        // istanzio un oggetto Login
        Login accesso = new Login();

        // istanzio un oggetto Data
        Dati data = new Dati();

        // finche l'utente non inserisce una coppia {username,password} giusta ripeto l'accesso
        while(true){

            // dichiaro e assegno un booleano richiamando il metodo accesso
            boolean giusto = accesso.effettua();

            // se il metodo accesso ha restituito true esco dal ciclo
            if(giusto) break;
        }       // FINE CICLO WHILE

        // stampo a video l'accesso effettuato
        System.out.println("Hai effettuato l'accesso.");

        // dichiaro variabili di utilità
        boolean numeri;
        String comando;

        // ciclo per effettuare operazioni sull'oggetto data
        while(true){

            // stampo la plancia comandi
            System.out.println("--------------- MENU ---------------");

            // chiedo il comando da tastiera
            System.out.print("Scegli su quale vettore accedere [numeri][stringhe][esci]: ");
            comando = scan.nextLine();

            // assegno false a numeri se l'utente ha inserto "stringhe" (case-insensitive), altrimenti considero "numeri"
            numeri = (comando.equalsIgnoreCase("stringhe"))? false : true;

            // se l'utente ha inserito "esci" (case insensitive) esco dal ciclo
            if(comando.equalsIgnoreCase("esci")) break;

            // prendo da tastiera il comando da compiere
            System.out.println("Scegli quale azione compiere [aggiungi][modifica][rimuovi][stampa][esci]: ");
            comando = scan.nextLine();

            // se l'utente ha inserito "aggiungi" (case-insensitive) chiamo il metodo aggiungi
            if(comando.equalsIgnoreCase("aggiungi")) data.aggiungi(numeri);

            // se l'utente ha inserito "modifica" (case-insensitive) chiamo il metodo modifica
            if(comando.equalsIgnoreCase("modifica")) data.modifica(numeri);

            // se l'utente ha inserito "rimuovi" (case-insensitive) chiamo il metodo rimuovi
            if(comando.equalsIgnoreCase("rimuovi")) data.elimina(numeri);

            // se l'utente ha inserito "stampa" (case-insensitive) chiamo il metodo stampa
            if(comando.equalsIgnoreCase("stampa")) data.stampa(numeri);

            // se l'utente ha inserito "esci" (case-insensitive) uscendo dal ciclo
            if(comando.equalsIgnoreCase("esci")) break;
        }       // FINE CICLO WHILE

        // prendo da tastiera la conferma delle modifiche
        System.out.print("Vuoi confermare le modifiche? ");
        comando = scan.nextLine();

        // se l'utente vuole modificare
        if(comando.equalsIgnoreCase("si")){

            //finche l'utente non inserisce la coppia {username,password} giusta non esco dal ciclo
            while(true){

                // assegno il booleano giusto col metodo accesso di Login
                boolean giusto = accesso.effettua();

                // se il login è giusto
                if(giusto) break;
            }

            // se l'utente ha inserito username e password giusti aggiorno i dati effettivi
            data.aggiorna();
        }
    }
}
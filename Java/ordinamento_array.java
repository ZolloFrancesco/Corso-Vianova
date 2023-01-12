import java.util.Random;

class principale{
    
    // scambia vettore[x] con vettore[y]
    static void scambia(int vettore[], int x, int y) { 
        int temp = vettore[x];      // creo elemento temporaneo
        vettore[x] = vettore[y];    // scambio 
        vettore[y] = temp;          // metto il temporaneo
    }       // fine funzione scambia
    
    // randomizza vettore
    static void randomizzaVettore(int vettore[]){
        Random ran = new Random();                  // istanzio oggetto random
        
        // riempio il vettore con numeri casuali da 0 a 50
        for(int i = 0; i < vettore.length; i++){
            vettore[i] = ran.nextInt(50);
        }
    }

    // stampa vettore
    static void stampa(int vettore[]){
        System.out.println("----------------------------------");
        for(int i = 0; i < vettore.length; i++){
            System.out.print(vettore[i] + " ");
        }
        System.out.println("");
    }

    // ordina vettore con selection sort
    static void selectionSort(int vettore[]) {

        // variabile di appoggio dove metterò il minimo per ogni ciclo
        int min;

        // per ogni elemento (tranne l'ultimo) scorro tutto l'array 
        // e vedo se è il più piccolo degli altri non ancora processati
        for (int i = 0 ; i < vettore.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < vettore.length; j++)
                if (vettore[j] < vettore[min]) min = j;
            scambia(vettore, i, min);
        }
    }

    // ordina vettore con bubble sort
    static void bubbleSort(int vettore[]) {
        for (int i = 0 ; i < vettore.length - 1; i++)
            for (int j = vettore.length - 1 ; j > i; j--)
                if (vettore[j] < vettore[j-1])
                    scambia(vettore, j, j-1); 
    }

    public static void main(String[] arg){

        int[] daOrdinare = new int[50];                             // dichiarazione del vettore

        randomizzaVettore(daOrdinare);                              // randomizzazione del vettore

        System.out.println("VETTORE RANDOMIZZATO");                 // stampo plancia
        stampa(daOrdinare);                                         // stampo vettore
        
        selectionSort(daOrdinare);                                  // ordino vettore con selection sort
        
        System.out.println("VETTORE ORDINATO CON SELECTION SORT");  // stampo plancia
        stampa(daOrdinare);                                         // stampo vettore
        
        randomizzaVettore(daOrdinare);                              // randomizzazione vettore
        
        System.out.println("VETTORE RANDOMIZZATO");                 // stampo plancia
        stampa(daOrdinare);                                         // stampo vettore
        
        bubbleSort(daOrdinare);                                     // ordino vettore con bubble sort
        
        System.out.println("VETTORE ORDINATO CON BUBBLE SORT");     // stampo plancia
        stampa(daOrdinare);                                         // stampo vettore
    }
}
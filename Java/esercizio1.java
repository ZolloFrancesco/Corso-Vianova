// valorizzare direttamente {b,123.34,1,true,false}
// sommare tutti e dividerli per 3
// stampare il risultato con "EVVIVA!" utilizzando i due booleani in condizione

class esercizio1 {
    public static void main( String[] args){
        
        // dichiarazione e assegnamento delle variabili
        double uno = (int)'b';
        double due = 123.34;
        double tre = 1;
        boolean bool1 = false, bool2 = true;

        double somma = ( uno + due + tre ) / 3;

        if (bool1 || bool2) {
            System.out.println( somma + "\nEVVIVA!" );
        }   // fine dell'if
    }       // fine del main
}           // fine della classe

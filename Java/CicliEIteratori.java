class principale{
    public static void main(String[] arg){

        System.out.println("CICLO WHILE");

        // dichiarazione contatore
        int i = 0;

        // ciclo while
        while( i < 100 ){
            System.out.println( i );

            // incremento il contatore
            i++;
        }       // fine while

        // riazzeramento contatore
        i = 0;

        System.out.println("CICLO DO-WHILE");

        do {        // ciclo do-while
            System.out.println( i );

            // incremento il contatore
            i++;
        } while ( i < 100 );        // fine do-while
    }       // fine main
}       // fine classe principale
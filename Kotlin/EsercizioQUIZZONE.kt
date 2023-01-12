// Realizzato da Francesco Zollo e Michael Bagnoli

// Creare un sistema di login per due tipologie
// UTENTE: Verrà riportato al menu per eseguire il quiz o uscire
// ADMIN: verrà riportato a un menu per: Aggiungere, Rimuovere, Visualizzare le domande e uscire

// La classe Domande rappresenta un insieme di:
// domande, risposte, e risposte giuste.
class Domande{

    // array i cui elementi rappresentano la terna {domanda,risposte,risposte giuste}
    private var question : ArrayList<DomandaRisposte> = ArrayList()

    // Metodo d'inizializzazione.
    // Chiede quante domande devono essere inserite,
    // prende da tastiera le domande e le risposte.
    // Annota anche quali sono le risposte giuste.
    fun inizializza() {

        // chiedo quante domande l'utente vuole inserire da tastiera
        println("Quante domande vuoi fare?")
        val nDomande : Int = readLine()!!.toInt()

        // ciclo che chiama la add sull'array di domande tante volte quanto è stato inserito da tastiera
        for(i in 0 until nDomande)
            question.add(DomandaRisposte())

        }       // FINE METODO inizializza

    // Metodo che permette di rispondere alle domande salvate.
    // Ritorna il punteggio del test.
    fun gioca() : Int{

        // variabile di utilità
        var punteggio = 0

        // ciclo che per ogni domanda
        for(i in 0 until question.size){

            // stampo la domanda
            question[i].stampa()

            // chiedo quale risposta l'utente ha intenzione di selezionare da tastiera
            print("La risposta giusta è la numero: ")
            val scelta : Int = readLine()!!.toInt()

            // se la risposta appena inserita è giusta aumento il punteggio di un punto
            if(question[i].rispGiuste[scelta]) punteggio++
        }

        // restituisco il punteggio ottenuto alla fine del test
        return punteggio
    }       // FINE METODO gioca

    // Metodo accessibile quando l'utente è admin.
    // Esegue ciclicamente un comando da tastiera
    fun menu() {

        // ciclo infinito da cui si esce solo con un return
        while (true){

            // stampo plancia
            println("-------------MENU-------------")

            // chiedo un comando da tastiera (se non valido inizia un nuovo ciclo)
            print("Inserisci un comando [Aggiungi, Rimuovi, Visualizza, Esci]: ")

            // in base al comando preso da tastiera e chiamo il metodo privato adeguato
            when (readLine()!!.toString()) {
                "Aggiungi", "aggiungi" -> {
                    aggiungi()
                }
                "Rimuovi", "rimuovi" -> {
                    rimuovi()
                }
                "Visualizza", "visualizza" -> {
                    stampa()
                }
                else -> {
                    return
                }       // FINE IF
            }       // FINE WHEN
        }       // FINE WHILE
    }       // FINE METODO menu

    // Metodo privato che aggiunge un elemento all'array di domande question (sfruttando il costruttore di DomandaRisposte)
    private fun aggiungi(){
        question.add(DomandaRisposte())
    }       // FINE METODO aggiungi

    // Metodo privato che stampa la terna {domanda, risposte} per ogni domanda nell'array question
    private fun stampa(){

        // ciclo tanto volte quanto è grande question
        for(i in 0 until question.size){

            // stampo plancia
            println("-----------------------QUESITO NUMERO $i-----------------------")

            // richiamo metodo stampa sull'oggetto DomandaRisposte
            question[i].stampa()
        }       // FINE FOR
    }       // FINE METODO stampa

    // Metodo privato che stampa le domande disponibili, e ne rimuove una su comando dell'utente.
    private fun rimuovi(){

        // stampo plancia
        println("---------------RIMOZIONE-----------------------")

        // stampo tutte le coppie {domande,risposte}
        stampa()

        // chiedo il numero di domanda che si vuole eliminare da tastiera
        println("Quale domanda vuoi eliminare?")
        val comando : Int = readLine()!!.toInt()

        // controllo se l'inserimento non supera la dimensione corrente dell'array
        if(comando >= question.size){

            // se la dimensione è eccessiva, ritorno al metodo menu e continua il ciclo
            return
        }

        // se il controllo è andato a buon fine rimuovo la domanda
        question.removeAt(comando)
    }       // FINE METODO rimuovi

}       // FINE CLASSE Domande


// Classe DomandaRisposte che contiene metodi e attributi che riguardano una singola domanda del quiz
class DomandaRisposte {

    // attributo che contiene la domanda
    private var domanda : String = ""

    // array di stringhe che contiene le risposte possibili
    private var risposte : ArrayList<String> = ArrayList()

    // array di bool: se ( rispGiuste[i] == true ) => risposte[i] è la risposta esatta
    var rispGiuste : ArrayList<Boolean> = ArrayList()

    // costruttore che inserisce una domanda, un insieme di risposte,
    // si annota anche le risposte giuste
    init {

        // chiedo il testo della domanda da tastiera
        println("Inserire domanda")
        this.domanda = readLine()!!.toString()

        // chiedo quante risposte ha la domanda appena inserita
        println("Inserisci numero di risposte")
        val nRisposte = readLine()!!.toInt()

        // ciclo per inserire le risposte e annotare quelle giuste
        for(i in 0 until nRisposte){
            
            // stampo plancia
            print("Scrivi la risposta n. ${i}: ")

            // aggiungo una risposta all'array risposte
            this.risposte.add(readLine()!!.toString())

            // annoto la correttezza inserendo il bool adeguato in rispGiuste
            print("E' una risposta giusta? ")
            rispGiuste.add( (( readLine()!!.toString() ) == "si") )
        }

    }

    // metodo che stampa la sequenza {domanda,risposte}
    fun stampa(){

        // stampo l'intestazione della domanda
        println("---------------- DOMANDA -----------------")

        // stampo la domanda
        println(domanda)

        // stampo l'intestazione delle risposte
        println("---------------- RISPOSTE ----------------")

        // ciclo che per ogni elemento nell'array risposte
        for(i in 0 until risposte.size){

            // stampo l'elemento i-esimo del vettore risposte
            println("$i - ${risposte[i]}")
        }

        // stampa per rappresentare la fine della stampa
        println("------------------------------------------")
    }       // FINE METODO stampa
}

// Classe Account che rappresenta un account.
class Account(usr_inserito : String , pss_inserito : String, admin_inserito : Boolean) {

    // attributo che rappresenta l'username
    var usr : String = ""

    // attributo che rappresenta la password
    var pss : String = ""

    // attributo booleano che indica se l'utente è admin
    var admin : Boolean = false

    // Costruttore che assegna tutti gli attributi dell'oggetto.
    // Prende in ingresso lo username, la password e un booleano che indica se l'utente è admin
    init {
        // assegnamento dello username
        this.usr = usr_inserito

        // assegnamento della password
        this.pss = pss_inserito

        // assegnamento dell'indicatore admin
        this.admin = admin_inserito
    }       // FINE DEL COSTRUTTORE

    // metodo che stampa le informazioni dell'oggetto di tipo Account
    fun stampa (){
        // stampo plancia
        println("---------STAMPA CREDENZIALI UTENTE---------")

        // stampo lo username
        println("lo username è: $usr")

        // stampo la password
        println(" La tua password è : $pss")

        // stampo informazioni sul carattere dell'account
        print("E' un account ")
        if(admin) println("admin")
        if(!admin) println("utente")

        // stampa che rappresenta la fine della stampa
        println("------------------------------------------")
    }       // FINE METODO stampa
}       // FINE CLASSE Account

// Classe AccountSessione in cui vengono salvati tutti gli account che si sono registrati nella sessione corrente.
class AccountSessione{

    // array di oggetti di tipo Account
    private var accountSalvati : ArrayList <Account> = ArrayList()

    // costruttore che inserisce un admin di default
    init{
        // inserimento nell'array di Account di un account admin con:
        // username = "ADMIN", password = "admin" 
        accountSalvati.add(Account("ADMIN","admin",true))
    }       // FINE COSTRUTTORE

    // metodo che stampa informazioni di tutti gli account della sessione
    fun stampa(){
        // ciclo che per ogni elemento dell'array accountSalvati
        for( i in 0 until accountSalvati.size)
            // stampa l'account i-esimo
            accountSalvati[i].stampa()
    }       // FINE METODO stampa

    // metodo che aggiunge chiede l'inserimento da tastiera dei dati di registrazione di un account,
    // che poi viene inserito nell'array accountSalvati
    fun aggiungiAccount (){

        // variabili di utility'
        var nonesiste: Boolean
        var usr: String
        val admin = false

        // ciclo infinito in cui
        while(true){

            // setto a false il booleano nonesiste (utile dal secondo ciclo in poi)
            nonesiste = true

            // chiedo l'username da tastiera
            print("Inserisci username: ")
            usr = readLine()!!.toString()

            // ciclo che per ogni elemento salvato nel vettore accountSalvati,
            // controlla l'esistenza di un account con lo stesso username.
            for(i in 0 until accountSalvati.size){

                // se il nome inserito da tastiera è uguale all'account i-esimo
                if(usr == accountSalvati[i].usr){

                    // avverto l'utente dell'esistenza di un account con nome uguale
                    println("Username già in uso, riprova")

                    // setto nonesiste a false in modo da poter uscire dal ciclo
                    nonesiste = false
                }       // FINE IF
            }       // FINE CICLO FOR

            // se l'account non esiste esco dal ciclo, altrimenti chiedo i dati nuovamente
            if(nonesiste) 
                break
        }       // FINE CICLO WHILE

        // chiedo la password da tastiera
        print("Inserisci password: ")
        val pss: String = readLine()!!.toString()


        // inserisco un elemento (quindi un account) nel vettore accountSalvati
        // inizializzandolo con i dati appena presi da tastiera
        accountSalvati.add(Account(usr,pss,admin))
    }       // FINE METODO aggiungiaccount

    // metodo privato di utility' che testa se la coppia {username, password} esiste,
    //  e nel caso restituisce true, altrimenti restituisce false.
    private fun test (usr : String, pss : String) : Boolean{

        // ciclo che per ogni elemento del vettore accountSalvati
        for(i in 0 until accountSalvati.size){
            
            // verifica se l'username e la password coincidono con i parametri in ingresso
            if((accountSalvati[i].usr == usr) && (accountSalvati[i].pss == pss)){

                // nel caso restituisce true
                return true
            }       // FINE IF
        }       // FINE CICLO FOR

        // altrimenti restituisce false
        return false
    }       // FINE METODO PRIVATO test

    // Metodo che permette di effettuare un login.
    // Restituisce true se il login ha avuto successo e l'utente è admin,
    // restituisce false se il login ha avuto successo e l'utente è utente
    fun login() : Boolean {

        // stampo una intestazione
        println("--------------LOGIN-------------")

        // dichiaro variabili di utility'
        var usr: String
        var pss : String

        // ciclo infinito
        while(true){

            // chiedo uno username da tastiera
            print("Inserire username: ")
            usr = readLine()!!.toString()

            // chiedo una password da tastiera
            print("Inserire password: ")
            pss = readLine()!!.toString()

            // se il metodo privato restituisce true,
            // significa che ho trovato una coppia {username, password} corrispondenti a quelli inseriti da tastiera
            if ( test(usr,pss) ){

                // esco dal ciclo
                break
            } else {

                // altrimenti stampo un avviso e faccio iniziare un altro ciclo
                println("Utente o password errati")
            }       // FINE IF
        }       // FINE CICLO WHILE

        // ciclo che per ogni elemento del vettore accountSalvati
        for(i in 0 until accountSalvati.size){

            // se corrispondono i dati inseriti da tastiera con quelli dell'elemento i-esimo del vettore accountSalvati
            if ((accountSalvati[i].usr == usr) && (accountSalvati[i].pss == pss)) {

                // restituisco l'admin (ovvero true)
                return accountSalvati[i].admin
            }       // FINE IF
        }       // FINE CICLO FOR

        // altrimenti restituisco false
        return false
    }       // FINE METODO login
}       // FINE CLASSE AccountSessione

// INIZIO MAIN
fun main() {

    // istanzio un oggetto di tipo AccountSessione
    val sessione = AccountSessione()

    // istanzio un oggetto di tipo Domande
    val domRisp = Domande()

    // variabile di utilita'
    var punteggio: Int

    // stampo una intestazione
    println("---------ACCESSO---------")

    // ciclo infinito
    while(true){

        // chiedo da tastiera se l'utente ha un account
        println("Hai già un account? ")
        val login : String = readLine()!!.toString()

        // se l'utente non ha un account
        if(login == "no"){

            // chiamo il metodo aggiungiAccount che mi permette di aggiungerne uno
            // (tutte le stampe sono delegate al metodo)
            sessione.aggiungiAccount()
        }

        // altrimenti se l'utente ha gia' un account
        else if(login == "si"){

            // effettuo il login e mi faccio restituire 
            // true se è stato effettuato un accesso di tipo admin,
            // false se è stato effettuato l'accesso di tipo utente
            val admin = sessione.login()

            // se l'accesso è di tipo admin
            if(admin) {

                // chiamo metodo inizializza che si occupa di far inserire
                // quante volte si vuole la terna {domanda,risposte,risposte giuste}
                domRisp.inizializza()

                // chiamo il metodo menu che si occupa di gestire le operazioni che puo' fare un admin
                domRisp.menu()

            }
            
            // se l'utente è di tipo utente
            else{

                // chiamo il metodo gioca che permette di rispondere a tutte le domande salvate e
                // restituisce il punteggio che il giocatore ha conseguito
                punteggio = domRisp.gioca()

                // stampo il risultato
                println("Il tuo punteggio è di: $punteggio")
            }       // FINE IF
        }

        // se l'utente ha inserito qualcosa di diverso da "si" e "no", stampo un avvertimento e ricomincio il ciclo
        else println("Input non valido")
    }       // FINE CICLO WHILE
}       // FINE MAIN
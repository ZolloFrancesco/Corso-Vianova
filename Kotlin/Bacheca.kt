/*
-TRE FASI( Login, Creazione Utente o Admin, Gestione azioni Bacheca)

-Login:Creare e poter accedere a un profilo utente o admin

-Utente:Pubblicare annunci sulla Bacheca, commentare e comprare e fare logout dell'account

-Admin:Eliminare annunci, modificare annunci, stampare la lista utenti, eliminare utenti e fare logout

il sistema dopo aver eseguito un clock completo(una fuzione più end) dovrà riportare alla schermata di login.
*/

import kotlin.collections.ArrayList

// serve a registrarsi come admin
const val pss_di_sistema = "123456"

/*
Classe che rappresenta un singolo account.
Contiene informazioni come username, password e un booleano che indica se l'utente è admin
*/
class Account(var usr: String, private var pss: String, var admin: Boolean) {

    // metodo che restituisce true se l'utente ha username usr e password pss
    fun login(usr : String, pss : String) : Boolean{

        // se username e password corrispondono restituisco true
        if( usr == this.usr && pss == this.pss){
            return true
        }

        // altrimenti restituisco false
        return false
    }       /* FINE METODO login() */
}       /* FINE CLASSE Account */

/* classe che rappresenta un insieme di utenti */
class Utenza{

    // array di oggetti Account
    private var utente : ArrayList<Account> = ArrayList()

    // metodo che stampa tutti gli utenti nell'array utente
    fun stampa(){

        println("")

        // per ogni utente nell'array utente
        for(i in 0 until utente.size){

            // variabile di utilità
            val tipo : String

            // salvo il tipo di utente come stringa (serve per la stampa)
            if(utente[i].admin) tipo = "admin"
            else tipo = "utente"

            // stampo l'utente
            println("Utente $i -> ${utente[i].usr} -> $tipo")
        }       /* FINE CICLO FOR */
    }       /* FINE METODO stampa() */

    // Metodo che permette di effettuare il login, utilizza i metodi login o registrazione.
    // Restituisce un bool che rappresenta se l'utente è admin oppure no
    fun loginIniziale(): Boolean {

        // ciclo infinito
        while (true){

            // chiedo un comando da tastiera
            print("Vuoi loggarti o registrarti? [login][registrazione]: ")
            val comando = readLine()!!.toString()

            // variabile di utilità
            val admin: Boolean

            // se la volontà dell'utente è loggarsi
            if(comando=="login"){

                // chiamo il metodo login() che restituisce la tipologia di utente: true è di tipo admin
                admin = login()

                // restituisce il risultato di login()
                return admin
            }       /* FINE IF */

            // se la volontà dell'utente è registrarsi
            if(comando=="registrazione"){

                // chiamo il metodo registrazione() che permette di registrarsi
                registrazione()

                // in seguito a una registrazione chiamo il metodo login() per fare l'accesso
                admin = login()

                // restituisco il risultato di login()
                return admin
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO loginIniziale() */

    // Metodo che permette di effettuare il login.
    // Restituisce true se l'utente che ha effettuato il login è admin, false se è utente (ordinario)
    private fun login() : Boolean {

        // se non ci sono utenti
        if(utente.isEmpty()){

            // stampo un avviso
            println("Non ci sono utenti.")

            // richiamo metodo per registrarsi
            registrazione()
        }

        // variabile di utilità
        var admin = false

        // ciclo infinito
        while (true){

            // stampo plancia
            println("\n------LOGIN-----")

            // richiedo username da tastiera
            print("Inserisci username: ")
            val usr : String = readLine()!!.toString()

            // richiedo password da tastiera
            print("Inserisci password: ")
            val pss : String = readLine()!!.toString()

            // variabile di utilità
            var giusto = false

            // per ogni utente
            for(i in 0 until utente.size){

                // se il metodo login() (della classe Account, non Utenza!) è andato a buon fine.
                if(utente[i].login(usr,pss)){

                    // indica che ho trovato un account con quello username e quella password
                    giusto = true

                    // salvo la tipologia dell'utente per poterla restituire
                    admin = utente[i].admin
                }       /* FINE IF */
            }       /* FINE CICLO FOR */

            // se ho trovato una corrispondenza {username,password}, esco dal ciclo perchè la coppia era corretta
            if(giusto) break
        }       /*FINE WHILE */

        // restituisco la tipologia di utente che ha effettuato il login
        return admin
    }       /* FINE METODO login() */

    // metodo di utilità che restituisce true se esiste un utente con username usr
    private fun esiste(usr : String) : Boolean{

        // per ogni utente
        for(i in 0 until utente.size){

            // se username usr corrisponde, restituisco true
            if(utente[i].usr == usr) return true
        }       // FINE CICLO FOR

        // altrimenti restituisco false
        return false
    }       /* FINE METODO esiste() */

    // metodo che permette la registrazione di un utente.
    private fun registrazione(){

        // ciclo infinito
        while (true){

            // stampo plancia
            println("\n------REGISTRAZIONE-----")

            // richiedo username da tastiera
            print("Inserisci username: ")
            val usr : String = readLine()!!.toString()

            // richiedo password da tastiera
            print("Inserisci password: ")
            val pss : String = readLine()!!.toString()

            // se una corrispondenza {username, password} ricomincio il ciclo
            if(esiste(usr)) continue

            // altrimenti chiedo se si vuole registrare come admin
            print("Sei admin? ")
            val scelta = readLine()!!.toString()

            // se si vuole registrare come admin
            if(scelta == "si" || scelta == "Si"){

                // ciclo infinito
                while (true){

                    // chiedo la password di sistema da tastiera
                    print("Inserire password di sistema: ")
                    val pasSis = readLine()!!.toString()

                    // se la password di sistema è corretta
                    if(pasSis == pss_di_sistema){

                        // inserisco un oggetto Account admin nell'array utente
                        utente.add(Account(usr,pss,true))

                        // stampo conferma e ritorno
                        println("Registrazione Admin avvenuta con successo")
                        return
                    }       /* FINE IF */
                }       /* FINE CICLO WHILE */
            }
            // altrimenti se l'utente non vuole registrarsi come admin
            else{

                // inserisco un oggetto Account utente (ordinario) nell'array utente
                utente.add(Account(usr,pss,false))

                // stampo conferma e ritorno
                println("Registrazione Utente avvenuta con successo")
                return
            }       /* FINE ELSE */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO registrazione() */

    // metodo che chiede quale utente si vuole eliminare e lo elimina
    fun eliminaUtente() {

        // stampo la lista degli utenti
        stampa()

        // ciclo infinito
        while (true){

            // chiedo quale utente si vuole eliminare da tastiera
            print("Quale utente vuoi eliminare? ")
            val indice = readLine()!!.toInt()

            // se l'utente inserito esiste
            if(indice < utente.size){

                // rimuovo l'utente
                utente.removeAt(indice)
                return
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO eliminaUtente() */
}       /* FINE CLASSE Utenza */

/*
Classe che rappresenta un singolo Annuncio.
Contiene informazioni come il testo dell'annuncio e un array di commenti di tipo stringa
*/
class Annuncio{

    var testoAnnuncio : String = ""

    private var commenti : ArrayList<String> = ArrayList()


    // inizializzatore che inizializza un annuncio solo con il testo
    // (chi lo scrive solitamente non lo commenta, ma può farlo in seguito)
    init{

        // chiedo il testo e lo assegno a testoAnnuncio
        print("Scrivi il testo dell'annuncio: ")
        testoAnnuncio = readLine()!!.toString()
    }       /* FINE METODO init() */

    // metodo che stampa un singolo annuncio
    fun stampa(){

        // stampo il testo dell'annuncio
        println("-----Testo-----")
        println(testoAnnuncio)

        // stampo l'intestazione dei commenti
        println("-----Commenti-----")

        // se non ci sono commenti
        if (commenti.isEmpty()){

            // informo l'utente e ritorno
            println("Non ci sono commenti.")
            return
        }       /* FINE IF */

        // per ogni commento
        for (i in 0 until commenti.size){

            // stampo il commento
            print("-- commento $i --> ")
            println(commenti[i])
        }       /* FINE CICLO FOR */

        // vado a capo (rende la stampa su terminale più leggibile
        println("\n")
    }       /* FINE METODO stampa() */

    // Metodo di utilità che stampa solo i commenti.
    // Richiamato quando si vuole eliminare un commento e non si vuole stampare di nuovo l'intero annuncio.
    private fun stampaCommenti(){

        // se non ci sono commenti
        if (commenti.isEmpty()){

            // stampo una plancia
            // (la stampo anche se non ci sono commenti perchè rende più leggibile il terminale)
            println("-----Commenti-----")

            // informo l'utente e ritorno
            println("Non ci sono commenti.")
            return
        }       /* FINE IF */

        // per ogni commento
        for (j in 0 until commenti.size){

            // stampo il numero del commento e il testo
            print("-- commento $j -->")
            println(commenti[j])
        }       /* FINE CICLO FOR */
    }       /* FINE METODO stampaCommenti() */

    // metodo che permette l'inserimento di un commento
    fun aggiungiCommento(){

        // chiedo il commento da tastiera
        print("Inserisci commento: ")
        val com = readLine()!!.toString()

        // aggiungo il commento all'array di commenti
        commenti.add(com)
    }       /* FINE METODO aggiungiCommenti() */

    //metodo che permette di rimuovere un commento
    fun rimuoviCommento(){

        // chiamo il metodo di utilità che stampa solo commenti
        stampaCommenti()

        // se non ci sono commenti
        if(commenti.isEmpty()){

            // ritorno senza informare da terminale, perchè la stampaCommenti() avverte già
            return
        }       /* FINE IF */

        // variabile di utilità
        var indice : Int

        // ciclo infinito
        while (true){

            // chiedo quale commento si vuole eliminare da tastiera
            print("Quale commento vuoi rimuovere? ")
            indice = readLine()!!.toInt()

            // se l'indice inserito è di un commento esistente esco dal ciclo infinito
            if(indice < commenti.size) break
        }       /* FINE CICLO WHILE */

        // rimuovo il commento dall'array di commenti
        commenti.removeAt(indice)
    }       /* FINE METODO rimuoviCommenti() */
}       /* FINE CLASSE Annuncio() */


/* classe che rappresenta un insieme di annunci. */
class Bacheca{
    private var annuncio : ArrayList<Annuncio> = ArrayList()

    // metodo che stampa tutti gli annunci e i relativi commenti
    private fun stampa(){

        // vado a capo (solo per leggibilità)
        println("\n")

        // per ogni annuncio
        for(i in 0 until annuncio.size){

            //stampo un'intestazione
            println("---------Annuncio  $i")

            // richiamo il metodo stampa() (metodo della classe Annuncio)
            annuncio[i].stampa()
        }       /* FINE FOR */
    }       /* FINE METODO stampa() */

    // metodo che permette di pubblicare un annuncio
    private fun pubblicaAnnuncio(){

        // Inserisco semplicemente nell'array annuncio.
        // Non mi serve fare altro, perchè verrà chiamata la init() della classe Annuncio().
        // la init() chiederà solo il testo da tastiera e ritornerà un oggetto Annuncio senza commenti,
        // che verrà inserito dalla add().
        annuncio.add(Annuncio())
    }       /* FINE METODO pubblicaAnnuncio() */

    // metodo che permette di commentare un annuncio
    private fun commenta(){

        // stampo l'intera bacheca
        stampa()

        // vado a capo solo per leggibilità del terminale
        println("\n")

        // variabile di utilità
        var i : Int

        // ciclo infinito
        while (true){

            // chiedo il numero di annuncio che si vuole commentare da tastiera
            print("Quale annuncio vuoi commentare? ")
            i = readLine()!!.toInt()

            // esco dal ciclo se è valido
            if(i < annuncio.size) break
        }       /* FINE CICLO WHILE */

        // richiamo metodo aggiungiCommento(), che chiederà il testo e lo inserirà nell'apposito array di stringhe
        annuncio[i].aggiungiCommento()
    }       /* FINE METODO commenta() */

    // metodo che permette di comprare un oggetto, che equivale a eliminarne l'annuncio
    private fun compra(){

        // stampo l'intera bacheca
        stampa()

        // vado a capo solo per leggibilità del terminale
        println("\n")

        // ciclo infinito
        while (true){

            // chiedo quale oggetto si vuole comprare da tastiera
            print("Quale oggetto vuoi comprare? ")
            val indice = readLine()!!.toInt()

            // se l'utente ha inserito un annuncio esistente
            if(indice<annuncio.size){

                // rimuovo l'annuncio corrispondente e esco dal ciclo
                annuncio.removeAt(indice)
                break
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO compra() */

    // metodo che permette di eseguire una(!) azione all'utente ordinario
    fun menuUtente(){

        // stampo la plancia comandi e chiedo il comando da tastiera
        print("Scrivi un comando [pubblica][commenta][compra][logout]: ")
        val comando = readLine()!!.toString()

        // dipendentemente dalla volontà dell'utente, richiamo il metodo corrispondente
        if(comando == "pubblica" || comando == "Pubblica" || comando == "PUBBLICA") pubblicaAnnuncio()
        if(comando == "commenta") commenta()
        if(comando == "compra") compra()

        // nel caso in cui si vuole fare logout, mi basta ritornare perchè la logica dell'applicativo mi impone di
        // fare il login per ogni azione che io voglia fare, sia come utente ordinario che come admin
        if(comando == "logout") return
    }       /* FINE METODO menuUtente() */

    // Metodo che permette di eseguire una(!) Azione all'utente admin.
    // Prende in ingresso una utenza perchè l'admin può anche bannare e stampare gli utenti, che altrimenti non potrebbe reperire
    fun menuAdmin(utenza: Utenza){

        // stampo la plancia comandi e chiedo il comando da tastiera
        print("Scrivi un comando [elimina annuncio][modifica annuncio][stampa Utenti][elimina commento][banna utente][logout]: ")
        val comando = readLine()!!.toString()

        // dipendentemente dalla volontà dell'utente, richiamo il metodo corrispondente
        if(comando == "elimina annuncio") eliminaAnnuncio()
        if(comando == "modifica annuncio") modificaAnnuncio()
        if(comando == "stampa utenti") utenza.stampa()
        if(comando == "elimina commento") eliminaCommento()
        if(comando == "banna utente") utenza.eliminaUtente()

        // nel caso in cui si vuole fare logout, mi basta ritornare perché la logica dell'applicativo mi impone di
        // fare il login per ogni azione che io voglia fare, sia come utente ordinario che come admin
        if(comando == "logout") return
    }       /* FINE METODO menuAdmin() */

    // metodo che permette la rimozione di un commento
    private fun eliminaCommento() {

        // stampo l'intera bacheca
        stampa()

        // vado a capo solo per leggibilità del terminale
        println("\n")

        // ciclo infinito
        while (true){

            // chiedo da tastiera di quale annuncio si vuole eliminare il commento
            print("Di quale annuncio vuoi eliminare il commento? ")
            val ann = readLine()!!.toInt()

            // se l'annuncio inserito esiste
            if (ann<annuncio.size){

                // richiamo il metodo rimuoviCommento() della classe Annuncio ed esco dal ciclo
                annuncio[ann].rimuoviCommento()
                break
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO eliminaCommento() */

    // metodo che permette di eliminare un intero annuncio
    private fun eliminaAnnuncio(){

        // stampo l'intera bacheca
        stampa()

        // vado a capo solo per leggibilità del terminale
        println("\n")

        // se non ci sono annunci in bacheca
        if(annuncio.isEmpty()){

            // stampo un messaggio informativo e ritorno
            println("La bacheca è vuota.")
            return
        }       // FINE IF

        // ciclo infinito
        while (true){

            // chiedo da tastiera quale annuncio si vuole eliminare
            print("Quale annuncio vuoi eliminare? ")
            val comando = readLine()!!.toInt()

            // se l'annuncio inserito è esistente
            if (comando < annuncio.size){

                // rimuovo l'annuncio ed esco dal ciclo
                annuncio.removeAt(comando)
                break
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */

        // stampo messaggio informativo
        println("Annuncio eliminato.")
    }       /* FINE METODO eliminaAnnuncio() */

    // metodo che permette di modificare il testo di un annuncio (sovrascrivendo il precedente)
    private fun modificaAnnuncio(){

        // stampo l'intera plancia
        stampa()

        // se non ci sono annunci in bacheca
        if(annuncio.isEmpty()){

            // stampo in messaggio informativo e ritorno
            println("La bacheca è vuota.")
            return
        }       /* FINE IF */

        // ciclo infinito
        while (true){

            // chiedo da tastiera di quale annuncio si vuole modificare il testo
            print("Quale annuncio vuoi modificare? ")
            val comando = readLine()!!.toInt()

            // se l'annuncio inserito esiste
            if (comando < annuncio.size){

                // chiedo il testo da tastiera
                print("Inserisci il testo: ")
                val nuovo = readLine()!!.toString()

                // sovrascrivo il vecchio testo dell'annuncio con quello nuovo ed esco dal ciclo
                annuncio[comando].testoAnnuncio = nuovo
                break
            }       /* FINE IF */
        }       /* FINE CICLO WHILE */
    }       /* FINE METODO modificaAnnuncio() */

}       /* FINE CLASSE Bacheca() */

/* MAIN */
fun main() {

    // istanzio un oggetto di tipo Utenza
    val u = Utenza()

    // istanzio un oggetto di tipo Bacheca
    val bac = Bacheca()

    // ciclo infinito
    // la logica dell'applicativo mi impone di effettuare il login per ogni azione che voglio fare nella bacheca
    while (true){

        // Effettuo il login.
        // Il metodo loginIniziale() chiede se si vuole registrarsi oppure no e si comporta di conseguenza.
        // Se l'utente chiede di loggarsi ma non ci sono utenti registrati, automaticamente farà effettuare una registrazione.
        // Admin conterrà true se l'utente che alla fine ha fatto login è admin, false se è utente ordinario.
        val admin = u.loginIniziale()

        // se l'utente è un admin, richiamo il metodo menuAdmin(),
        // che permette di scegliere un comando e portarlo a termine in autonomia.
        if(admin) bac.menuAdmin(u)

        // altrimenti se l'utente è un admin, richiamo il metodo menuUtente(),
        // che permette di scegliere un comando e portarlo a termine in autonomia.
        else bac.menuUtente()
    }       /* FINE CICLO WHILE */
}       /* FINE MAIN */
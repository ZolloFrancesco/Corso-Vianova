package com.example.listatodo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listatodo.database.ToDoDatabaseHandler
import com.example.listatodo.recycler.ToDo
import com.example.listatodo.recycler.ToDoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cardview.*
import kotlinx.android.synthetic.main.layout_conferma_uscita.*
import kotlinx.android.synthetic.main.popup_calendario.view.*
import kotlinx.android.synthetic.main.popup_inserimento.*
import kotlinx.android.synthetic.main.popup_inserimento.view.*
import kotlin.system.exitProcess


/*
* ELEMENTI BASE

x Deve partire e far inserire le task e dare la possibilità di segnarle come completate
x Deve avere un sistema CRUD isolato ( richiamato da funzione )
x Le task devono essere integrate con tutte le funzioni crud funzionanti su le medesime
x Il sistema si deve chiudere previo conferma


* FEATURE

-Report in vari formati ( EASY txt, json ) { far si che si possa esportare la to do list in vari formati ]
-Composizione di pacchetti dati  ( EASY ) { è legato col login ed ha possibilità di andare ad utilizzare i dati privati dell'utente, come la password, senza conoscerli in maniera diretta}
-Elementi della lista DO a step ( EASY ) { far si che si possono creare anche task che abbiamo più di una fase }
xIntroduzione elementi grafici(EASY oppure MEDIUM con elementi animati) { Introduzione della grafica minima indispensabile atta alle funzionalità o elementi di contorno funzionali animati  }
-Rendere possibile avere gruppi di to do list nominativi( EASY ){ far si che si possa dal menu iniziale scegliere se e a quale gruppo di to-do-list accedere dopo aver fatto visualizzare i nomi se esistenti delle liste di to-do  }
-Impostazioni funzionanti ( MEDIUM ){ Permettere tramite un input di poter accedere ad alcune impostazioni es: colore sfondo, numero task a vista  }
-Login system funzionante ( MEDIUM ) {possibilità di recuperare la password, nome unico e richiesta dell'email anch'essa con obbligo di unicità}
-Test unitario delle funzioni CRUD o test della UI ( HARD ) { Un sistema molto delicato ma che può rivelarsi molto utile in ottica di strutturazione della mentalità di programmazione  }
-Collegamento parziale DB  ( HARD ){ una sfida per chi vuole osare }
-Possibilità di introduzione di elementi secondari concordati(JOLLY)
* */

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // nascondo la ActionBarper estetica
        // supportActionBar?.hide()

        // dichiaro un handler per database per eseguire le operazioni messe a disposizione
        val dbHandler = ToDoDatabaseHandler(this)

        // dichiaro un array di task
        val todoListItem = ArrayList<ToDo>()

        // dichiaro un adapter che gestisce la visualizzazione di todoListItem
        val adapter = ToDoAdapter(todoListItem, this)
        recyclerView.adapter = adapter

        // dichiaro un LinearLayoutManager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // helper che permette di intercettare uno swipe
        ItemTouchHelper(

            // oggetto che intercetta sia gli swipe verso destra che quelli verso sinistra
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                // non sovrascrivo la funzione onMove()
                override fun onMove(rw: RecyclerView, vh: RecyclerView.ViewHolder, trg: RecyclerView.ViewHolder) = false

                override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {

                    // se c'è stato uno swipe verso sinistra
                    if (dir == ItemTouchHelper.LEFT) {

                        // chiamo il metodo che prende come argomento l'indice dell'elemento
                        // che verrà eliminato dal viewRecycler (aggiorna anche il database)
                        adapter.cancellaByPosition(vh.adapterPosition)

                    }
                    // altrimenti
                    else {

                        // chiamo il metodo che prende come argomento l'indice dell'elemento
                        // che verrà modificato dal viewRecycler (aggiorna anche il database)
                        adapter.modificaToDoByPosition(vh.adapterPosition)
                    }
                }
            }

        // il touchHelper lavora sulla recyclerView
        ).attachToRecyclerView(recyclerView)


        // listener per il pulsante flottante
        // btn_aggiungi_float.setOnClickListener {

            // passo alla pagina di inserimento di un nuovo task
            // startActivity(Intent(this@MainActivity, AggiungiToDo::class.java))

            // IMPLEMENTAZIONE POPUP

            /*
            var dialogBuilder: AlertDialog.Builder?
            var dialog: AlertDialog?
            var view = LayoutInflater.from(this).inflate(R.layout.popup_inserimento, null, false)

            var testoInserito = view.inserisci_popup
            var btnInserisciPopup = view.btn_inserisci_popup

            dialogBuilder = AlertDialog.Builder(this).setView(view)
            dialog = dialogBuilder!!.create()
            dialog.show()
            btnInserisciPopup.setOnClickListener {
                if (!TextUtils.isEmpty(testoInserito.text.toString())) {
                    var todo = ToDo()
                    todo.testo = testoInserito.text.toString()
                    todo.completato = false
                    todo.preferito = false

                    dbHandler.createToDo(todo)
                    adapter!!.notifyItemInserted(adapter!!.list.size)
                    dialog!!.dismiss()

                }
            }
            * */
        // }

        // lista che contiene tutti i task nel database
        val todoList = dbHandler.leggiTutto()

        // per ogni elemento dell'array todoList
        for (i in todoList.iterator()) {

            // istanzio un nuovo task
            val todo = ToDo()

            // lo riempio con l'elemento i-esimo di todoList
            todo.testo = i.testo
            todo.id = i.id
            todo.completato = i.completato
            todo.preferito = i.preferito
            todo.dataScadenza = i.dataScadenza

            // aggiungo il nuovo task alla lista che viene visualizzata nel recyclerView
            todoListItem.add(todo)

            // Stampo sul log per debugging
            Log.d("Lista", i.testo.toString())
        }

        /*
        // IMPLEMENTAZIONE DEL PULSANTE PREFERITO E COMPLETATO (DA FARE)
        completata.setOnClickListener{
         chiamare la settaCompletato passando la posizione dell'elemento in cui ho cliccato il radio button
        }

        preferito.setOnClickListener{
            if(preferito.isChecked){
                chiamare la settaPreferito passando la posizione dell'elemento in cui ho cliccato il radio button
            }
            if(!preferito.isChecked){
                chiamare la settaPreferito passando la posizione dell'elemento in cui ho cliccato il radio button
            }
        }
         */

        // listener del pulsante esporta, per esportare in txt la lista dei task

        /*
        btn_esporta_float.setOnClickListener {

            // dichiaro un array che contiene tutte le task presenti all'interno del database
            val listaToDo = dbHandler.leggiTutto()

            // oggetto che rappresenta il file che verrà scritto
            val stampato = openFileOutput("todolist.txt", MODE_PRIVATE)

            // oggetto che rappresenta uno scrittore per l'oggetto che verrà scritto
            val outputWriter = OutputStreamWriter(stampato)

            // scrivo una intestazione
            outputWriter.write("------------TO DO LIST-------------\n")

            // per ogni elemento dell'array listaToDo
            for (i in 0 until listaToDo.size) {

                // scrivo una intestazione
                outputWriter.write("Task $i: \n ")

                // scrivo il testo della task
                outputWriter.write("Testo: ${listaToDo[i].testo}")

                // scrivo una intestazione
                outputWriter.write("Preferito: ")

                // se è un task preferito, scrivo si
                if (listaToDo[i].preferito) outputWriter.write("si\n")

                // se non è un task preferito, scrivo no
                else outputWriter.write("no\n")

                // scrivo una intestazione
                outputWriter.write("Completato: ")

                // se è un task completato, scrivo si
                if (listaToDo[i].completato) outputWriter.write("si\n")

                // se non è un task completato, scrivo no
                else outputWriter.write("no\n")

                // vado a capo
                outputWriter.write("\n")
            }

            // chiudo lo stream sul file
            outputWriter.close()

            // informo l'utente del salvataggio avvenuto con successo
            Toast.makeText(baseContext, "File salvato con successo!", Toast.LENGTH_SHORT).show()

        }
         */
    }

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean{
        menuInflater.inflate(R.menu.top_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.btn_aggiungi_menu_top){
            Log.d("PREMUTO TASTO MENU","RILEVATO")
            createPopupDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    fun createPopupDialog(){

        val nuovo = ToDo()


        val view_menu = layoutInflater.inflate(R.layout.popup_inserimento, null)
        val btnSalva = view_menu.btn_inserisci_popup
        val testoTodo = view_menu.inserisci_popup
        val btnData = view_menu.btn_data_popup

        val view_calendario = layoutInflater.inflate(R.layout.popup_calendario, null)
        val calendario = view_calendario.popup_calendario


        val dialogbuild : AlertDialog.Builder? = AlertDialog.Builder(this).setView(view_calendario)
        val popup_data: AlertDialog? = dialogbuild!!.create()

        val dialogBuilder : AlertDialog.Builder? = AlertDialog.Builder(this).setView(view_menu)
        val popup_menu: AlertDialog? = dialogBuilder!!.create()

        popup_menu!!.show()

        btnSalva.setOnClickListener{
            if(!TextUtils.isEmpty(testoTodo.text)){
                nuovo.testo = testoTodo.text.toString()
                nuovo.preferito = false
                nuovo.completato = false

                if (nuovo.dataScadenza == null){
                    nuovo.dataScadenza = ""
                }

                val dbHandler = ToDoDatabaseHandler(this)
                dbHandler.inserisciToDo(nuovo)

                popup_menu.dismiss()

                startActivity(Intent(this, MainActivity::class.java))

                finish()
            }
        }

        btnData.setOnClickListener {
            val dbHandler = ToDoDatabaseHandler(this)
            popup_data!!.show()

            calendario.setOnDateChangeListener { _, anno, mese, giorno ->
                nuovo.dataScadenza = formattaData(anno,mese,giorno)
                popup_data!!.dismiss()
            }
        }
    }

    fun formattaData(anno: Int, mesevecchio: Int, giorno: Int): String {

        var m_ris : String = ""
        val mese = mesevecchio + 1
        if(mese == 1) m_ris = "Gen"
        if(mese == 2) m_ris = "Feb"
        if(mese == 3) m_ris = "Mar"
        if(mese == 4) m_ris = "Apr"
        if(mese == 5) m_ris = "Mag"
        if(mese == 6) m_ris = "Giu"
        if(mese == 7) m_ris = "Lug"
        if(mese == 8) m_ris = "Ago"
        if(mese == 9) m_ris = "Set"
        if(mese == 10) m_ris = "Ott"
        if(mese == 11) m_ris = "Nov"
        if(mese == 12) m_ris = "Dic"

        return "$giorno $m_ris"
    }

    override fun onBackPressed() {
        val dialogBuilder: AlertDialog.Builder?
        val view = LayoutInflater.from(this).inflate(R.layout.layout_conferma_uscita, null, false)

        dialogBuilder = AlertDialog.Builder(this).setView(view)
        val dialog = dialogBuilder!!.create()
        dialog!!.show()

        btn_conferma.setOnClickListener {
            exitProcess(0)
        }

        btn_cancella.setOnClickListener {
            dialog.dismiss()
        }
    }
}


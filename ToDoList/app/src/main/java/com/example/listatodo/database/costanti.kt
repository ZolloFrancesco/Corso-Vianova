package com.example.listatodo.database

// versione del database: da incrementare ogni volta che si aggiorna il database (attributi,tabelle)
const val DATABASE_VERSION : Int = 9

// nome del database
const val DATABASE_NAME : String = "todo.db"

// nome della tabella in cui salvo informazioni riguardanti i task
const val TABLE_NAME : String = "todo"

// nome attributo chiave
const val KEY_ID : String = "id"

// nome attributo che identifica il testo del task
const val KEY_TODO_NAME : String = "testo"

// nome attributo che identifica la data di inserimento del task
const val TIME : String = "dataInserimento"

// nome attributo che identifica se il task è preferito
const val PREFERITO : String = "preferito"

// nome attributo che identifica se il task è completato
const val COMPLETATO : String = "completato"
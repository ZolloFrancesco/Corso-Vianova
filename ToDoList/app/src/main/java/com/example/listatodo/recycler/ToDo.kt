package com.example.listatodo.recycler

class ToDo() {
    var id : Int? = null
    var testo : String? = null
    var preferito : Boolean = false
    var completato : Boolean = false
    var dataScadenza : String = ""

    constructor(id: Int, nome: String, pref: Boolean, completato : Boolean, data : String) : this() {
        this.id = id
        this.testo = nome
        this.preferito = pref
        this.completato = completato
        this.dataScadenza = data
    }
}
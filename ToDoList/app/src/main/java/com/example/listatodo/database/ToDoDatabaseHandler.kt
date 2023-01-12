package com.example.listatodo.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.listatodo.recycler.ToDo

class ToDoDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null /* cursore */, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TODO_TABLE = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_TODO_NAME TEXT,  $PREFERITO BOOLEAN, $COMPLETATO BOOLEAN, $TIME TEXT)"
        db?.execSQL(CREATE_TODO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // CRUD - Create, Read, Update, Delete
    fun inserisciToDo(todo : ToDo){

        val db : SQLiteDatabase = writableDatabase

        val values = ContentValues()
        values.put(KEY_TODO_NAME,todo.testo)
        values.put(KEY_ID, todo.id)
        values.put(PREFERITO,todo.preferito)
        values.put(COMPLETATO,todo.completato)
        values.put(TIME,todo.dataScadenza)
        db.insert(TABLE_NAME, null, values)

        Log.d("DATI INSERITI","SUCCESS")
    }

    @SuppressLint("Range", "Recycle")
    fun readTodo() : ToDo{

        val db : SQLiteDatabase = readableDatabase

        /*
        * SECONDO ARGOMENTO = ARRAY DI STRINGHE -> INSERIAMO LE COLONNE CHE VOLGIAMO ALAIZZARE (NULL=TUTTE LE COLONNE)
        * TERZO ARGOMENTO = STRINGA -> ID DELLA RIGA CHE VOGLIO ANALIZZARE  (CON IL COMANDO "=?" PESCO L'ARGOMENTO DELLA MIA FUNZIONE ES: READATODO(ARGOMENTO)
        * QUARTO ARGOMENTO = ARRAY DI STRINGHE-> MI FACCIO RETITUIRE TUTTI GLI ELEMENTI RICHIESTI (COLONNE) DALL'ID SELEZIONATO
        * QUINTO ARGOMENTO = STRINGA -> UN FILTRO CHE MI DICHIARA COME RAGGRUPPARE LE RICHE (NON NECESSARIO PUO' ESSERE NULL)
        * SESTO ARGOMENTO = STRINGA -> RICHIEDO QUALE GRUPPO DI RIGHE (SE SONO RAGGIUPPATE) PRENDERE (NON NECESSARIO, PUO' ESSERE NULL)
        * SETTIMO ARGOMENTO = STRINGA -> COME ORDINARE LE RIGHE, SE NULL HO L'ORGDINE DI DEFAULT
        * OTTAVO ARGOMENTO = IMPOSTARE IL LIMITE DELLE RIGHE DA RESTITUIRCI
        */
        val cur : Cursor = db.query(TABLE_NAME, arrayOf(KEY_ID, KEY_TODO_NAME), "$KEY_ID=?", null, null , null , null, null)

        val todo = ToDo()

        cur.moveToFirst()

        todo.testo = cur.getString(cur.getColumnIndex(KEY_TODO_NAME))
        todo.dataScadenza = cur.getString(cur.getColumnIndex(TIME))

        Log.d("Cursore fa fetch","SUCCESS")

        return todo
    }

    fun updateToDo(todo: ToDo): Int {

        val db: SQLiteDatabase = writableDatabase

        val values = ContentValues()
        values.put(KEY_TODO_NAME, todo.testo)
        values.put(PREFERITO, todo.preferito)
        values.put(TIME, todo.dataScadenza)

        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(todo.id.toString()))
    }

    fun deleteToDo(id : Int){
        val db : SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
    }

    @SuppressLint("Range", "Recycle")
    fun leggiTutto() : ArrayList<ToDo>{
        val db : SQLiteDatabase = writableDatabase
        val list : ArrayList<ToDo> = ArrayList()
        val comando = "SELECT * FROM $TABLE_NAME"
        val cur : Cursor = db.rawQuery(comando,null)
        if(cur.moveToFirst()){
            do {
                val todo = ToDo()
                todo.id = cur.getInt(cur.getColumnIndex(KEY_ID))
                todo.testo = cur.getString(cur.getColumnIndex(KEY_TODO_NAME))
                todo.completato = cur.getString(cur.getColumnIndex(COMPLETATO)).toBoolean()
                todo.preferito = cur.getString(cur.getColumnIndex(PREFERITO)).toBoolean()
                todo.dataScadenza = cur.getString(cur.getColumnIndex(TIME))

                list.add(todo)
            } while(cur.moveToNext())
        }
        return list
    }

    @SuppressLint("Recycle")
    fun vuoto(): Boolean {
        val db : SQLiteDatabase = writableDatabase
        val comando = "SELECT * FROM $TABLE_NAME"
        val cur : Cursor = db.rawQuery(comando,null)
        return !cur.moveToFirst()
    }
}
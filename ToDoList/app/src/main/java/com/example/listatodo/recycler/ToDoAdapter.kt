package com.example.listatodo.recycler

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listatodo.R
import com.example.listatodo.database.ToDoDatabaseHandler
import kotlinx.android.synthetic.main.popup_calendario.view.*
import kotlinx.android.synthetic.main.popup_modifica.view.*

class ToDoAdapter(var list : ArrayList<ToDo>, private val context : Context) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    fun settaCompletato(pos : Int){
        val db = ToDoDatabaseHandler(context)
        list[pos].completato = true
        db.updateToDo(list[pos])
        notifyItemChanged(pos)
    }

    fun settaPreferito(pos : Int){
        val db = ToDoDatabaseHandler(context)
        list[pos].preferito = true
        db.updateToDo(list[pos])
        notifyItemChanged(pos)
    }

    fun cancellaByPosition(pos: Int) {

        val db = ToDoDatabaseHandler(context)

        val id = list[pos].id

        if (id != null) {
            db.deleteToDo(id)
            list.removeAt(pos)
            notifyItemRemoved(pos)
        }

    }

    @SuppressLint("InflateParams")
    fun modificaToDoByPosition(pos : Int) {
        val db = ToDoDatabaseHandler(context)

        val view = LayoutInflater.from(context).inflate(R.layout.popup_modifica, null, false)
        val testoToDo = view.modifica_popup
        val btnSalvaModifiche = view.btn_modifica_popup
        val btnData = view.btn_data_popup

        val dialogBuilder = AlertDialog.Builder(context).setView(view)
        val pop_mod: AlertDialog? = dialogBuilder!!.create()

        pop_mod!!.show()

        btnSalvaModifiche.setOnClickListener {
            if (!TextUtils.isEmpty(testoToDo.text.toString())) {
                list[pos].testo = testoToDo.text.toString()
                db.updateToDo(list[pos])
                pop_mod.dismiss()
                notifyItemChanged(pos)
            }
        }

        btnData.setOnClickListener{
            val view1 = LayoutInflater.from(context).inflate(R.layout.popup_calendario,null, false)
            val calendario = view1.popup_calendario
            val dialogBuilder1 = AlertDialog.Builder(context).setView(view1)
            val pop_cal: AlertDialog? = dialogBuilder1!!.create()

            pop_cal!!.show()

            calendario.setOnDateChangeListener{ _, anno, mese, giorno ->
                list[pos].dataScadenza = formattaData(anno,mese,giorno)
                pop_cal.dismiss()
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

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        var nome = itemView.findViewById(R.id.testo_singolo_todo) as TextView
        var data = itemView.findViewById(R.id.data_singolo_todo) as TextView

        //var btn_modifica = itemView.findViewById(R.id.btn_modifica) as Button
        //var btn_cancella = itemView.findViewById(R.id.btn_cancella) as Button

        fun bindView(todo: ToDo) {

            nome.text = todo.testo
            data.text = todo.dataScadenza
            //btn_modifica.setOnClickListener(this)
            //btn_cancella.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        /*
            if (p0!!.id == btn_cancella.id) {

                cancellaToDo(list[adapterPosition].id!!)

                list.removeAt(adapterPosition)

                notifyItemRemoved(adapterPosition)

                Toast.makeText(context, "Cancellato", Toast.LENGTH_LONG).show()

                return
            }
        * */

            modificaToDoByPosition(adapterPosition)
            Toast.makeText(context, "Modificato", Toast.LENGTH_LONG).show()
        }

        fun cancellaToDo(id: Int) {
            val db = ToDoDatabaseHandler(context)
            db.deleteToDo(id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.cardview,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

package ru.app.mvvm.ui.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import ru.app.R
import ru.app.mvvm.models.Note

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var listNotes = emptyList<Note>()

    fun setList(list: List<Note>) {
        listNotes = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textNote.text = listNotes[position].text
        holder.nameNote.text = listNotes[position].name
    }

    override fun getItemCount(): Int = listNotes.size


    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.item_note_name
        val textNote: TextView = view.item_note_text


    }


}
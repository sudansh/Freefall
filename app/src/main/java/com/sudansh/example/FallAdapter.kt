package com.sudansh.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sudansh.falldetection.model.FallRecord
import kotlinx.android.synthetic.main.item_records.view.*
import java.text.DateFormat

class FallAdapter :
    RecyclerView.Adapter<RepoViewHolder>() {
    private val data: MutableList<FallRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_records, parent, false)
        return RepoViewHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(vh: RepoViewHolder, position: Int) {
        vh.itemView.date.text =
            DateFormat.getDateInstance().format(data[position])
        vh.itemView.duration.text = "${data[position].duration} ms"
    }

    fun updateItems(data: List<FallRecord>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}

class RepoViewHolder(v: View) : RecyclerView.ViewHolder(v)
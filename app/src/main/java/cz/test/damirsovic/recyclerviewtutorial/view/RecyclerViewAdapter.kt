package cz.test.damirsovic.recyclerviewtutorial.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.test.damirsovic.recyclerviewtutorial.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.data_item.view.*

class RecyclerViewAdapter(val items:List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.descriptionItem.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val view : View): RecyclerView.ViewHolder(view){
        val descriptionItem = view.itemDescription

    }
}
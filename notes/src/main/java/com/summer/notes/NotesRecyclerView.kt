package com.summer.notes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.summer.a.notes.databinding.NotesItemBinding

class NotesRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    init {
        layoutManager = LinearLayoutManager(context)
        adapter = NotesAdapter(context).apply {}
    }
}

class NotesAdapter(val context: Context):RecyclerView.Adapter<NotesHolder>(){

    val notesViewModel by lazy { ViewModelProvider(context as FragmentActivity)[NotesViewModel::class.java] }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false).root)
    }


    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.binding.text.text = notesViewModel.笔记列表数据[position]
        holder.binding.text.setOnClickListener {
            NotesDialog(context){text->
                if(!text.isEmpty()){
                    notesViewModel.笔记列表数据.add(text)
                    notifyItemRangeInserted(itemCount,1)
                }
            }.show()
        }
    }

    override fun getItemCount(): Int {
        return notesViewModel.笔记列表数据.size
    }

}

class NotesHolder(itemView: View) :ViewHolder(itemView){
    val binding by lazy { NotesItemBinding.bind(itemView) }
    init {
        binding
    }
}
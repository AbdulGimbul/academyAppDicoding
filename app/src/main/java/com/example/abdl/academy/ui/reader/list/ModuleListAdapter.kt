package com.example.abdl.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abdl.academy.data.source.local.entity.ModuleEntity
import com.example.abdl.academy.databinding.ItemsModuleListCustomBinding

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener): RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>(){
    private val listModules = ArrayList<ModuleEntity>()

    internal fun setModules(modules: List<ModuleEntity>?){
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModuleListAdapter.ModuleViewHolder {
        val binding = ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModuleListAdapter.ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener{
            listener.onItemClicked(holder.adapterPosition, listModules[holder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    inner class ModuleViewHolder(private val binding: ItemsModuleListCustomBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(module: ModuleEntity){
            binding.textModuleTitle.text = module.title
        }
    }
}

internal interface MyAdapterClickListener{
    fun onItemClicked(position: Int, moduleId: String)
}
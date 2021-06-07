package com.example.abdl.academy.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abdl.academy.data.source.local.entity.ModuleEntity
import com.example.abdl.academy.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {
    private val listModules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?){
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCourseAdapter.ModuleViewHolder {
        val itemModuleListBinding = ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(itemModuleListBinding)
    }

    override fun onBindViewHolder(holder: DetailCourseAdapter.ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    inner class ModuleViewHolder(private val binding: ItemsModuleListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(module: ModuleEntity){
            binding.textModuleTitle.text = module.title
        }
    }

}
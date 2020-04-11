package com.example.thingtodo.feature.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thingtodo.R
import com.example.thingtodo.databinding.ItemTypeTaskBinding
import com.vunguyenhoang.core.model.TaskListType
import com.vunguyenhoang.core.model.TypeTask

class TypeTaskAdapter(val listener: (TypeTask) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var typeTask = arrayListOf<TaskListType>()

    fun updateList(list: List<TaskListType>){
        typeTask.clear()
        typeTask.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemTypeTaskBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_type_task,
            parent,
            false
        )
        return TypeViewHolder(binding)
    }

    override fun getItemCount(): Int = typeTask.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TypeViewHolder)?.onBind(typeTask.getOrNull(position) ?: return)
    }


    inner class TypeViewHolder(private val binding: ItemTypeTaskBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(item: TaskListType){
            binding.type = item
            binding.ivImgType.setImageResource(when(item.title){
                TypeTask.WORK -> R.drawable.ic_work_task
                TypeTask.PERSONAL -> R.drawable.ic_person_task
                TypeTask.ALL -> R.drawable.ic_all_task
                TypeTask.ADD_NEW -> R.drawable.ic_add_list
                else -> R.drawable.ic_add_list
            })
            binding.root.setOnClickListener {
                listener(item.title)
            }
        }
    }
}
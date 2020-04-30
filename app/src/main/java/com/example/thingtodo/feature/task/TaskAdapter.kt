package com.example.thingtodo.feature.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thingtodo.R
import com.example.thingtodo.databinding.ItemTaskBinding
import com.example.thingtodo.viewmodel.TaskViewModel
import com.vunguyenhoang.core.model.Task

class TaskAdapter(private val viewModel: TaskViewModel) :
    PagedListAdapter<Task, RecyclerView.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TaskViewHolder)?.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task?) {
            item?.let {
                binding.item = item
                binding.viewModel = viewModel
                binding.root.setOnClickListener {
                    if (item.isShowCheck.get() == true) {
                        item.isCheck.set(!item.isCheck.get()!!)
                    }
                }
                binding.root.setOnLongClickListener {
                    // TODO: need to fix this feature
                    //  flow: add new then long press -> bug show up
                    if (item.isShowCheck.get() == true) {
                        currentList?.forEach {
                            it.isShowCheck.set(false)
                            it.isCheck.set(false)
                        }
                    } else {
                        currentList?.forEach { it.isShowCheck.set(true) }
                    }
                    viewModel.needToShowDeleteAll.set(item.isShowCheck.get())
                    false
                }
            }
        }
    }
}
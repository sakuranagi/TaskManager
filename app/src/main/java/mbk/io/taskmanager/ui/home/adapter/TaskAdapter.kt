package mbk.io.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mbk.io.taskmanager.databinding.ItemTaskBinding
import mbk.io.taskmanager.model.Task

class TaskAdapter(): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val taskList = arrayListOf<Task>()
    fun addTask(task: Task){
        taskList.add(0, task)
        notifyItemChanged(0)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = taskList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(tasks: Task){
            binding.apply {
                tasks.apply {
                    tvTitle.text = title
                    tvDescription.text = description
                }
            }

        }
    }
}
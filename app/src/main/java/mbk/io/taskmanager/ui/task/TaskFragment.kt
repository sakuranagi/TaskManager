package mbk.io.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import mbk.io.taskmanager.App
import mbk.io.taskmanager.R
import mbk.io.taskmanager.databinding.FragmentTaskBinding
import mbk.io.taskmanager.databinding.ItemTaskBinding
import mbk.io.taskmanager.model.Task
import mbk.io.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = arguments?.getSerializable(HomeFragment.TASK_EDIT_KEY) as Task?
        if (task != null){
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
        }
        binding.btnSave.setOnClickListener{
            if (binding.etTitle.text.toString().isNotEmpty()){
                if (task != null){
                    update(task)
                }else save()
                    findNavController().navigateUp()
            }else binding.etTitle.error = "Enter Title"
        }
    }

    private fun update(task: Task) {
        App.db.taskDao().update(task.copy(title = binding.etTitle.text.toString(), description = binding.etDescription.text.toString()))
    }

    private fun save(){
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.db.taskDao().insert(data)
    }

}
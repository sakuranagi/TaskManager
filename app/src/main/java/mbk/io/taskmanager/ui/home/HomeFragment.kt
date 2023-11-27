package mbk.io.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import mbk.io.taskmanager.App
import mbk.io.taskmanager.R
import mbk.io.taskmanager.databinding.FragmentHomeBinding
import mbk.io.taskmanager.model.Task
import mbk.io.taskmanager.ui.home.adapter.TaskAdapter
import mbk.io.taskmanager.ui.task.TaskFragment.Companion.TASK_KEY
import mbk.io.taskmanager.ui.task.TaskFragment.Companion.TASK_RESULT_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this:: onLongClickItem)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTask.adapter = adapter

        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)

        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }


    private fun onLongClickItem(tasks:Task){
        showAlertDialog(tasks)
    }

    private fun showAlertDialog(tasks: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить? " + tasks.title)
            .setMessage("Вы точно хотите удалить?")
            .setCancelable(true)
            .setPositiveButton("Да"){_,_ ->
                App.db.taskDao().delete(tasks)
                val data = App.db.taskDao().getAll()
                adapter.addTasks(data)
            }
            .setNegativeButton("Нет"){_,_ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
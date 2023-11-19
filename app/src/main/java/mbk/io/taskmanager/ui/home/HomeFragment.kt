package mbk.io.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import mbk.io.taskmanager.R
import mbk.io.taskmanager.databinding.FragmentHomeBinding
import mbk.io.taskmanager.model.Task
import mbk.io.taskmanager.ui.home.adapter.TaskAdapter
import mbk.io.taskmanager.ui.task.TaskFragment.Companion.TASK_KEY
import mbk.io.taskmanager.ui.task.TaskFragment.Companion.TASK_RESULT_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter()
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

        setFragmentResultListener(TASK_RESULT_KEY) {_, bundle ->
            val data = bundle.getSerializable(TASK_KEY) as Task
            adapter.addTask(data)
        }

        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
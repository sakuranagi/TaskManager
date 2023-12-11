package mbk.io.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import mbk.io.taskmanager.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.firestore
import mbk.io.taskmanager.R
import mbk.io.taskmanager.model.Book
import mbk.io.taskmanager.utils.showToast

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener {
            val data = Book(
                name = etName.text.toString(),
                author = etAuthor.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data)
                .addOnSuccessListener {
                    etName.text.clear()
                    etAuthor.text.clear()
                    showToast(getString(R.string.success_book_saved))
                }
                .addOnFailureListener {
                    showToast(it.message.toString())
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
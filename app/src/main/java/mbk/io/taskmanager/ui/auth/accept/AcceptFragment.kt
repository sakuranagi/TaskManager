package mbk.io.taskmanager.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import mbk.io.taskmanager.R
import mbk.io.taskmanager.databinding.FragmentAcceptBinding
import mbk.io.taskmanager.ui.auth.phone.PhoneFragment
import mbk.io.taskmanager.utils.showToast


class AcceptFragment : Fragment() {

    private lateinit var binding: FragmentAcceptBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString(PhoneFragment.VER_KEY)
        binding.btnConfirm.setOnClickListener{
            val credential = PhoneAuthProvider.getCredential(verId!!, binding.etCode.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                showToast("Auth is success!")
                findNavController().navigate(R.id.navigation_home)
            }
            .addOnFailureListener {
                showToast(it.message.toString())
            }

    }
}
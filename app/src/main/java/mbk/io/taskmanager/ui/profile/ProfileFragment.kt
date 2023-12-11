package mbk.io.taskmanager.ui.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import mbk.io.taskmanager.R
import mbk.io.taskmanager.data.local.Pref
import mbk.io.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val pref:Pref by lazy {
        Pref(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etProfile.setText(pref.getName())
        binding.etProfile.addTextChangedListener {
            pref.saveName(binding.etProfile.text.toString())
        }

        binding.ivExit.setOnClickListener(){
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Выйти")
                .setMessage("Вы уверенны что хотите выйти?")
                .setCancelable(true)
                .setPositiveButton("Да"){_,_ ->
                    findNavController().navigate(R.id.phoneFragment)
                }
                .setNegativeButton("Нет"){_,_ -> }
                .show()
        }

        binding.profileImage.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,777)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            pref.savePicture(imageUri.toString())
            binding.profileImage.setImageURI(imageUri)
        }

    }
}
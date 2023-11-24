package mbk.io.taskmanager.ui.onboarding

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mbk.io.taskmanager.R
import mbk.io.taskmanager.data.local.Pref
import mbk.io.taskmanager.databinding.FragmentOnBoardingBinding
import mbk.io.taskmanager.ui.onboarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    private val adapter = OnBoardingAdapter(this::onClick)
    private val pref: Pref by lazy{
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter

        binding.circleIndicator.setViewPager(binding.viewPager)
    }

    private fun onClick(){
        pref.onBoardingShow()
        findNavController().navigate(R.id.navigation_home)
    }
}
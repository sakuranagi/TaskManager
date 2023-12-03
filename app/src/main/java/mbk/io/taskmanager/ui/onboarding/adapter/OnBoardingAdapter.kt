package mbk.io.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import mbk.io.taskmanager.R
import mbk.io.taskmanager.databinding.ItemOnboardingBinding
import mbk.io.taskmanager.model.OnBoarding
import mbk.io.taskmanager.ui.home.adapter.TaskAdapter

class OnBoardingAdapter(private val onClick:()-> Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

private val  list = arrayListOf<OnBoarding>(
    OnBoarding(R.raw.anim, "Добро пожаловать в Task Manager! ", "Мы рады, что вы с нами"),
    OnBoarding(R.raw.anim2,"Добавляйте задачи","Нажмите на задачу, чтобы отредактировать ее."),
    OnBoarding(R.raw.last_anim,"Будьте продуктивны","и не забывайте о важных делах!")
)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding): ViewHolder(binding.root){

        fun bind(boarding: OnBoarding){
            binding.apply {
                boarding.apply {
                    tvTitle.text = title
                    tvDescription.text = description
                    animationView.setAnimation(image)
                    animationView.playAnimation()
                }
                btnStart.isVisible = adapterPosition == list.lastIndex
                skip.isVisible = adapterPosition != list.lastIndex
                skip.setOnClickListener{onClick()}
                btnStart.setOnClickListener{onClick()}
            }
        }

    }
}
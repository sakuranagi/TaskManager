package mbk.io.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import mbk.io.taskmanager.databinding.ItemOnboardingBinding
import mbk.io.taskmanager.model.OnBoarding
import mbk.io.taskmanager.ui.home.adapter.TaskAdapter

class OnBoardingAdapter(private val onClick:()-> Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

private val  list = arrayListOf<OnBoarding>(
    OnBoarding("https://ryohkei.com/wp-content/uploads/2020/01/welcome1.7.jpg", "Добро пожаловать! ", "Мы рады, что вы с нами"),
    OnBoarding("https://cdn-icons-png.flaticon.com/512/1950/1950715.png","Организуйте свой день",""),
    OnBoarding("https://static.thenounproject.com/png/2587261-200.png","Ничего не забывайте!","Будьте продуктивны вместе  с Task Manager")
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
                    Glide.with(ivBoarding).load(boarding.image).into(ivBoarding)
                }
                btnStart.isVisible = adapterPosition == list.lastIndex
                skip.isVisible = adapterPosition != list.lastIndex

                skip.setOnClickListener{onClick()}
                btnStart.setOnClickListener{onClick()}
            }
        }
    }
}
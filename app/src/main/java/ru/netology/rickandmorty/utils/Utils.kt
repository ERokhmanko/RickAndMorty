package ru.netology.rickandmorty.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.netology.rickandmorty.R

object Utils {

    fun uploadingAvatar(view: ImageView, avatar: String?) {
        Glide.with(view)
            .load(avatar)
            .circleCrop()
            .placeholder(R.drawable.ic_avatar_165)
            .timeout(10_000)
            .into(view)
    }
}
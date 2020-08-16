package com.example.assignment_shaadi_kotlin.ui.matchprofile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.databinding.ItemUserProfileBinding


class MatchProfileAdapter(private val listener: MatchProfileItemListener) : RecyclerView.Adapter<MatchProfileViewHolder>() {

    interface MatchProfileItemListener {
        fun onClickedCharacter(characterId: String)
    }

    private val items = ArrayList<Result>()

    fun setItems(items: ArrayList<Result>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchProfileViewHolder {
        val binding: ItemUserProfileBinding = ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchProfileViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MatchProfileViewHolder, position: Int) = holder.bind(items[position])
}

class MatchProfileViewHolder(private val itemBinding: ItemUserProfileBinding, private val listener: MatchProfileAdapter.MatchProfileItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: Result

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item:Result) {
        this.character = item
        itemBinding.name.text = item.email
        Glide.with(itemBinding.root)
            .load(item.picture.medium)
            .transform(CircleCrop())
            .into(itemBinding.imgMatchImageProfile)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(character.email)
    }
}


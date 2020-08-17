package com.example.assignment_shaadi_kotlin.ui.matchprofile

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.assignment_shaadi_kotlin.R
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.databinding.ItemUserProfileBinding


class MatchProfileAdapter(private val listener: MatchProfileAcceptedItemListener,private val declistener: MatchProfileDeclinedItemListener) : RecyclerView.Adapter<MatchProfileViewHolder>() {

    interface MatchProfileAcceptedItemListener {
        fun onClickedAccepted( viewId: Int,characterId: Result)
    }
    interface MatchProfileDeclinedItemListener {
        fun onClickedDeclined( viewId: Int,characterId: Result)
    }

    private val items = ArrayList<Result>()

    fun setItems(items: ArrayList<Result>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchProfileViewHolder {
        val binding: ItemUserProfileBinding = ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchProfileViewHolder(binding, listener,declistener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MatchProfileViewHolder, position: Int) = holder.bind(items[position])
}

class MatchProfileViewHolder(private val itemBinding: ItemUserProfileBinding, private val listener: MatchProfileAdapter.MatchProfileAcceptedItemListener,private val declistener: MatchProfileAdapter.MatchProfileDeclinedItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var character: Result

    init {
        itemBinding.imgAcceptProfile.setOnClickListener(this)
        itemBinding.imgDeclineProfile.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    fun bind(item:Result) {
        this.character = item
        if(item.isAccepted == "Accepted"){
            itemBinding.txtAccept.visibility = View.VISIBLE
            itemBinding.txtDecline.visibility= View.GONE
            itemBinding.imgAcceptProfile.visibility= View.GONE
            itemBinding.imgDeclineProfile.visibility= View.GONE
            itemBinding.cardMain.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
        }
        else if (item.isAccepted == "Declined"){
            itemBinding.txtDecline.visibility= View.VISIBLE
            itemBinding.txtAccept.visibility = View.GONE
            itemBinding.imgDeclineProfile.visibility= View.GONE
            itemBinding.imgAcceptProfile.visibility= View.GONE
            itemBinding.cardMain.setCardBackgroundColor(Color.parseColor("#E57373"))
        }
        else{
            itemBinding.txtAccept.visibility = View.GONE
            itemBinding.txtDecline.visibility= View.GONE
            itemBinding.imgAcceptProfile.visibility= View.VISIBLE
            itemBinding.imgDeclineProfile.visibility= View.VISIBLE
            itemBinding.cardMain.setCardBackgroundColor(Color.parseColor("#CFD8DC"))
        }

            itemBinding.name.text =
                item.name.first + " " + item.name.last


        itemBinding.city.text= item.dob.age.toString() + " yrs"
        Glide.with(itemBinding.root)
            .load(item.picture.large)
            .transform(CircleCrop())
            .into(itemBinding.imgMatchImageProfile)

    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.img_accept_profile) {
            listener.onClickedAccepted(v!!.id, character)
            itemBinding.txtAccept.visibility = View.VISIBLE
            itemBinding.txtDecline.visibility= View.GONE
            itemBinding.imgAcceptProfile.visibility= View.GONE
            itemBinding.imgDeclineProfile.visibility= View.GONE
            //
        }
        else {
            declistener.onClickedDeclined(v!!.id,character)
            itemBinding.txtDecline.visibility= View.VISIBLE
            itemBinding.txtAccept.visibility = View.GONE
            itemBinding.imgDeclineProfile.visibility= View.GONE
            itemBinding.imgAcceptProfile.visibility= View.GONE

        }
    }
}


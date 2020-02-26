package studio.inprogress.postertestapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_poster.view.*
import studio.inprogress.postertestapp.R
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity

class PosterListAdapter: RecyclerView.Adapter<PosterListAdapter.ViewHolder>() {

    private var posterList: List<PosterEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return posterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(posterList[position].imageUrl)
            .into(holder.posterImageView)
    }

    fun setPosterList(posterList: List<PosterEntity>) {
        this.posterList = posterList
    }

    fun updateList(posterList: List<PosterEntity>) {
        val diffCallback = object: DiffUtil.Callback() {

            private val oldList = this@PosterListAdapter.posterList
            private val newList = posterList

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem.id == newItem.id
            }

            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                return oldItem == newItem
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        setPosterList(posterList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.posterImageView
    }
}
package id.redlyst.codex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.redlyst.codex.R
import id.redlyst.codex.model.TopStoriesModel

class TopStoriesAdapter(var listStory: ArrayList<TopStoriesModel>) : RecyclerView.Adapter<TopStoriesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, title, comments, score) = listStory[position]
        holder.tvTitle.text = title
        holder.tvComments.text = comments
        holder.tvScore.text = score
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listStory[holder.adapterPosition]) }
//        holder.itemView.setOnClickListener {
//            Log.d("ID", "HASIL ${listStory[position].title}")
//        }

    }

    override fun getItemCount(): Int {
        return listStory.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvComments: TextView = itemView.findViewById(R.id.tv_comments)
        var tvScore: TextView = itemView.findViewById(R.id.tv_score)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TopStoriesModel)
    }
}
package com.example.ixd_projekt


import android.content.ReceiverCallNotAllowedException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_parent_item.view.*
import kotlinx.android.synthetic.main.home_playlist_item.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_home, container, false)

        val parentRecyclerView = layout.home_parentRV
        parentRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        parentRecyclerView.adapter = HomeParentAdapter()
        parentRecyclerView.isNestedScrollingEnabled = false

        return layout
    }


    private inner class HomeParentAdapter: RecyclerView.Adapter<HomeParentViewHolder>() {

        val homeCaptionTexts = arrayListOf("Heruntergeladene Musik","Deine Lieblingskünstler" , "Zuletzt gehört", "Deine Heavy Rotation", "Lange nicht gehört", "Empfohlene Alben")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentViewHolder{
            val itemView = layoutInflater.inflate(R.layout.home_parent_item, parent, false)
            return  HomeParentViewHolder(itemView)
        }
        override fun getItemCount(): Int {
            return 6
        }

        override fun onBindViewHolder(holder: HomeParentViewHolder, position: Int) {
            holder.itemView.home_parentItem_label.text = homeCaptionTexts[position]
            holder.itemView.home_parentItem_RV.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            when (position) {
                1 ->{
                    holder.itemView.home_parentItem_RV.adapter = HomeChildAdapter(1)
                }
                5 -> {

                    holder.itemView.home_parentItem_RV.adapter = HomeChildAdapter(2)
                }
                else -> {
                    holder.itemView.home_parentItem_RV.adapter = HomeChildAdapter(0)
                }
            }

            if (position == 0) {
                holder.itemView.home_parentItem_layout.setPadding(0, (50 * density).toInt(), 0, 0)
            }
        }


    }

    private class HomeParentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private inner class HomeChildAdapter(val type: Int): RecyclerView.Adapter<HomeChildViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChildViewHolder {
            val itemView =  if (type == 0 || type == 2) {
                layoutInflater.inflate(R.layout.home_playlist_item, parent, false)
            } else {
                layoutInflater.inflate(R.layout.home_artist_item, parent, false)
            }
            return HomeChildViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: HomeChildViewHolder, position: Int) {
            if (type == 2) {
                holder.itemView.home_playlistItem_title.text = "Album-Title"
            }
            if (type != 1) {
                holder.itemView.home_playlistItem_cover.setOnClickListener {
                    fragmentManager?.beginTransaction()?.replace(R.id.main_container, PlaylistFragment())?.commit()
                }
            }

        }

    }


    private class HomeChildViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}

package com.example.ixd_projekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_library.view.*
import kotlinx.android.synthetic.main.home_playlist_item.view.*
import kotlinx.android.synthetic.main.library_playlist_item.view.*

class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_library, container, false)

        val recyclerView = layout.library_recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = LibraryAdapter()

        return layout
    }

    private inner class LibraryAdapter: RecyclerView.Adapter<LibraryViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
            val itemView = if (viewType == 0) {
                layoutInflater.inflate(R.layout.library_search_item, parent, false)
            } else {
                layoutInflater.inflate(R.layout.library_playlist_item, parent, false)
            }
            return LibraryViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 7
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 0) {
                0
            } else {
                1
            }
        }

        override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
            if (position == 1) {

                val lp = holder.itemView.library_playlistItem_title.layoutParams as ConstraintLayout.LayoutParams
                lp.setMargins((24 * density).toInt(),(28 * density).toInt(), 0, 0)

                holder.itemView.library_playlistItem_title.text = "Playlis erstellen"
                holder.itemView.library_playlistItem_title.layoutParams = lp
                holder.itemView.library_playlistItem_creator.visibility = View.GONE
                holder.itemView.library_playlistItem_cover.setImageResource(R.drawable.ic_add_black_24dp)
                holder.itemView.library_playlistItem_cover.setPadding((16 * density).toInt(), (16 * density).toInt(), (16 * density).toInt(), (16 * density).toInt())
            }
            if (position > 1) {
                holder.itemView.setOnClickListener {
                    holder.itemView.setOnClickListener {
                        fragmentManager?.beginTransaction()?.replace(R.id.main_container, PlaylistFragment())?.commit()
                    }
                }
            }
        }

    }


    private class LibraryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

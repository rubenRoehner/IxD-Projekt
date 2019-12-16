package com.example.ixd_projekt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_playlist.view.*


class PlaylistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_playlist, container, false)
        layout.playlist_backButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, selectedFragment)?.commit()
        }

        layout.playlist_moreButton.setOnClickListener {
            val dialog = MenuDialog(context!!, MenuDialog.MenuDialogType.PLAYLIST)
            dialog.show()
        }

        val recyclerView = layout.playlist_recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = PlaylistAdapter()
        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity.setVisibilitySettingsButton(View.GONE)
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity.setVisibilitySettingsButton(View.VISIBLE)
    }

    private inner class PlaylistAdapter: RecyclerView.Adapter<PlaylistCustomViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PlaylistCustomViewHolder {
            var itemView: View
            when(viewType) {
                0 -> {
                    itemView = layoutInflater.inflate(R.layout.playlist_overview_item, parent, false)
                }
                1 -> {
                    itemView = layoutInflater.inflate(R.layout.playlist_button_item, parent, false)
                }
                else -> {
                    itemView = layoutInflater.inflate(R.layout.playlist_song_item, parent, false)
                }
            }

            return PlaylistCustomViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> 0
                1 -> 1
                else -> 2
            }
        }

        override fun onBindViewHolder(holder: PlaylistCustomViewHolder, position: Int) {

        }

    }


    private class PlaylistCustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
